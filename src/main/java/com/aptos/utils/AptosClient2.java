package com.aptos.utils;

import com.aptos.request.v1.model.Resource;
import com.aptos.request.v1.request.RequestAccountResource;
import com.aptos.request.v1.request.RequestAccountResources;
import com.aptos.request.v1.request.RequestLedgerVersionQuery;
import com.aptos.request.v1.request.RequestTransactionByHash;
import com.aptos.request.v1.response.ResponseAccountResource;
import com.aptos.request.v1.response.ResponseTransaction;

import java.util.List;
import java.util.Objects;

public class AptosClient2 extends AptosClient {

    public AptosClient2(String host) {
        super(host);
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

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(account)
                .resource(resource)
                .query(requestLedgerVersionQuery)
                .build();

        return this.call(requestAccountResources, ResponseAccountResource.class);
    }

    public ResponseTransaction requestTransactionByHash(String hash) {
        RequestTransactionByHash requestTransactionByHash = RequestTransactionByHash.builder()
                .hash(hash)
                .build();

        return this.call(requestTransactionByHash, ResponseTransaction.class);
    }

//    public ResponseToken requestToken(String account, Token token) {
//        CoinStore coinStore = CoinStore.builder()
//                .token(token)
//                .build();
//
//        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
//                .account(account)
//                .resourceType(coinStore.type())
//                .build();
//
//        return this.call(requestAccountResources, ResponseToken.class);
//    }


}