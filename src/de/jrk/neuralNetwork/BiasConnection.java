package de.jrk.neuralNetwork;

public class BiasConnection extends Connection {
	public BiasConnection() {
		super(null);
	}
	
	public BiasConnection(float weigth) {
		super(null, weigth);
	}

	@Override
	public float getValue() {
		return super.weight;
	}
}
