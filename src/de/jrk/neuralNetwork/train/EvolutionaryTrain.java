package de.jrk.neuralNetwork.train;

import java.io.InputStream;
import java.io.OutputStream;

import de.jrk.neuralNetwork.NeuralNetwork;

public class EvolutionaryTrain {
	private NeuralNetwork[] networks;
	private float[] scores;
	private int currentNetwork = 0;
	private final int networksPerGen;
	
	public EvolutionaryTrain(int loadLevel, InputStream inputStream, int networksPerGen) {
		this.networksPerGen = networksPerGen;
		
		networks = new NeuralNetwork[this.networksPerGen];
		scores = new float[this.networksPerGen];
		
		networks[0] = new NeuralNetwork(loadLevel, inputStream);
		for (int i = 1; i < networks.length; i++) {
			networks[i] = networks[0].getClone();
			networks[i].mutate();
		}
	}
	
	public float[] step(float[] inputs) {
		return networks[currentNetwork].step(inputs);
	}
	
	public void setScore(float score) {
		scores[currentNetwork] = score;
	}
	
	public void nextNetwork() {
		currentNetwork++;
		if (currentNetwork >= networksPerGen) {
			currentNetwork = 0;
			mutateNetworksFromBestNetwork();
			resetScores();
		}
	}
	
	public void saveBestNetwork(int saveLevel, OutputStream outputStream) {
		networks[getIndexOfBestNetwork()].save(saveLevel, outputStream);
	}
	
	private void resetScores() {
		for (int i = 0; i < scores.length; i++) {
			scores[i] = 0;
		}
	}
	
	private int getIndexOfBestNetwork() {
		int bestNetwork = 0;
		for (int i = 1; i < networks.length; i++) {
			if (scores[i] > scores[bestNetwork]) bestNetwork = i;
		}
		return bestNetwork;
	}

	private void mutateNetworksFromBestNetwork() {
		networks[0] = networks[getIndexOfBestNetwork()];
		for (int i = 1; i < networks.length; i++) {
			networks[i] = networks[0].getClone();
			networks[i].mutate();
		}
	}
}
