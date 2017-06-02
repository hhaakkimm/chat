package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends ChatFrame{

	private static final long serialVersionUID = 570408157370709070L;

	JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    
	public MainFrame() {
		textField.setEditable(false);
        messageArea.setEditable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
        
        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	if(!textField.getText().trim().equals("")){
            		Main.runnerPrintln(textField.getText());
                	textField.setText("");
            	}
            }
        });
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextArea getMessageArea() {
		return messageArea;
	}

	public void setMessageArea(JTextArea messageArea) {
		this.messageArea = messageArea;
	}

	@Override
	public String getServerAddress() {
		return JOptionPane.showInputDialog(
	            frame,
	            "Enter IP Address of the Server:",
	            "Welcome to the Chatter",
	            JOptionPane.QUESTION_MESSAGE);
	}
	
	@Override
	public String getName() {
		return JOptionPane.showInputDialog(
	            frame,
	            "Choose a screen name:",
	            "Screen name selection",
	            JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void setsetFrameTextFieldEditable(boolean b) {
		textField.setEditable(b);
	}

	@Override
	public void addMessage(String message) {
		messageArea.append(message);
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
        frame.pack();
	}

}
