package de.jrk.neuralNetwork;

import java.util.*;
import de.jrk.neuralNetwork.activationFunctions.*;
public class Neuron {
	private ArrayList<Connection> connections;
	private double activationLevel;
	private double netInput;
	private ActivationFunction activationFunction;
	
	public Neuron() {
		connections = new ArrayList();
		activationFunction = ActivationFunction.tangensHyperbolicus;
	}
	
	public void addConnection(Neuron neuron) {
		connections.add(new Connection(neuron));
	}
	
	public double getActivationLevel() {
		return activationLevel;
	}
	
	public void calculateNetInput() {
		netInput = 0;
		for(Connection c : connections) {
			netInput += c.getInput();
		}
	}
	
	public void calculateActivationLevel() {
		activationLevel = activationFunction.function(netInput);
	}
}
