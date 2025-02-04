package pexer1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class PreExercise1 {
    public static void main(String[] args) {
        try


                (
                        ServerSocket server = new ServerSocket(4000);
                        Socket socket = server.accept();


                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(socket.getOutputStream());
                )




        {
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
        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
