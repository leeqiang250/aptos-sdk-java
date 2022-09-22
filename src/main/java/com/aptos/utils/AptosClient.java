package com.aptos.utils;

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

import java.util.List;
import java.util.Objects;

/**
 * @author liqiang
 */
public class AptosClient extends AbstractClient {

    public AptosClient(String host) {
        super(host);
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

    public List<Resource> requestAccountResources(String account, String ledgerVersion) {
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

    public Resource requestAccountResource(String account, com.aptos.request.v1.model.Resource resource) {
        return this.requestAccountResource(account, resource, null);
    }

    public Resource requestAccountResource(String account, com.aptos.request.v1.model.Resource resource, String ledgerVersion) {
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

    public Block requestBlockByHeight(String height, boolean withTransactions) {
        RequestBlockQuery requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        RequestBlockByHeight requestBlockByHeight = RequestBlockByHeight.builder()
                .height(height)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByHeight, Block.class);
    }

    public Block requestBlockByVersion(String ledgerVersion, boolean withTransactions) {
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

    public CoinStore requestCoinStore(String account, com.aptos.request.v1.model.Resource resource) {
        com.aptos.request.v1.model.CoinStore coinStore = com.aptos.request.v1.model.CoinStore.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinStore)
                .build();

        return this.call(requestAccountResources, CoinStore.class);
    }

    public CoinInfo requestCoinInfo(String account, com.aptos.request.v1.model.Resource resource) {
        com.aptos.request.v1.model.CoinInfo coinInfo = com.aptos.request.v1.model.CoinInfo.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinInfo)
                .build();

        return this.call(requestAccountResources, CoinInfo.class);
    }

    public CollectionData requestTableCollectionData(String handle, String key) {
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

    public boolean checkTransaction(String hash) {
        return true;
    }

}