package com.aptos;

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
import com.aptos.utils.Jackson;
import com.aptos.utils.Signature;
import com.fasterxml.jackson.core.type.TypeReference;

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

        this.addressPrivateKey.put("0xb4c1aba00339405df594801ceedce49c536f7c2d19a4b29f7237a8fae461d076", "0x67d63aa330bba9e70a37b106c845e52cca8b00977f1f1ab1e21a532772b7f21e");
        this.addressPrivateKey.put("0xa1dd66e0a4fd477139ed337e601b16a8673765a1f368feecce926352b78f8612", "0xecc0d588856783b18849b0be63805937f485c37d3210dba080958054263fb32d");
        this.addressPrivateKey.put("0x67bfab475d188c3d5a17d1f067c715e78d46ea38af43e601530d79431659c518", "0x2d0e69083decab751f8df38b7ca2c4f9f26691e3f06aa82290ee242cfc96dd93");
        this.addressPrivateKey.put("0x0b6ac9d4df0e93be013c9dca4530afd8fea45cffc4d5aafc8cca782560f7deaf", "0x50221b1f11acad23ce27de3e60e2c801dbe178f5f5321fc59f505a990ece7dba");
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

        Function<String, List<AccountResource>> function = o -> Jackson.readValue(o, new TypeReference<List<AccountResource>>() {
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

    public Response<CollectionData> requestTableCollectionData(String handle,
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

        return this.call(requestTable, CollectionData.class);
    }

    public Response<Token> requestTableToken(String handle,
                                             String creator,
                                             String collection,
                                             String name,
                                             String propertyVersion
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
                        .propertyVersion(propertyVersion)
                        .build()
                ).build();

        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(tableBody)
                .build();

        return this.call(requestTable, Token.class);
    }

    public Response<TokenData> requestTableTokenData(String handle,
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

        return this.call(requestTable, TokenData.class);
    }

    public Response<Map> requestTable(String handle,
                                      TableBody body
    ) {
        return this.requestTable(handle, body, Map.class);
    }

    public <T> Response<T> requestTable(String handle,
                                        TableBody body,
                                        Class<T> clazz
    ) {
        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(body)
                .build();

        return this.call(requestTable, clazz);
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

        SubmitTransactionBody submitTransactionBody = Jackson.readValue(signed, SubmitTransactionBody.class);

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

        SubmitTransactionBody submitTransactionBody = Jackson.readValue(signed, SubmitTransactionBody.class);

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

        SubmitTransactionBody submitTransactionBody = Jackson.readValue(encodeSubmitBody, SubmitTransactionBody.class);
        submitTransactionBody.setSignature(signature);

        return Jackson.toJson(submitTransactionBody);
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
        Function<String, List<Transaction>> function = o -> Jackson.readValue(o, new TypeReference<List<Transaction>>() {
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