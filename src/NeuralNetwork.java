import java.util.*;

public class NeuralNetwork {
	private ArrayList<InputNeuron> inputNeurons;
	private ArrayList<ArrayList<Neuron>> neurons;

	public NeuralNetwork(int... neuronAmount) {
		neurons = new ArrayList();
		for(int i = 0; i < neuronAmount[0]; i++) {
			neurons.get(0).add(new Neuron());
		}
		neurons.add(new ArrayList());
		for(int i = 1; i < neuronAmount.length; i++) {
			neurons.add(new ArrayList());
			for(int j = 0; j < neuronAmount[i]; j++) {
				neurons.get(i).add(new Neuron());
			}
		}
	}
	
	
}
