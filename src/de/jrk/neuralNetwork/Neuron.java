package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.activationFunctions.ActivationFunction;
import de.jrk.neuralNetwork.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * A neuron with {@link Connection}s, an activation level and an
 * {@link ActivationFunction}.
 * 
 * @author Jonas Keller
 *
 */
public class Neuron {
	protected List<Connection> connections;
	private float activationLevel;
	private float netInput;
	protected ActivationFunction activationFunction;

	/**
	 * Constructs an empty {@link Neuron}.
	 */
	public Neuron() {
		connections = new ArrayList<Connection>();
	}

	/**
	 * Sets the {@link ActivationFunction} by its string representation.
	 * 
	 * @param activationFunction
	 *            the string representation of the {@link ActivationFunction}.
	 */
	public void setActivationFunction(String activationFunction) {
		setActivationFunction(ActivationFunction.getActivationFunction(activationFunction));
	}

	/**
	 * Sets the {@link ActivationFunction}.
	 * 
	 * @param activationFunction
	 *            the {@link ActivationFunction}.
	 */
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	/**
	 * Returns the {@link ActivationFunction}.
	 * 
	 * @return the {@link ActivationFunction}.
	 */
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * Sets the activation level.
	 * 
	 * @param activationLevel
	 *            the activation level.
	 */
	public void setActivationLevel(float activationLevel) {
		this.activationLevel = activationLevel;
	}

	
	/**
	 * Returns a list of all {@link Connection}s of this {@link Neuron}.
	 * 
	 * @return a list of all {@link Connection}s of this {@link Neuron}.
	 */
	public List<Connection> getConnections() {
		return connections;
	}

	/**
	 * Returns the activation level of this {@link Neuron}.
	 * 
	 * @return the activation level of this {@link Neuron}.
	 */
	public float getActivationLevel() {
		return activationLevel;
	}
	
	/**
	 * Adds a {@link Connection} with the given {@link Neuron}. The weight will be
	 * 0.
	 * 
	 * @param neuron
	 *            the {@link Neuron} to connect to.
	 */
	public void addConnection(Neuron neuron) {
		connections.add(new Connection(neuron));
	}

	/**
	 * Adds a {@link Connection} with the given {@link Neuron} and weight.
	 * 
	 * @param neuron
	 *            the {@link Neuron} to connect to.
	 * @param weight
	 *            the weight of the {@link Connection}.
	 */
	public void addConnection(Neuron neuron, float weight) {
		connections.add(new Connection(neuron, weight));
	}

	/**
	 * Adds a {@link BiasConnection} with a weight of 0.
	 */
	public void addBiasConnection() {
		connections.add(new BiasConnection());
	}

	/**
	 * Adds a {@link BiasConnection} with the given weight.
	 * 
	 * @param weight
	 *            the weight.
	 */
	public void addBiasConnection(float weight) {
		connections.add(new BiasConnection(weight));
	}

	/**
	 * Initializes the weights of all {@link Connection}s. The weight is randomly
	 * chosen from -1/sqrt(inputAmount) to 1/sqrt(inputAmount).
	 */
	public void initWeights() {
		float x = (float) Math.sqrt(connections.size());
		for (Connection c : connections) {
			c.setWeight((Util.random.nextFloat() * 2 - 1) / x);
		}
	}

	/**
	 * Calculates the net input.
	 */
	public void calculateNetInput() {
		netInput = 0;
		for (Connection c : connections) {
			netInput += c.getValue();
		}
	}

	/**
	 * Calculates the activation level.
	 */
	public void calculateActivationLevel() {
		activationLevel = activationFunction.function(netInput);
	}

	/**
	 * Mutates the weights of all {@link Connection}s of this {@link Neuron} with
	 * the given mutation rate.
	 * 
	 * @param mutationRate
	 *            the mutation rate.
	 */
	public void mutate(float mutationRate) {
		for (Connection c : connections) {
			c.mutate(mutationRate);
		}
	}
	
	/**
	 * Resets the activation level to 0.
	 */
	public void resetActivationLevel() {
		activationLevel = 0;
	}
}
