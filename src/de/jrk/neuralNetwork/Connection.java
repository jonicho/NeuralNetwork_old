package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.util.Util;

public class Connection {
	protected float weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron, float weight) {
		this.weight = weight;
		this.neuron = neuron;
	}
	
	public Connection(Neuron neuron) {
		this(neuron, 0);
	}

	public Neuron getNeuron() {
		return neuron;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public float getValue() {
		return neuron.getActivationLevel() * weight;
	}
	
	public void mutate(float mutationRate) {
		weight += (Util.random.nextBoolean() ? 1 : -1) * weight * Math.random() * mutationRate;
	}
}
