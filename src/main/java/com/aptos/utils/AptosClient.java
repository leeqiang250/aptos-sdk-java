package com.aptos.utils;

import com.aptos.request.v1.model.CoinInfo;
import com.aptos.request.v1.model.CoinStore;
import com.aptos.request.v1.model.Resource;
import com.aptos.request.v1.request.*;
import com.aptos.request.v1.response.*;

import java.util.List;
import java.util.Objects;

/**
 * @author liqiang
 */
public class AptosClient extends AbstractClient {

    public AptosClient(String host) {
        super(host);
    }

    public ResponseNode requestNode() {
        RequestNode requestNode = RequestNode.builder().build();

        return this.call(requestNode, ResponseNode.class);
    }

    public ResponseGasEstimate requestGasEstimate() {
        RequestGasEstimate requestGasEstimate = RequestGasEstimate.builder().build();

        return this.call(requestGasEstimate, ResponseGasEstimate.class);
    }

    public List<ResponseAccountResource> requestAccountResources(String account) {
        return this.requestAccountResources(account, null);
    }

    public List<ResponseAccountResource> requestAccountResources(String account, String ledgerVersion) {
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

        return this.callList(requestAccountResources, ResponseAccountResource.class);
    }

    public ResponseAccountResource requestAccountResource(String account, Resource resource) {
        return this.requestAccountResource(account, resource, null);
    }

    public ResponseAccountResource requestAccountResource(String account, Resource resource, String ledgerVersion) {
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

        return this.call(requestAccountResource, ResponseAccountResource.class);
    }

    public ResponseBlock requestBlockByHeight(String height, boolean withTransactions) {
        RequestBlockQuery requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        RequestBlockByHeight requestBlockByHeight = RequestBlockByHeight.builder()
                .height(height)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByHeight, ResponseBlock.class);
    }

    public ResponseBlock requestBlockByVersion(String ledgerVersion, boolean withTransactions) {
        RequestBlockQuery requestBlockQuery = RequestBlockQuery.builder()
                .withTransactions(withTransactions)
                .build();

        RequestBlockByVersion requestBlockByVersion = RequestBlockByVersion.builder()
                .ledgerVersion(ledgerVersion)
                .query(requestBlockQuery)
                .build();

        return this.call(requestBlockByVersion, ResponseBlock.class);
    }

    public ResponseTransaction requestTransactionByHash(String hash) {
        RequestTransactionByHash requestTransactionByHash = RequestTransactionByHash.builder()
                .hash(hash)
                .build();

        return this.call(requestTransactionByHash, ResponseTransaction.class);
    }

    public ResponseCoinStore requestCoinStore(String account, Resource resource) {
        CoinStore coinStore = CoinStore.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinStore)
                .build();

        return this.call(requestAccountResources, ResponseCoinStore.class);
    }

    public ResponseCoinInfo requestCoinInfo(String account, Resource resource) {
        CoinInfo coinInfo = CoinInfo.of(resource);

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(coinInfo)
                .build();

        return this.call(requestAccountResources, ResponseCoinInfo.class);
    }

    public boolean checkTransaction(String hash) {
        return true;
    }

}