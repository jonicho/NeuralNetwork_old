package de.jrk.tests;
import de.jrk.neuralNetwork.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		File file = new File("/storage/emulated/0/Git/NeuralNetwork/src/de/jrk/tests/test.nns");
		NeuralNetwork nn = new NeuralNetwork(file);
		FileHandler.save(file, nn.save(true));
		float[] outputs;
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			outputs = nn.step(new float[] {0, 0, 0, 0, 0});
			for(int j = 0; j < outputs.length - 1; j++) {
				System.out.print(outputs[j] + "; ");
			}
			System.out.println(outputs[outputs.length - 1]);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}
