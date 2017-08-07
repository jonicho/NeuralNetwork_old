import java.util.*;
import activationFunktions.*;
public class Neuron {
	private ArrayList<Connection> connections;
	private double activationLevel;
	private double netInput;
	private ActivationFunction activationFunction;
	
	public Neuron() {
		connections = new ArrayList();
		activationFunction = ActivationFunction.tangensHyperbolicus;
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
		activationLevel = activationFunction.function(activationLevel);
	}
}
