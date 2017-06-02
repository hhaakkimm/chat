package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import interfaces.IRunner;

public class Runner implements IRunner {
	
	private BufferedReader in;
    private PrintWriter out;
	
	@Override
	public void run() throws UnknownHostException, IOException {
		String serverAddress = Main.getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        try{
	        // Process all messages from server, according to the protocol.
	        while (true) {
	            String line = in.readLine();
	            if (line.startsWith("SUBMITNAME")) {
	                out.println(Main.getName());
	            } else if (line.startsWith("NAMEACCEPTED")) {
	                Main.setFrameTextFieldEditable(true);
	            } else if (line.startsWith("MESSAGE")) {
	                Main.addMesssageToFrame(line.substring(8) + "\n");
	            }
	        }
        }
        finally {
        	socket.close();
        }
	}

	@Override
	public void sendMessage(String message) {
		out.println(message);
	}

}
