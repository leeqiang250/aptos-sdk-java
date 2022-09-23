import com.aptos.request.v1.model.CoinStore;
import com.aptos.request.v1.model.Resource;
import com.aptos.request.v1.model.Struct;
import com.aptos.request.v1.model.TransactionPayload;
import com.aptos.AptosClient;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class AptosClientTest {

    final String host = "https://fullnode.devnet.aptoslabs.com";

    //final String account1 = "0xc73b774dd8ea3ce870a29f20e9f37bc9631198bcf21dc294cc72fea27f212a10";

    final String account1 = "0xdddae7d9bd64a8b7200a3ec8a95386e416257236a3e673cc48a159535d418f53";

    final String height = "3639613";

    final String ledgerVersion = "11987459";

    final String tx = "0xb046e9791f0420121741f710ee2da4db9db685ed852f141bb75001e0e6e531d2";

    AptosClient aptosClient;

    @Before
    public void init() {
        aptosClient = new AptosClient(this.host);
    }

    @Test
    public void requestNode() {
        println(aptosClient.requestNode());
    }

    @Test
    public void requestEstimateGasPrice() {
        println(aptosClient.requestGasEstimate());
    }

    @Test
    public void requestAccountResources() {
        printlnList(aptosClient.requestAccountResources(this.account1));
    }

    @Test
    public void requestAccountResource() {
        println(aptosClient.requestAccountResource(this.account1, Struct.account()));
        println(aptosClient.requestAccountResource(this.account1, CoinStore.of(Struct.APT())));
    }

    @Test
    public void requestBlockByHeight() {
        println(aptosClient.requestBlockByHeight(this.height, true));
    }

    @Test
    public void requestBlockByVersion() {
        println(aptosClient.requestBlockByVersion(this.ledgerVersion, true));
    }

    @Test
    public void requestTransactionByHash() {
        println(aptosClient.requestTransactionByHash(this.tx));
    }

    @Test
    public void requestCoinStore() {
        println(aptosClient.requestCoinStore(this.account1, Struct.APT()));
    }

    @Test
    public void requestCoinInfo() {
        Resource token = Resource.builder()
                .moduleAddress("0xdddae7d9bd64a8b7200a3ec8a95386e416257236a3e673cc48a159535d418f53")
                .moduleName("good_token")
                .resourceName("GoodToken")
                .build();

        println(aptosClient.requestCoinInfo(this.account1, token));
    }

    @Test
    public void resourceTag() {
        Resource resource0 = Resource.builder()
                .moduleAddress("0")
                .moduleName("0")
                .resourceName("0")
                .build();

        Resource resource1 = Resource.builder()
                .moduleAddress("1")
                .moduleName("1")
                .resourceName("1")
                .resources(List.of(resource0))
                .build();

        Resource resource2 = Resource.builder()
                .moduleAddress("2")
                .moduleName("2")
                .resourceName("2")
                .resources(List.of(resource1))
                .build();


        Resource resource3 = Resource.builder()
                .moduleAddress("3")
                .moduleName("3")
                .resourceName("3")
                .resources(List.of(resource2))
                .build();

        println(resource3.resourceTag());
    }


    @Test
    public void requestTableCollectionData() {
        println(aptosClient.requestTableCollectionData("0xbe512fec6c98dade8d0ce8dd9abd1c9a3427df6627c788efa3ee81b9d10ebd44", "0x69616d6e616d65"));
    }

    @Test
    public void requestSubmitTransaction() {
        TransactionPayload transactionPayload = TransactionPayload.builder()
                .type(TransactionPayload.ENTRY_FUNCTION_PAYLOAD)
                .function("0x2b490841c230a31fe012f3b2a3f3d146316be073e599eb7d7e5074838073ef14::message::set_message")
                .arguments(List.of("hello"))
                .typeArguments(List.of())
                .build();

        println(aptosClient.requestSubmitTransaction(
                "0x2b490841c230a31fe012f3b2a3f3d146316be073e599eb7d7e5074838073ef14",
                transactionPayload));
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