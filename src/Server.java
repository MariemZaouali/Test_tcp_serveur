
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// SERVER : Single Server                       
// TIPE : One-Way Communication (Client to Server)
// DESCRIPTION : 
// A simple server that will accept a single client connection and display everything the client says on the screen. 
// If the client user types "exit", the client and the server will both quit.
public class Server {

    private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Waiting for client...");

            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            bis = new BufferedInputStream(socket.getInputStream());
            dis = new DataInputStream(bis);

            while (true) {
                try {
                    String messageFromClient = dis.readUTF();
                    if (messageFromClient.equals("exit")) {
                        break;
                    }
                    System.out.println("Client [" + socket.getRemoteSocketAddress() + "] : " + messageFromClient);
                } catch (IOException e) {
                    break;
                }
            }
            dis.close();
            socket.close();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnect from server...");
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}