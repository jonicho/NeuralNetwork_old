package de.jrk.neuralNetwork;
import java.io.*;
import java.util.*;

public class SaveLoad {
	private SaveLoad() {}
	
	public static String[] load(File file) {
		String[] result;
		ArrayList<String> list = new ArrayList();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			list.add(br.readLine());
		} catch (FileNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
		result = new String[list.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	
	public void save(File file, String data) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
		} catch (FileNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
