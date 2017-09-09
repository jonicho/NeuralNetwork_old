package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.util.Util;

/**
 * A connection that connects one {@link Neuron} to another with a weight.
 * 
 * @author Jonas Keller
 *
 */
public class Connection {
	protected float weight;
	private Neuron neuron;

	/**
	 * Constructs a new {@link Connection} with the given {@link Neuron} and weight.
	 * 
	 * @param neuron
	 *            the {@link Neuron}.
	 * @param weight
	 *            the weight.
	 */
	public Connection(Neuron neuron, float weight) {
		this.weight = weight;
		this.neuron = neuron;
	}

	/**
	 * Constructs a new {@link Connection} with the given {@link Neuron} and an
	 * initial weight of 0.
	 * 
	 * @param neuron
	 *            the {@link Neuron}.
	 */
	public Connection(Neuron neuron) {
		this(neuron, 0);
	}

	/**
	 * Returns the {@link Neuron}.
	 * 
	 * @return the {@link Neuron}.
	 */
	public Neuron getNeuron() {
		return neuron;
	}

	/**
	 * Sets the weight.
	 * 
	 * @param weight
	 *            the weight.
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Returns the weight.
	 * 
	 * @return the weight.
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Returns the activation level of the {@link Neuron} multiplied by the weight.
	 * 
	 * @return the activation level of the {@link Neuron} multiplied by the weight.
	 */
	public float getValue() {
		return neuron.getActivationLevel() * weight;
	}

	/**
	 * Mutates the weight with the given mutation rate by adding a random number
	 * between {@code weight * mutationRate} and {@code -weight * mutationRate} to
	 * the weight.
	 * 
	 * @param mutationRate
	 *            the mutation rate
	 */
	public void mutate(float mutationRate) {
		weight += (Util.random.nextBoolean() ? 1 : -1) * weight * Math.random() * mutationRate;
	}
}
