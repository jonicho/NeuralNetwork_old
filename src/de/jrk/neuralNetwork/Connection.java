package de.jrk.neuralNetwork;

public class Connection {
	private double weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron, double weight) {
		this.weight = weight;
		this.neuron = neuron;
	}
	
	public Connection(Neuron neuron) {
		this(neuron, Math.random() * 2 - 1);
	}
	
	public double getInput() {
		return neuron.getActivationLevel() * weight;
	}
}
