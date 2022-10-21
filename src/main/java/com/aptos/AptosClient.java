package com.aptos;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.aptos.request.v1.model.*;
import com.aptos.request.v1.model.CoinInfo;
import com.aptos.request.v1.model.CoinStore;
import com.aptos.request.v1.model.AccountResource;
import com.aptos.request.v1.rpc.body.EncodeSubmitBody;
import com.aptos.request.v1.rpc.body.SubmitTransactionBody;
import com.aptos.request.v1.rpc.body.TableBody;
import com.aptos.request.v1.rpc.query.RequestBlockQuery;
import com.aptos.request.v1.rpc.query.RequestTransactionQuery;
import com.aptos.request.v1.rpc.request.*;
import com.aptos.utils.Hex;
import com.aptos.utils.Signature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author liqiang
 */
public class AptosClient extends AbstractClient {

    Map<String, String> addressPrivateKey = new HashMap<>();

    public AptosClient(String host, Consumer<RequestInfo> info, Consumer<String> log) {
        super(host, info, log);

        this.addressPrivateKey.put("0x1ff00114f046e27033b9bfdcd217fcf50023b576cbd3baaafe9674961632a5bd", "0x8710d71f5e02f5dd2d75d748abdbd778b427efa31e7a2e23344ea94e524476ff");
        this.addressPrivateKey.put("0xe89e92f0ea0ccb394fa3cb10a72ad866c4ad786956898fe7164731aa348ec1c5", "0x679213b91c104a590ed0929ce40840d0bdd6c28c419a6f51734e086ccb13b314");
        this.addressPrivateKey.put("0xf7e09293bfc8a0c70a4bf9b6fecc4527da518dc4d8a60a84c293de6854dae0d8", "0x6a1d7fe3e8cf255279d43732ffcf88fd7843e5fca5cfffbe41a552b4d25954b9");
        this.addressPrivateKey.put("0x4cd5040c25c069143f22995f0deaae6bfb674949302b008678455174b8ea8104", "0x7f4f1932d406a5e4857bb3e03bce782941cbcebbd800a5b61623433e930f25fe");
        this.addressPrivateKey.put("0x5278d1f03aa5b3586d0c835835e6593dcb629f0fbcf69365b741365041d31a5a", "0x91f088fc2baaf07b4403e4aec916eb6ff1d3c7095b5380fe139da56dc73bb9b7");
        this.addressPrivateKey.put("0x48d2915b483ab4c88fb420ffe0e784c5ffb44a39c4aec197a6871dc835fa0fa", "0x8ae91715a4328ce244dfe2f30d3f3b72c8c9cc60c18feeeef4886c19599de60d");
        this.addressPrivateKey.put("0xf350c47af19cb5c1de817454e8ca925581d813d055fe276dca99ee91b4dda259", "0xb4bde7f579247c785968ac530761832e23eef5096b84646edae1fdcfdc9362f5");
        this.addressPrivateKey.put("0xb1ddfbcb10bfd108577a49d84f59b1eea2bdec252b4b58616345c8a7e12e7392", "0x4c8c1b6217091f228fd8c25aba77bea83861d2c8eacaf08205aa49e5bf524dce");
        this.addressPrivateKey.put("0x1c87ad158f251d661cbacb167e0e459ab1ab43e1a3ca61edbf548f1cc6b23b11", "0xc6913a0bf06eb49f49957f4cbccb802d7f2ef0913910066db185122281c9b915");
        this.addressPrivateKey.put("0x3fccd08ccac071ee2d241344e6909068c1a152d3051ad22553abd2b8c4a2e3b8", "0x940d1805f68533bfc281dce0952fce288260a541bfa2f3ea2b0479845ec49dc2");
    }

    public Response<Account> requestAccount(String account) {
        RequestAccount requestAccount = RequestAccount.builder()
                .account(account)
                .build();

        return this.call(requestAccount, Account.class);
    }

    public Response<Node> requestNode() {
        RequestNode requestNode = RequestNode.builder().build();

        return this.call(requestNode, Node.class);
    }

    public Response<GasEstimate> requestGasEstimate() {
        RequestGasEstimate requestGasEstimate = RequestGasEstimate.builder().build();

        return this.call(requestGasEstimate, GasEstimate.class);
    }

    public Response<List<AccountResource>> requestAccountResources(String account) {
        RequestAccountResources requestAccountResources = RequestAccountResources.builder()
                .account(account)
                .build();

        Function<String, List<AccountResource>> function = o -> JSONObject.parseObject(o, new TypeReference<List<AccountResource>>() {
        });

        return this.callList(requestAccountResources, function);
    }

    public <T> Response<T> requestAccountResource(String account,
                                                  Resource resource,
                                                  Class<T> clazz
    ) {
        RequestAccountResource requestAccountResource = RequestAccountResource.builder()
                .account(account)
                .resource(resource)
                .build();

        return this.call(requestAccountResource, clazz);
    }

    public Response<AccountResource> requestAccountResource(String account,
                                                            Resource resource
    ) {
        return this.requestAccountResource(account, resource, AccountResource.class);
    }

    public Response<Block> requestBlockByHeight(String height,
                                                boolean withTransactions
    ) {
        RequestBlockQuery requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        RequestBlockByHeight requestBlockByHeight = RequestBlockByHeight.builder()
                .height(height)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByHeight, Block.class);
    }

    public Response<Block> requestBlockByVersion(
            String ledgerVersion,
            boolean withTransactions
    ) {
        RequestBlockQuery requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        RequestBlockByVersion requestBlockByVersion = RequestBlockByVersion.builder()
                .ledgerVersion(ledgerVersion)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByVersion, Block.class);
    }

    public Response<Transaction> requestTransactionByHash(String hash) {
        RequestTransactionByHash requestTransactionByHash = RequestTransactionByHash.builder()
                .hash(hash)
                .build();

        return this.call(requestTransactionByHash, Transaction.class);
    }

    public Response<CoinStore> requestCoinStore(String account,
                                                Resource resource
    ) {
        Resource coinStore = Resource.ofCoinStore(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinStore)
                .build();

        return this.call(requestAccountResources, CoinStore.class);
    }

    public Response<CoinInfo> requestCoinInfo(String account,
                                              Resource resource
    ) {
        Resource coinInfo = Resource.ofCoinInfo(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinInfo)
                .build();

        return this.call(requestAccountResources, CoinInfo.class);
    }

    public Response<TableCollectionData> requestTableCollectionData(String handle,
                                                                    String key
    ) {
        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(TableBody.builder()
                        .keyType("vector<u8>")
                        .valueType("0x3::token::CollectionData")
                        .key(key)
                        .build())
                .build();

        return this.call(requestTable, TableCollectionData.class);
    }

    public Response<TableTokenData> requestTableToken(String handle,
                                                      String creator,
                                                      String collection,
                                                      String name
    ) {
        TableBody tableBody = TableBody.builder()
                .keyType("0x3::token::TokenId")
                .valueType("0x3::token::Token")
                .key(TokenId.builder()
                        .tokenDataId(TokenDataId.builder()
                                .creator(creator)
                                .collection(collection)
                                .name(name)
                                .build()
                        )
                        .propertyVersion("0")
                        .build()
                ).build();

        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(tableBody)
                .build();

        return this.call(requestTable, TableTokenData.class);
    }

    public Response<TableTokenData> requestTableTokenData(String handle,
                                                          String creator,
                                                          String collection,
                                                          String name
    ) {
        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(TableBody.builder()
                        .keyType("0x3::token::TokenDataId")
                        .valueType("0x3::token::TokenData")
                        .key(TokenDataId.builder()
                                .creator(creator)
                                .collection(collection)
                                .name(name)
                                .build()
                        )
                        .build())
                .build();

        return this.call(requestTable, TableTokenData.class);
    }

    public Response<String> requestEncodeSubmit(EncodeSubmitBody body) {
        RequestEncodeSubmit requestEncodeSubmit = RequestEncodeSubmit.builder()
                .body(body)
                .build();

        return this.call(requestEncodeSubmit, String.class);
    }

    public Response<Transaction> requestSubmitTransaction(SubmitTransactionBody body) {
        RequestSubmitTransaction requestSubmitTransaction = RequestSubmitTransaction.builder()
                .body(body)
                .build();

        return this.call(requestSubmitTransaction, Transaction.class);
    }

    public Response<Transaction> requestSubmitTransaction(
            String sender,
            TransactionPayload transactionPayload
    ) {
        Response<EncodeSubmitBody> encodeSubmitBodyResponse = this.encodeSubmitBody(sender, transactionPayload);
        if (encodeSubmitBodyResponse.isValid()) {
            return Response.from(encodeSubmitBodyResponse, Transaction.class);
        }

        Response<String> stringResponse = this.requestEncodeSubmit(encodeSubmitBodyResponse.getData());
        if (stringResponse.isValid()) {
            return Response.from(stringResponse, Transaction.class);
        }

        String signed = this.sign(this.getAddressPrivateKey(sender), stringResponse.getData(), encodeSubmitBodyResponse.getData());

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public Response<Transaction> transferResource(
            String from,
            String to,
            String amount,
            Resource resource
    ) {
        TransactionPayload transactionPayload = TransactionPayload.builder()
                .type(TransactionPayload.ENTRY_FUNCTION_PAYLOAD)
                .function("0x1::coin::transfer")
                .arguments(List.of(
                        to,
                        amount
                ))
                .typeArguments(List.of(resource.resourceTag()))
                .build();

        Response<EncodeSubmitBody> encodeSubmitBodyResponse = this.encodeSubmitBody(from, transactionPayload);
        if (encodeSubmitBodyResponse.isValid()) {
            return Response.from(encodeSubmitBodyResponse, Transaction.class);
        }

        Response<String> stringResponse = this.requestEncodeSubmit(encodeSubmitBodyResponse.getData());
        if (stringResponse.isValid()) {
            return Response.from(stringResponse, Transaction.class);
        }

        String signed = this.sign(this.getAddressPrivateKey(from), stringResponse.getData(), encodeSubmitBodyResponse.getData());

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public Response<Transaction> transferApt(
            String from,
            String to,
            String amount
    ) {
        return this.transferResource(from, to, amount, Resource.APT());
    }

    public String sign(
            String privateKey,
            String encodeUnSign,
            EncodeSubmitBody encodeSubmitBody
    ) {
        byte[] signed = Signature.ed25519Sign(Hex.decode(privateKey), Hex.decode(encodeUnSign));

        com.aptos.request.v1.model.Signature signature = com.aptos.request.v1.model.Signature.builder()
                .type(com.aptos.request.v1.model.Signature.ED25519_SIGNATURE)
                .publicKey(Signature.getPublicKey(Hex.decode(privateKey)))
                .signature(Hex.encode(signed))
                .build();

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(JSONObject.toJSONString(encodeSubmitBody), SubmitTransactionBody.class);
        submitTransactionBody.setSignature(signature);

        return JSONObject.toJSONString(submitTransactionBody);
    }

    public Response<EncodeSubmitBody> encodeSubmitBody(
            String sender,
            TransactionPayload transactionPayload
    ) {
        Response<Account> accountResponse = this.requestAccount(sender);
        if (accountResponse.isValid()) {
            return Response.from(accountResponse, EncodeSubmitBody.class);
        }

        Response<GasEstimate> gasEstimateResponse = this.requestGasEstimate();
        if (gasEstimateResponse.isValid()) {
            return Response.from(gasEstimateResponse, EncodeSubmitBody.class);
        }

        EncodeSubmitBody encodeSubmitBody = new EncodeSubmitBody();
        encodeSubmitBody.setSender(sender);
        encodeSubmitBody.setSequenceNumber(accountResponse.getData().getSequenceNumber());
        //TODO 待优化
        encodeSubmitBody.setMaxGasAmount("400000");
        encodeSubmitBody.setGasUnitPrice(String.valueOf(gasEstimateResponse.getData().getGasEstimate()));
        encodeSubmitBody.setExpirationTimestampSecs(String.valueOf(System.currentTimeMillis() / 1000L + 600L));
        encodeSubmitBody.setPayload(transactionPayload);

        return Response.from(encodeSubmitBody);
    }

    public Response<List<Transaction>> requestTransaction(String start) {
        var requestTransaction = RequestTransaction.builder()
                .query(RequestTransactionQuery.builder()
                        //TODO
                        .limit("1000")
                        .start(start)
                        .build())
                .build();
        Function<String, List<Transaction>> function = o -> JSONObject.parseObject(o, new TypeReference<List<Transaction>>() {
        });

        return this.callList(requestTransaction, function);
    }

    String getAddressPrivateKey(String address) {
        var privateKey = this.addressPrivateKey.get(address);
        if (Objects.isNull(privateKey)) {
            throw new RuntimeException("private key is null");
        }
        return privateKey;
    }
}