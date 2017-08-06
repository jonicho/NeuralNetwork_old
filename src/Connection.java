public class Connection {
	private double weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron, double weight) {
		this.weight = weight;
		this.neuron = neuron;
	}
	
	public Connection(Neuron neuron) {
		this(neuron, 1);//TODO random weight
	}
}
