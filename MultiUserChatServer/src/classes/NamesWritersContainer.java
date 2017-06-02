package classes;

import java.io.PrintWriter;
import java.util.HashSet;

public class NamesWritersContainer {
	private HashSet<PrintWriter> writers;
    private HashSet<String> names;
    
    public NamesWritersContainer() {
		writers = new HashSet<>();
		names = new HashSet<>();
	}
	public NamesWritersContainer(HashSet<PrintWriter> writers, HashSet<String> names) {
		super();
		this.writers = writers;
		this.names = names;
	}
	public HashSet<PrintWriter> getWriters() {
		return writers;
	}
	public void setWriters(HashSet<PrintWriter> writers) {
		this.writers = writers;
	}
	public HashSet<String> getNames() {
		return names;
	}
	public void setNames(HashSet<String> names) {
		this.names = names;
	}
	
	public boolean addName(String name){
		synchronized (names) {
            if (!names.contains(name)) {
                names.add(name);
                return true;
            } else {
            	return false;
            }
        }
		
	}
	
	public void removeName(String name){
		names.remove(name);
	}
	
	public void addWriter(PrintWriter writer){
		writers.add(writer);
	}
	
	public void removeWriter(PrintWriter writer){
		writers.remove(writer);
	}
	public void writeToAll(String input) {
		for (PrintWriter writer : writers) {
            writer.println(input);
        }
	}

}
