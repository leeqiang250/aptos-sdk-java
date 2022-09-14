import com.aptos.request.v1.*;
import com.aptos.utils.AptosClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class AptosClientTest {


    final long ledgerVersion = 29473448L;

    final String account = "0xc73b774dd8ea3ce870a29f20e9f37bc9631198bcf21dc294cc72fea27f212a10";

    AptosClient aptosClient;

    @Before
    public void init() {
        aptosClient = new AptosClient("https://fullnode.devnet.aptoslabs.com");
    }

    @Test
    public void testResponseBlocksByVersion() {
        RequestBlocksByVersionQuery requestBlocksByVersionQuery = RequestBlocksByVersionQuery.builder()
                .withTransactions(true)
                .build();

        RequestBlocksByVersion requestBlocksByVersion = RequestBlocksByVersion.builder()
                .ledgerVersion(ledgerVersion)
                .query(requestBlocksByVersionQuery)
                .build();

        ResponseBlocksByVersion responseBlocksByVersion = aptosClient.call(requestBlocksByVersion, ResponseBlocksByVersion.class);
        System.out.println(responseBlocksByVersion);
    }

    @Test
    public void test() {
        RequestLedgerVersionQuery requestLedgerVersionQuery = RequestLedgerVersionQuery.builder()
                .ledgerVersion(ledgerVersion)
                .build();

        RequestAccountResources requestAccountResources = RequestAccountResources.builder()
                .account(account)
                .query(requestLedgerVersionQuery)
                .build();

        List<ResponseAccountResource> responseAccountResources = aptosClient.callList(requestAccountResources, ResponseAccountResource.class);
        System.out.println(responseAccountResources);
    }

}