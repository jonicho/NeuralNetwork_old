package de.jrk.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import de.jrk.neuralNetwork.NeuralNetwork;
import de.jrk.neuralNetwork.train.EvolutionaryTrain;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("test.xml");
		NeuralNetwork nn = new NeuralNetwork(NeuralNetwork.LEVEL_WEIGHTS, new FileInputStream(file));
		EvolutionaryTrain train = new EvolutionaryTrain(nn);
		float random;
		float x;
		for (int i = 0; i < 100; i++) {
			float score = 0;
			random = (int) (Math.random() * 100) - 50;
			System.out.println(random);
			x = train.getNetwork().step(random)[0];
			System.out.println(x);
			random *= 33;
			float diff = Math.abs(random - x);
			diff /= random;
			score = 1 / diff;
			System.out.println(score);
			train.setScore(score);
			train.next();
		}
		train.getNetwork().save(NeuralNetwork.LEVEL_WEIGHTS, new FileOutputStream(file));
	}
}
