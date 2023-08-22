import org.voltdb.client.Client;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;

public class MyVoltDBClient {
    public static void main(String[] args) throws Exception {
        String hostname = "localhost";
        String username = "";
        String password = "";
        Client myApp;
        myApp = ClientFactory.createClient();
        myApp.createConnection(hostname);

        ClientResponse response = myApp.callProcedure("@AdHoc", "SELECT * FROM SUBSCRIBER");
        System.out.println(response.getResults()[0]);
    }
}