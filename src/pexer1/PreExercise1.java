package pexer1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class PreExercise1 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        try


//                (
//                        ServerSocket server = new ServerSocket(4000);
//                        Socket socket = server.accept();
//
//
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        PrintWriter System.out = new PrintWriter(socket.getOutputStream());
//                )




        {
            int hostCount = 0;
            boolean userWantsMore = true;


            while (userWantsMore) {
                System.out.print("Host " + ++hostCount + " - Type IP address/Hostname: ");
                System.out.flush();


                String hostName = reader.readLine();
                InetAddress[] addresses = InetAddress.getAllByName(hostName);
                int numberOfHosts = addresses.length;


                System.out.println("Number of Hosts/IPs: " + numberOfHosts);
                System.out.printf("%-15s %s%n", "Host name", "IP Address");


                for (InetAddress address : addresses)
                    System.out.printf("%-15s %s%n", address.getHostName(), address.getHostAddress());


                System.out.printf("Search another [y/n]? ");
                System.out.flush();
                String choice = reader.readLine();


                if (choice.equals("n"))
                    userWantsMore = false;


                System.out.println();
            }
//            // call a mock client ..
//            Client.start();
        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

