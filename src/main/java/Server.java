import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = serverSocket.accept();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

            while (true) {
                String clientMsg = in.readUTF();
                if (clientMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Client has exited the chat.");
                    break;
                }
                System.out.println("Client: " + clientMsg);

                System.out.print("You: ");
                String response = scanner.nextLine();
                out.writeUTF(response);
                out.flush();

                if (response.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
