package de.jrk.neuralNetwork;

public class BiasConnection extends Connection {
	public BiasConnection() {
		super(null);
	}
	
	public BiasConnection(double weigth) {
		super(null, weigth);
	}

	@Override
	public double getValue() {
		return super.weight;
	}
}
