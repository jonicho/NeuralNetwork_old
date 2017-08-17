package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.activationFunctions.ActivationFunction;
import java.util.ArrayList;
import java.util.List;

public class Neuron {
	protected List<Connection> connections;
	private float activationLevel;
	private float netInput;
	protected ActivationFunction activationFunction;
	
	public Neuron() {
		connections = new ArrayList<Connection>();
	}
	
	public void setActivationFunction(String activationFunction) {
		setActivationFunction(ActivationFunction.getActivationFunction(activationFunction));
	}
	
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}
	
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}
	
	public void setActivationLevel(float activationLevel) {
		this.activationLevel = activationLevel;
	}
	
	public void addConnection(Neuron neuron) {
		connections.add(new Connection(neuron));
	}
	
	public void addConnection(Neuron neuron, float weight) {
		connections.add(new Connection(neuron, weight));
	}
	
	public void addBiasConnection() {
		connections.add(new BiasConnection());
	}
	
	public void addBiasConnection(float weight) {
		connections.add(new BiasConnection(weight));
	}
	
	public List<Connection> getConnections() {
		return connections;
	}
	
	public float getActivationLevel() {
		return activationLevel;
	}
	
	public void calculateNetInput() {
		netInput = 0;
		for(Connection c : connections) {
			netInput += c.getValue();
		}
	}
	
	public void calculateActivationLevel() {
		activationLevel = activationFunction.function(netInput);
	}
	
	public void mutate(float mutationRate) {
		for(Connection c : connections) {
			c.mutate(mutationRate);
		}
	}
}
