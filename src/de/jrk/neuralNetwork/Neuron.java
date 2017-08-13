package de.jrk.neuralNetwork;

import java.util.ArrayList;
import de.jrk.neuralNetwork.activationFunctions.ActivationFunction;

public class Neuron {
	protected ArrayList<Connection> connections;
	private float activationLevel;
	private float netInput;
	protected ActivationFunction activationFunction;
	
	public Neuron() {
		connections = new ArrayList<Connection>();
		activationFunction = ActivationFunction.tangensHyperbolicus;
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
	
	public ArrayList<Connection> getConnections() {
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
