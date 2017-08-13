package de.jrk.neuralNetwork;

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
}
