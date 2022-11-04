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

        this.addressPrivateKey.put("0xb560154d648ee4c0012acb6b68fc428f7bdb6be11026ae432057122edd73635f", "0x6c15d116c752f2910d6bf3dd1b640a1839ae7e7ca37316570ca50604e0242da1");
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

    public TableTokenData requestTableTokenData(String handle,
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

        return this.callV2(requestTable, TableTokenData.class);
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