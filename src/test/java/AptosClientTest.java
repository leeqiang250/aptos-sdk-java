import com.aptos.request.v1.model.CoinStore;
import com.aptos.request.v1.model.Token;
import com.aptos.request.v1.request.*;
import com.aptos.request.v1.response.ResponseAccountResource;
import com.aptos.request.v1.response.ResponseBlocksByVersion;
import com.aptos.request.v1.response.ResponseToken;
import com.aptos.utils.AptosClient;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class AptosClientTest {

    final long ledgerVersion = 29473448L;

    final String host = "https://fullnode.devnet.aptoslabs.com";

    final String account1 = "0xc73b774dd8ea3ce870a29f20e9f37bc9631198bcf21dc294cc72fea27f212a10";

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
                .account(this.account1)
                .query(requestLedgerVersionQuery)
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
                .account(this.account1)
                .resourceType("0x1::account::Account")
                .query(requestLedgerVersionQuery)
                .build();

        ResponseAccountResource responseAccountResource = aptosClient.call(requestAccountResources, ResponseAccountResource.class);
        println(responseAccountResource);
    }

    @Test
    public void test() {
        CoinStore coinStore = CoinStore.builder()
                .token(Token.APT())
                .build();

        RequestAccountResource requestAccountResources = RequestAccountResource.builder()
                .account(this.account1)
                .resourceType(coinStore.type())
                .build();

        ResponseToken responseToken = aptosClient.call(requestAccountResources, ResponseToken.class);
        println(responseToken);
    }

    @Test
    public void testEstimateGasPrice() {
        long estimateGasPrice = aptosClient.estimateGasPrice();
        println(estimateGasPrice);
    }

    void println(Serializable serializable) {
        System.out.println("--------------------" + serializable.getClass().getSimpleName());
        System.out.println(serializable);
        System.out.println("----------------------------------------");
    }

    @SuppressWarnings(value = {"rawtypes"})
    void printlnList(List serializable) {
        System.out.println("--------------------" + serializable.getClass().getSimpleName());
        System.out.println(serializable);
        System.out.println("----------------------------------------");
    }

}