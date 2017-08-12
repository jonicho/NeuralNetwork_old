package de.jrk.neuralNetwork;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FileHandler {
	private FileHandler() {}
	
	public static String[] load(File file) {
		String[] result;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			result = (String[]) br.lines().toArray();
			br.close();
		} catch (FileNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public void save(File file, String[] data) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < data.length; i++) {
				bw.write(data[i]);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
