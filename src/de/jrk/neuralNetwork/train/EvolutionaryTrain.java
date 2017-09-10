package de.jrk.neuralNetwork.train;

import de.jrk.neuralNetwork.NeuralNetwork;

/**
 * A training class for a {@link NeuralNetwork}.
 * 
 * @author Jonas Keller
 *
 */
public class EvolutionaryTrain {
	private NeuralNetworkWithScore networkWithScore;
	private NeuralNetworkWithScore networkWithScoreSave;

	public EvolutionaryTrain(NeuralNetwork network) {
		this.networkWithScore = new NeuralNetworkWithScore(network);
	}

	/**
	 * Returns the network.
	 * 
	 * @return the network.
	 */
	public NeuralNetwork getNetwork() {
		return networkWithScore.getNetwork();
	}

	/**
	 * Sets the score for the current {@link NeuralNetwork}.<br/>
	 * It is recommended to set this directly before calling {@code next()}.
	 * 
	 * @param score
	 *            the score.
	 */
	public void setScore(float score) {
		networkWithScore.setScore(score);
	}

	/**
	 * If the mutated {@link NeuralNetwork} was better than the initial one, it is
	 * used as the next initial one. Otherwise the initial one is mutated one more
	 * time.<br/>
	 * Resets all activation levels to keep the same start conditions.<br/>
	 * It is recommended to set the score before calling {@link next()}.
	 */
	public void next() {
		networkWithScore.getNetwork().resetActivatonLevels();
		networkWithScoreSave.getNetwork().resetActivatonLevels();
		if (networkWithScore.getScore() > networkWithScoreSave.getScore()) {
			networkWithScoreSave = networkWithScore;
			networkWithScore.getNetwork().mutate();
		} else {
			networkWithScore = networkWithScoreSave;
			networkWithScore.getNetwork().mutate();
		}
	}
}
