package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.util.Util;
import de.jrk.tests.*;

public class Connection {
	protected float weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron, float weight) {
		this.weight = weight;
		this.neuron = neuron;
	}
	
	public Connection(Neuron neuron) {
		this(neuron, (float) Math.random() * 2 - 1);
	}

	public Neuron getNeuron() {
		return neuron;
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
