package de.jrk.neuralNetwork.activationFunctions;

public abstract class ActivationFunction {
	public final static TangensHyperbolicus tangensHyperbolicus = new TangensHyperbolicus();
	
	public abstract double function(double x);
}
