package classes;

import javax.swing.JFrame;

import interfaces.IFrameNameGetter;
import interfaces.IFrameServerGetter;

public abstract class ChatFrame extends JFrame implements IFrameNameGetter, IFrameServerGetter {

	private static final long serialVersionUID = 1L;

	public abstract void setsetFrameTextFieldEditable(boolean b);

	public abstract void addMessage(String string);
}
