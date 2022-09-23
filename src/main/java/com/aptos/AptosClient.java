package com.aptos;

import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.v1.model.*;
import com.aptos.request.v1.response.CoinInfo;
import com.aptos.request.v1.response.CoinStore;
import com.aptos.request.v1.response.Resource;
import com.aptos.request.v1.rpc.body.EncodeSubmitBody;
import com.aptos.request.v1.rpc.body.SubmitTransactionBody;
import com.aptos.request.v1.rpc.query.RequestBlockQuery;
import com.aptos.request.v1.rpc.query.RequestLedgerVersionQuery;
import com.aptos.request.v1.rpc.body.CollectionDataBody;
import com.aptos.request.v1.rpc.request.*;
import com.aptos.utils.Hex;
import com.aptos.utils.Signature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author liqiang
 */
public class AptosClient extends AbstractClient {

    Map<String, String> addressPrivateKey = new HashMap<>();

    public AptosClient(String host) {
        super(host);

        this.addressPrivateKey.put("0x1ff00114f046e27033b9bfdcd217fcf50023b576cbd3baaafe9674961632a5bd", "0x8710d71f5e02f5dd2d75d748abdbd778b427efa31e7a2e23344ea94e524476ff");
        this.addressPrivateKey.put("0xe89e92f0ea0ccb394fa3cb10a72ad866c4ad786956898fe7164731aa348ec1c5", "0x679213b91c104a590ed0929ce40840d0bdd6c28c419a6f51734e086ccb13b314");
        this.addressPrivateKey.put("0xf7e09293bfc8a0c70a4bf9b6fecc4527da518dc4d8a60a84c293de6854dae0d8", "0x6a1d7fe3e8cf255279d43732ffcf88fd7843e5fca5cfffbe41a552b4d25954b9");
    }

    public Account requestAccount(String account) {
        RequestAccount requestAccount = RequestAccount.builder()
                .account(account)
                .build();

        return this.call(requestAccount, Account.class);
    }

    public Node requestNode() {
        RequestNode requestNode = RequestNode.builder().build();

        return this.call(requestNode, Node.class);
    }

    public GasEstimate requestGasEstimate() {
        RequestGasEstimate requestGasEstimate = RequestGasEstimate.builder().build();

        return this.call(requestGasEstimate, GasEstimate.class);
    }

    public List<Resource> requestAccountResources(String account) {
        return this.requestAccountResources(account, null);
    }

    public List<Resource> requestAccountResources(String account,
                                                  String ledgerVersion
    ) {
        RequestLedgerVersionQuery requestLedgerVersionQuery = null;
        if (Objects.nonNull(ledgerVersion)) {
            requestLedgerVersionQuery = RequestLedgerVersionQuery.builder()
                    .ledgerVersion(ledgerVersion)
                    .build();
        }

        RequestAccountResources requestAccountResources = RequestAccountResources.builder()
                .account(account)
                .query(requestLedgerVersionQuery)
                .build();

        return this.callList(requestAccountResources, Resource.class);
    }

    public Resource requestAccountResource(String account,
                                           com.aptos.request.v1.model.Resource resource
    ) {
        return this.requestAccountResource(account, resource, null);
    }

    public Resource requestAccountResource(String account,
                                           com.aptos.request.v1.model.Resource resource,
                                           String ledgerVersion
    ) {
        RequestLedgerVersionQuery requestLedgerVersionQuery = null;
        if (Objects.nonNull(ledgerVersion)) {
            requestLedgerVersionQuery = RequestLedgerVersionQuery.builder()
                    .ledgerVersion(ledgerVersion)
                    .build();
        }

        RequestAccountResource requestAccountResource = RequestAccountResource.builder()
                .account(account)
                .resource(resource)
                .query(requestLedgerVersionQuery)
                .build();

        return this.call(requestAccountResource, Resource.class);
    }

    public Block requestBlockByHeight(String height,
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

    public Block requestBlockByVersion(
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

    public Transaction requestTransactionByHash(String hash) {
        RequestTransactionByHash requestTransactionByHash = RequestTransactionByHash.builder()
                .hash(hash)
                .build();

        return this.call(requestTransactionByHash, Transaction.class);
    }

    public CoinStore requestCoinStore(String account,
                                      com.aptos.request.v1.model.Resource resource
    ) {
        com.aptos.request.v1.model.CoinStore coinStore = com.aptos.request.v1.model.CoinStore.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinStore)
                .build();

        return this.call(requestAccountResources, CoinStore.class);
    }

    public CoinInfo requestCoinInfo(String account,
                                    com.aptos.request.v1.model.Resource resource
    ) {
        com.aptos.request.v1.model.CoinInfo coinInfo = com.aptos.request.v1.model.CoinInfo.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinInfo)
                .build();

        return this.call(requestAccountResources, CoinInfo.class);
    }

    public CollectionData requestTableCollectionData(String handle,
                                                     String key
    ) {
        CollectionDataBody requestTableCollectionDataBody = CollectionDataBody.builder()
                .keyType("vector<u8>")
                .valueType("0x3::token::CollectionData")
                .key(key)
                .build();

        RequestTable requestTable = RequestTable.builder()
                .handle(handle)
                .body(requestTableCollectionDataBody)
                .build();

        return this.call(requestTable, CollectionData.class);
    }

    public String requestEncodeSubmit(EncodeSubmitBody body) {
        RequestEncodeSubmit requestEncodeSubmit = RequestEncodeSubmit.builder()
                .body(body)
                .build();

        return this.call(requestEncodeSubmit, String.class);
    }

    public Transaction requestSubmitTransaction(SubmitTransactionBody body) {
        RequestSubmitTransaction requestSubmitTransaction = RequestSubmitTransaction.builder()
                .body(body)
                .build();

        return this.call(requestSubmitTransaction, Transaction.class);
    }

    public Transaction requestSubmitTransaction(
            String sender,
            TransactionPayload transactionPayload
    ) {
        EncodeSubmitBody encodeSubmitBody = this.encodeSubmitBody(sender, transactionPayload);

        String encodeUnSign = this.requestEncodeSubmit(encodeSubmitBody);

        String signed = this.sign(this.addressPrivateKey.get(sender), JSONObject.toJSONString(encodeUnSign), encodeUnSign);

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public Transaction transferApt(
            String from,
            String to,
            String amount
    ) {
        TransactionPayload transactionPayload = TransactionPayload.builder()
                .type(TransactionPayload.ENTRY_FUNCTION_PAYLOAD)
                .function("0x1::coin::transfer")
                .arguments(List.of(
                        to,
                        amount
                ))
                .typeArguments(List.of(Struct.APT().resourceTag()))
                .build();

        String encodeUnSign = this.requestEncodeSubmit(this.encodeSubmitBody(from, transactionPayload));

        String signed = this.sign(this.addressPrivateKey.get(from), JSONObject.toJSONString(encodeUnSign), encodeUnSign);

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(signed, SubmitTransactionBody.class);

        return this.requestSubmitTransaction(submitTransactionBody);
    }

    public String sign(
            String privateKey,
            String unSign,
            String encodeUnSign
    ) {
        byte[] signed = Signature.ed25519Sign(Hex.decode(privateKey), Hex.decode(encodeUnSign));

        com.aptos.request.v1.model.Signature signature = com.aptos.request.v1.model.Signature.builder()
                .type(com.aptos.request.v1.model.Signature.ED25519_SIGNATURE)
                .publicKey(Signature.getPublicKey(Hex.decode(privateKey)))
                .signature(Hex.encode(signed))
                .build();

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(unSign, SubmitTransactionBody.class);
        submitTransactionBody.setSignature(signature);

        return JSONObject.toJSONString(submitTransactionBody);
    }

    public EncodeSubmitBody encodeSubmitBody(
            String sender,
            TransactionPayload transactionPayload
    ) {
        EncodeSubmitBody encodeSubmitBody = new EncodeSubmitBody();
        encodeSubmitBody.setSender(sender);
        encodeSubmitBody.setSequenceNumber(this.requestAccount(sender).getSequenceNumber());
        //TODO 待优化
        encodeSubmitBody.setMaxGasAmount("4003243");
        encodeSubmitBody.setGasUnitPrice(String.valueOf(this.requestGasEstimate().getGasEstimate()));
        encodeSubmitBody.setExpirationTimestampSecs(String.valueOf(System.currentTimeMillis() / 1000L + 600L));
        encodeSubmitBody.setPayload(transactionPayload);

        return encodeSubmitBody;
    }

}