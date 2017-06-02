package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TextFileProcessor extends FileProcessor{
	private String readText;
	
	public TextFileProcessor() {
		
	}
	

	public void saveFile(String text, String filepath) {
		try{
			FileWriter fw = new FileWriter(filepath, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
	
		    out.println(text);

			out.close();
		}catch (Exception e) {
			System.out.println("save file exception");				
		}
	}

	public String readFile(String filepath) {
		readText = "";
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    readText = sb.toString();
			} finally {
			    br.close();
			}
			
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return readText;
		
	}

}
