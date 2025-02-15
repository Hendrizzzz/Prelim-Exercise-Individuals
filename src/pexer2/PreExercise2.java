package pexer2;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * This needs a client.
 */
public class PreExercise2 {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000))


        {
            while (true) {
                Socket socket = server.accept();
                new ClientHandler(socket).start();
            }
        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
}




class ClientHandler extends Thread {
    private final Socket socket;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());




            int hostCount = 0;
            boolean userWantsMore = true;


            while (userWantsMore) {
                writer.print("Host " + ++hostCount + " - Type IP address/Hostname: ");
                writer.flush();


                String hostName = reader.readLine();
                InetAddress[] addresses = InetAddress.getAllByName(hostName);
                int numberOfHosts = addresses.length;


                writer.println("Number of Hosts/IPs: " + numberOfHosts);
                writer.printf("%-15s %s%n", "Host name", "IP Address");


                for (InetAddress address : addresses)
                    writer.printf("%-15s %s%n", address.getHostName(), address.getHostAddress());


                writer.printf("Search another [y/n]? ");
                writer.flush();
                String choice = reader.readLine();


                if (choice.equals("n"))
                    userWantsMore = false;


                writer.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
