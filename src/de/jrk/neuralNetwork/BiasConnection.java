package de.jrk.neuralNetwork;

/**
 * A bias connection. In contrast to a {@link Connection} it has no
 * {@link Neuron} but only a weight.
 * 
 * @author Jonas Keller
 *
 */
public class BiasConnection extends Connection {
	/**
	 * Constructs a new {@link BiasConnection} with an initial weight of 0.
	 */
	public BiasConnection() {
		super(null);
	}

	/**
	 * Constructs a new {@link BiasConnection} with the given weight 0.
	 * 
	 * @param weigth
	 *            the weight.
	 */
	public BiasConnection(float weigth) {
		super(null, weigth);
	}

	/**
	 * Returns 1 multiplied by the weight.
	 * 
	 * @return 1 multiplied by the weight.
	 */
	@Override
	public float getValue() {
		return super.weight;
	}
}
