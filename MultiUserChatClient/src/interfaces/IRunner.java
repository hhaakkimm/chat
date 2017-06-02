package interfaces;

import java.io.IOException;
import java.net.UnknownHostException;

public interface IRunner {
	public void run() throws UnknownHostException, IOException;

	public void sendMessage(String message);
}
