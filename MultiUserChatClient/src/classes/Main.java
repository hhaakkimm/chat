package classes;
import interfaces.IRunner;

public class Main {

    private static ChatFrame mainFrame;
    private static IRunner runner;

    public static void main(String[] args) throws Exception {
		mainFrame = new MainFrame();
		runner = new Runner();
		runner.run();
	}
    
    public static String getServerAddress(){
    	return mainFrame.getServerAddress();
    }
    
    public static String getName(){
	   return mainFrame.getName();
    }	
   
   	public static void setFrameTextFieldEditable(boolean b){
	   mainFrame.setsetFrameTextFieldEditable(b);
   	}
   
   	public static void addMesssageToFrame(String string) {
		mainFrame.addMessage(string);
	}

	public static void runnerPrintln(String message) {
		runner.sendMessage(message);
	}
	
}