package de.jrk.neuralNetwork;

public class Connection {
	protected double weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron, double weight) {
		this.weight = weight;
		this.neuron = neuron;
	}
	
	public Connection(Neuron neuron) {
		this(neuron, Math.random() * 2 - 1);
	}

	public Neuron getNeuron() {
		return neuron;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getValue() {
		return neuron.getActivationLevel() * weight;
	}
}
