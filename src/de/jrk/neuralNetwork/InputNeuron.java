package de.jrk.neuralNetwork;

public class InputNeuron extends Neuron {
	private float input;
	
	public InputNeuron() {
		super.connections = null;
		super.activationFunction = null;
	}
	
	@Override
	public float getActivationLevel() {
		return input;
	}
	
	public void setInput(float input) {
		this.input = input;
	}

	@Override
	public void addConnection(Neuron neuron) {
		throw new UnsupportedOperationException(
			"You can't add a connection to an input neuron!"
		);
	}

	@Override
	public void calculateNetInput() {
		throw new UnsupportedOperationException(
			"You can't calculate the net input of an input neuron!"
		);
	}

	@Override
	public void calculateActivationLevel() {
		throw new UnsupportedOperationException(
			"You can't calculate the activation level of an input neuron!"
		);
	}

	@Override
	public void mutate(float mutationRate) {
		throw new UnsupportedOperationException(
			"You can't mutate an input neuron!"
		);
	}
}
