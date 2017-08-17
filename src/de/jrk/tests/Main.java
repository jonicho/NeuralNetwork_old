package de.jrk.tests;
import de.jrk.neuralNetwork.NeuralNetwork;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/storage/emulated/0/Git/NeuralNetwork/test.xml");
		NeuralNetwork nn = new NeuralNetwork(2, new FileInputStream(file));
		method(nn);
		nn.save(2, new FileOutputStream(file));
		nn = nn.getClone();
		nn.mutate();
		method(nn);
	}

	private static void method(NeuralNetwork nn) {
		float[] outputs;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			outputs = nn.step(new float[] {0, 0, 0, 0, 0});
			for (int j = 0; j < outputs.length - 1; j++) {
				System.out.print(outputs[j] + "; ");
			}
			System.out.println(outputs[outputs.length - 1]);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}
