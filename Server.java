import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket sc = null;
        Socket scc = null;
        BufferedReader bfr = null;
        DataOutputStream dout = null;
        BufferedReader inputFromTerminal = null;
        try {
            /* Bind ServerSocket to 50001 port on localhost.
            By default it accepts maximum 50 connection in queue. Any connection request later to that
            will receive connection refused error, however you can modify this limit as required
            It has one more constructor definition which accept an IP address as well.
            That is useful when you have server machine running with multiple network hosts.
            */
            sc = new ServerSocket(50001);

            //Wait for a client connection
            scc = sc.accept();
            String line = "";
            //Go into infinite loop unless client ask to quit
            while(true){
                bfr = new BufferedReader(new InputStreamReader(scc.getInputStream()));
                String clientdata = bfr.readLine();
                //If message from client is quit then exit the loop
                if (clientdata.equals("end")) break;
                System.out.println("Client: " + clientdata);
                dout = new DataOutputStream(scc.getOutputStream());
                inputFromTerminal  = new BufferedReader(new InputStreamReader(System.in));
                line = inputFromTerminal.readLine();
                dout.writeBytes(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Another way of handling IOException instead of throws
            try {
                sc.close();
                scc.close();
                bfr.close();
                dout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
