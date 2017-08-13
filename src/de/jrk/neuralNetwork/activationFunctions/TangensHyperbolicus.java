package de.jrk.neuralNetwork.activationFunctions;

public class TangensHyperbolicus extends ActivationFunction {

	@Override
	public float function(float x) {
		return (float) Math.tanh(x);
	}

	
}
