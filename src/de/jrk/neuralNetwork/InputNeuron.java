package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.activationFunctions.ActivationFunction;

/**
 * An input neuron. Is used as an input (isn't it obvious?) of a
 * {@link NeuralNetwork}. In contrast to a normal {@link Neuron} it has no
 * {@link Connection}s and no {@link ActivationFunction}.
 * 
 * @author Jonas Keller
 *
 */
public class InputNeuron extends Neuron {
	private float input;

	/**
	 * Constructs a new {@link InputNeuron} with no {@link Connection}s and no
	 * {@link ActivationFunction}.
	 */
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

	/**
	 * {@inheritDoc}<br/>
	 * Does not work with an {@link InputNeuron}.
	 */
	@Override
	public void addConnection(Neuron neuron) {
		throw new UnsupportedOperationException("You can't add a connection to an input neuron!");
	}

	/**
	 * {@inheritDoc}<br/>
	 * Does not work with an {@link InputNeuron}.
	 */
	@Override
	public void calculateNetInput() {
		throw new UnsupportedOperationException("You can't calculate the net input of an input neuron!");
	}

	/**
	 * {@inheritDoc}<br/>
	 * Does not work with an {@link InputNeuron}.
	 */
	@Override
	public void calculateActivationLevel() {
		throw new UnsupportedOperationException("You can't calculate the activation level of an input neuron!");
	}

	/**
	 * {@inheritDoc}<br/>
	 * Does not work with an {@link InputNeuron}.
	 */
	@Override
	public void mutate(float mutationRate) {
		throw new UnsupportedOperationException("You can't mutate an input neuron!");
	}
	
	/**
	 * {@inheritDoc}<br/>
	 * Does not work with an {@link InputNeuron}.
	 */
	@Override
	public void resetActivationLevel() {
		throw new UnsupportedOperationException("You can't reset the activation level of an input neuron!");
	}
}
