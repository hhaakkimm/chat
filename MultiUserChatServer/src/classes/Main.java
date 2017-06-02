package classes;
import java.net.ServerSocket;

public class Main {

    private static final int PORT = 9001;
    
    private static NamesWritersContainer container = new NamesWritersContainer();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        try {
            while (true) {
                new ChatHandler(listener.accept(), container).start();
            }
        } finally {
            listener.close();
        }
    }

}