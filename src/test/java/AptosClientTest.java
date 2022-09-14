import com.aptos.request.v1.RequestBlocksByVersion;
import com.aptos.request.v1.RequestBlocksByVersionQuery;
import com.aptos.request.v1.ResponseBlocksByVersion;
import com.aptos.utils.AptosClient;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

public class AptosClientTest {


    private AptosClient aptosClient;

    @Before
    public void setUp() throws Exception {
        aptosClient = new AptosClient("https://fullnode.devnet.aptoslabs.com");
    }

    @Test
    public void testGetState() {
        RequestBlocksByVersionQuery requestBlocksByVersionQuery = RequestBlocksByVersionQuery.builder()
                .withTransactions(true)
                .build();

        RequestBlocksByVersion requestBlocksByVersion = new RequestBlocksByVersion();
        requestBlocksByVersion.setLedgerVersion(29473448L);
        requestBlocksByVersion.setQuery(requestBlocksByVersionQuery);

        ResponseBlocksByVersion responseBlocksByVersion = aptosClient.call(requestBlocksByVersion, ResponseBlocksByVersion.class);
        System.out.println(responseBlocksByVersion);
    }

}