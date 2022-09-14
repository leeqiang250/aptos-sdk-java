import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.v1.*;
import com.aptos.utils.AptosClient;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AptosClientTest {

    final long ledgerVersion = 29473448L;

    final String host = "https://fullnode.devnet.aptoslabs.com";

    final String account = "0xc73b774dd8ea3ce870a29f20e9f37bc9631198bcf21dc294cc72fea27f212a10";

    AptosClient aptosClient;

    @Before
    public void init() {
        aptosClient = new AptosClient(this.host);
    }

    @Test
    public void testResponseBlocksByVersion() {
        RequestBlocksByVersionQuery requestBlocksByVersionQuery = RequestBlocksByVersionQuery.builder()
                .withTransactions(true)
                .build();

        RequestBlocksByVersion requestBlocksByVersion = RequestBlocksByVersion.builder()
                .ledgerVersion(this.ledgerVersion)
                .query(requestBlocksByVersionQuery)
                .build();

        ResponseBlocksByVersion responseBlocksByVersion = aptosClient.call(requestBlocksByVersion, ResponseBlocksByVersion.class);
        println(responseBlocksByVersion);
    }

    @Test
    public void testRequestAccountResources() {
        RequestLedgerVersionQuery requestLedgerVersionQuery = RequestLedgerVersionQuery.builder()
                .ledgerVersion(this.ledgerVersion)
                .build();

        RequestAccountResources requestAccountResources = RequestAccountResources.builder()
                .account(this.account)
                //.query(requestLedgerVersionQuery)
                .build();

        List<ResponseAccountResource> responseAccountResources = aptosClient.callList(requestAccountResources, ResponseAccountResource.class);
        printlnList(responseAccountResources);
    }

    @Test
    public void testRequestAccountResource() {
        RequestLedgerVersionQuery requestLedgerVersionQuery = RequestLedgerVersionQuery.builder()
                .ledgerVersion(this.ledgerVersion)
                .build();

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(this.account)
                .resourceType("0x1::account::Account")
                //.query(requestLedgerVersionQuery)
                .build();

        ResponseAccountResource responseAccountResource = aptosClient.call(requestAccountResources, ResponseAccountResource.class);
        println(responseAccountResource);
    }


    void println(Serializable serializable) {
        System.out.println("--------------------" + serializable.getClass().getSimpleName());
        System.out.println(serializable);
        System.out.println("----------------------------------------");
    }

    void printlnList(List serializable) {
        System.out.println("--------------------" + serializable.getClass().getSimpleName());
        System.out.println(serializable);
        System.out.println("----------------------------------------");
    }

}