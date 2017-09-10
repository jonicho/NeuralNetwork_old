package de.jrk.neuralNetwork.train;

import de.jrk.neuralNetwork.NeuralNetwork;

public class NeuralNetworkWithScore {
	private NeuralNetwork network;
	private float score;
	
	public NeuralNetworkWithScore(NeuralNetwork network) {
		this.network = network;
	}
	
	/**
	 * Returns the {@link NeuralNetwork}.
	 * @return the {@link NeuralNetwork}.
	 */
	public NeuralNetwork getNetwork() {
		return network;
	}
	
	/**
	 * Returns the score.
	 * @return the score.
	 */
	public float getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 * @param score the score.
	 */
	public void setScore(float score) {
		this.score = score;
	}
}
