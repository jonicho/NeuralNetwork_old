package de.jrk.neuralNetwork.train;

import java.io.InputStream;
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
		mutateNetworksFromFirstNetwork();
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
			putBestNetworkToBeginning();
			mutateNetworksFromFirstNetwork();
		}
	}
	
	private void putBestNetworkToBeginning() {
		int bestNetwork = 0;
		for (int i = 1; i < networks.length; i++) {
			//TODO
		}
	}

	private void mutateNetworksFromFirstNetwork() {
		for (int i = 1; i < networks.length; i++) {
			networks[i] = networks[0].getClone();
			networks[i].mutate();
		}
	}
}
