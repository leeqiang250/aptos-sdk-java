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
import com.aptos.utils.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author liqiang
 */
public class AptosClient extends AbstractClient {

    Map<String, String> addressPrivateKey = new HashMap<>();

    public AptosClient(String host, Consumer<RequestInfo> info, Consumer<String> log) {
        super(host, info, log);


        this.addressPrivateKey.put("0xdddd7c9445fc09486e76ce574b8bef28a8b9ae6ffd835c0daa45fef36d83f5db", "0x48189cc7a9517ea582ee882fa86b8f80850daee767886f8f0ec0665a5beb5ec0");
        this.addressPrivateKey.put("0x91654d8ba0d53ee2eff2ea893b787f64985e37e89df99190f5660185128e2a2e", "0x945066fd2e7a193493f965ea107a33493b984049a71eca6b15ca2d2d22cb05bd");
        this.addressPrivateKey.put("0xb4c1aba00339405df594801ceedce49c536f7c2d19a4b29f7237a8fae461d076", "0x67d63aa330bba9e70a37b106c845e52cca8b00977f1f1ab1e21a532772b7f21e");
        this.addressPrivateKey.put("0xa1dd66e0a4fd477139ed337e601b16a8673765a1f368feecce926352b78f8612", "0xecc0d588856783b18849b0be63805937f485c37d3210dba080958054263fb32d");
        this.addressPrivateKey.put("0x67bfab475d188c3d5a17d1f067c715e78d46ea38af43e601530d79431659c518", "0x2d0e69083decab751f8df38b7ca2c4f9f26691e3f06aa82290ee242cfc96dd93");
        this.addressPrivateKey.put("0x0b6ac9d4df0e93be013c9dca4530afd8fea45cffc4d5aafc8cca782560f7deaf", "0x50221b1f11acad23ce27de3e60e2c801dbe178f5f5321fc59f505a990ece7dba");
    }

    public Response<Account> requestAccount(String account) {
        var requestAccount = RequestAccount.builder()
                .account(account)
                .build();

        return this.call(requestAccount, Account.class);
    }

    public Response<Node> requestNode() {
        var requestNode = RequestNode.builder().build();

        return this.call(requestNode, Node.class);
    }

    public Response<GasEstimate> requestGasEstimate() {
        var requestGasEstimate = RequestGasEstimate.builder().build();

        return this.call(requestGasEstimate, GasEstimate.class);
    }

    public Response<List<AccountResource>> requestAccountResources(String account) {
        var requestAccountResources = RequestAccountResources.builder()
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
        var requestAccountResource = RequestAccountResource.builder()
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
        var requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        var requestBlockByHeight = RequestBlockByHeight.builder()
                .height(height)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByHeight, Block.class);
    }

    public Response<Block> requestBlockByVersion(
            String ledgerVersion,
            boolean withTransactions
    ) {
        var requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        var requestBlockByVersion = RequestBlockByVersion.builder()
                .ledgerVersion(ledgerVersion)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByVersion, Block.class);
    }

    public Response<Transaction> requestTransactionByHash(String hash) {
        var requestTransactionByHash = RequestTransactionByHash.builder()
                .hash(hash)
                .build();

        return this.call(requestTransactionByHash, Transaction.class);
    }

    public Response<CoinStore> requestCoinStore(String account,
                                                Resource resource
    ) {
        var coinStore = Resource.ofCoinStore(resource);

        var requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinStore)
                .build();

        return this.call(requestAccountResources, CoinStore.class);
    }

    public Response<CoinInfo> requestCoinInfo(String account,
                                              Resource resource
    ) {
        var coinInfo = Resource.ofCoinInfo(resource);

        var requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinInfo)
                .build();

        return this.call(requestAccountResources, CoinInfo.class);
    }

    public Response<CollectionData> requestTableCollectionData(String handle,
                                                               String key
    ) {
        var requestTable = RequestTable.builder()
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
        var tableBody = TableBody.builder()
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

        var requestTable = RequestTable.builder()
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
        var requestTable = RequestTable.builder()
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
        var requestTable = RequestTable.builder()
                .handle(handle)
                .body(body)
                .build();

        return this.call(requestTable, clazz);
    }

    public Response<String> requestEncodeSubmit(EncodeSubmitBody body) {
        var requestEncodeSubmit = RequestEncodeSubmit.builder()
                .body(body)
                .build();

        return this.call(requestEncodeSubmit, String.class);
    }

    public Response<Transaction> requestSubmitTransaction(SubmitTransactionBody body) {
        var requestSubmitTransaction = RequestSubmitTransaction.builder()
                .body(body)
                .build();

        return this.call(requestSubmitTransaction, Transaction.class);
    }

    public Response<Transaction> requestSubmitTransaction(
            String sender,
            TransactionPayload transactionPayload
    ) {
        var encodeSubmitBodyResponse = this.encodeSubmitBody(sender, transactionPayload);
        if (encodeSubmitBodyResponse.isValid()) {
            return Response.from(encodeSubmitBodyResponse, Transaction.class);
        }

        var stringResponse = this.requestEncodeSubmit(encodeSubmitBodyResponse.getData());
        if (stringResponse.isValid()) {
            return Response.from(stringResponse, Transaction.class);
        }

        var signed = this.sign(this.getAddressPrivateKey(sender), stringResponse.getData(), encodeSubmitBodyResponse.getData());

        var submitTransactionBody = Jackson.readValue(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public Response<Transaction> transferResource(
            String from,
            String to,
            String amount,
            Resource resource
    ) {
        var transactionPayload = TransactionPayload.builder()
                .type(TransactionPayload.ENTRY_FUNCTION_PAYLOAD)
                .function("0x1::coin::transfer")
                .arguments(List.of(
                        to,
                        amount
                ))
                .typeArguments(List.of(resource.resourceTag()))
                .build();

        var encodeSubmitBodyResponse = this.encodeSubmitBody(from, transactionPayload);
        if (encodeSubmitBodyResponse.isValid()) {
            return Response.from(encodeSubmitBodyResponse, Transaction.class);
        }

        var stringResponse = this.requestEncodeSubmit(encodeSubmitBodyResponse.getData());
        if (stringResponse.isValid()) {
            return Response.from(stringResponse, Transaction.class);
        }

        var signed = this.sign(this.getAddressPrivateKey(from), stringResponse.getData(), encodeSubmitBodyResponse.getData());

        var submitTransactionBody = Jackson.readValue(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public Response<Transaction> transferApt(
            String from,
            String to,
            String amount
    ) {
        return this.transferResource(from, to, amount, Resource.apt());
    }

    public String sign(
            String privateKey,
            String encodeUnSign,
            EncodeSubmitBody encodeSubmitBody
    ) {
        var signed = Signature.ed25519Sign(Hex.decode(privateKey), Hex.decode(encodeUnSign));

        var signature = com.aptos.request.v1.model.Signature.builder()
                .type(com.aptos.request.v1.model.Signature.ED25519_SIGNATURE)
                .publicKey(Signature.getPublicKey(Hex.decode(privateKey)))
                .signature(Hex.encode(signed))
                .build();

        var submitTransactionBody = Jackson.readValue(encodeSubmitBody, SubmitTransactionBody.class);
        submitTransactionBody.setSignature(signature);

        return Jackson.toJson(submitTransactionBody);
    }

    public Response<EncodeSubmitBody> encodeSubmitBody(
            String sender,
            TransactionPayload transactionPayload
    ) {
        var accountResponse = this.requestAccount(sender);
        if (accountResponse.isValid()) {
            return Response.from(accountResponse, EncodeSubmitBody.class);
        }

        var gasEstimateResponse = this.requestGasEstimate();
        if (gasEstimateResponse.isValid()) {
            return Response.from(gasEstimateResponse, EncodeSubmitBody.class);
        }

        var encodeSubmitBody = new EncodeSubmitBody();
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

    /**
     * signMessage
     *
     * @param sender  sender
     * @param message 16进制
     * @return String
     */
    public String signMessage(String sender, String message) {
        if (StringUtils.isEmpty(message)) {
            return null;
        }

        var privateKey = this.getAddressPrivateKey(sender);
        return Hex.encode(Signature.ed25519Sign(Hex.decode(privateKey), Hex.decode(message)));
    }

    String getAddressPrivateKey(String address) {
        var privateKey = this.addressPrivateKey.get(address);
        if (Objects.isNull(privateKey)) {
            throw new RuntimeException("private key is null");
        }

        return privateKey;
    }
}