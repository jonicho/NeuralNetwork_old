package de.jrk.neuralNetwork;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.util.stream.Stream;

public class FileHandler {
	private FileHandler() {}
	
	public static String[] load(File file) {
		String[] result;
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				list.add(br.readLine());
			}
			result = list.toArray(new String[]{});
			br.close();
		} catch (FileNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public static void save(File file, String[] data) {
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
