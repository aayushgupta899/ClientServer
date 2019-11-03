import java.net.*;
import java.io.*;
public class Client {
    public static void main(String[] args) throws IOException {
        Socket sc = null; // Need to initialize as we are closing in finally block
        BufferedReader bf = null;
        DataOutputStream dout = null;
        BufferedReader  inputFromTerminal   = null;
        try{
            //Binding socket to 50001 port number on localhost
            sc = new Socket("localhost",50001);

            /* DataOutputStream: Writes any primitive type to an output stream
            It accepts any OutputStream object as constructor parameter to write on.
            getOutputStream method of Socket class will return OutputStream object
            attached to that socket instance
            */
            String line = "";
            while(true)
            {
                inputFromTerminal  = new BufferedReader(new InputStreamReader(System.in));
                line = inputFromTerminal.readLine();
                dout = new DataOutputStream(sc.getOutputStream());
                dout.writeBytes(line + "\n");
                if(line.equalsIgnoreCase("end"))
                    break;
                bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                System.out.println("Server: " + bf.readLine());
            }

            //Sends data to the other end.

            /*getInputStream method of Socket class will return InputStream attached to
            that socket instance.
            InputStreamReader is bridge to bytes stream to character stream.
            Now as that InputStreamReader can read each character, we need a buffer to hold all of them
            hence use BufferedReader for that purpose
             */
            //bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            //Print data received from other end on local console
            //System.out.println("Message Server: " + bf.readLine());

            //Quit server
            //dout.writeBytes("quit");

            /*We can use BufferedWriter as well to write data, following code snippet demonstrate that:

            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
            bfw.write("Hellod");
            bfw.newLine();
            bfw.flush();
             */
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            /* Close all IO streams
            Following both statements may throw an IOException, thats why throws statement
            of main method has been included
            */
            bf.close();
            sc.close();
            dout.close();
        }
    }



}
