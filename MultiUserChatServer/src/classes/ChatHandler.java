package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatHandler extends Handler{
	
	private static final String CHAT_FILE_PATH = "chatHistory.txt";
	
	private String name;
    private BufferedReader in;
    private PrintWriter out;
    
    private Socket socket;
    private NamesWritersContainer container;
    
    private FileProcessor fileProcessor;
    
    public ChatHandler(Socket socket, NamesWritersContainer container) {
        this.socket = socket;
        this.container = container;
        fileProcessor = new TextFileProcessor();
    }

	@Override
	public void run() {
		
		try {

            // Create character streams for the socket.
            in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Request a name from this client.  Keep requesting until
            // a name is submitted that is not already used.  Note that
            // checking for the existence of a name and adding the name
            // must be done while locking the set of names.
            boolean nameAccepted = false;
            while (!nameAccepted) {
                out.println("SUBMITNAME");
                name = in.readLine();
                if (name == null) {
                    return;
                }
                
                nameAccepted = container.addName(name);

            }

            // Now that a successful name has been chosen, add the
            // socket's print writer to the set of all writers so
            // this client can receive broadcast messages.
            out.println("NAMEACCEPTED");
            
            String fullChat = fileProcessor.readFile(CHAT_FILE_PATH);
            
            out.println(fullChat);
            
            container.addWriter(out);

            // Accept messages from this client and broadcast them.
            // Ignore other clients that cannot be broadcasted to.
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                
                String message = "MESSAGE " + name + ":\t" + input;
                
                fileProcessor.saveFile(message, CHAT_FILE_PATH);
                container.writeToAll(message);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // This client is going down!  Remove its name and its print
            // writer from the sets, and close its socket.
            if (name != null) {
                container.removeName(name);
            }
            if (out != null) {
                container.removeWriter(out);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
	}

}
