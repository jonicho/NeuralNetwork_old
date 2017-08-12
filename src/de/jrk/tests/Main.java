package de.jrk.tests;
import de.jrk.neuralNetwork.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		File file = new File("/storage/emulated/0/Git/NeuralNetwork/src/de/jrk/tests/test.nns");
		NeuralNetwork nn = new NeuralNetwork(file);
		nn.save(file, true);
		double[] outputs;
		for(int i = 0; i < 100; i++) {
			outputs = nn.step(new double[] {2, 2, 2, 2, 2});
			for(int j = 0; j < outputs.length - 1; j++) {
				System.out.print(outputs[j] + "; ");
			}
			System.out.println(outputs[outputs.length - 1]);
		}
	}
}