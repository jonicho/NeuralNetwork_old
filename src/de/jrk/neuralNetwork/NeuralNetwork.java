package de.jrk.neuralNetwork;

import java.io.*;
import java.util.*;
import de.jrk.neuralNetwork.util.*;

public class NeuralNetwork {
	private ArrayList<InputNeuron> inputNeurons;
	private ArrayList<Neuron> neurons;
	private ArrayList<Neuron> outputNeurons;

	public NeuralNetwork(File file) {
		this(SaveLoad.load(file));
	}
	
	public NeuralNetwork(String[] data) {
		int[] neuronAmount = Util.convertToIntArray(data[0].split(";"));
		if(neuronAmount.length != 3) {
			throw new RuntimeException("The save data is corrupt!");
		}
		
		for(int i = 0; i < neuronAmount[0]; i++) {
			InputNeuron in = new InputNeuron();
			inputNeurons.add(in);
			neurons.add(in);
		}
		
		for(int i = 0; i < neuronAmount[1]; i++) {
			neurons.add(new Neuron());
		}
		
		for(int i = 0; i < neuronAmount[2]; i++) {
			Neuron n = new Neuron();
			outputNeurons.add(n);
			neurons.add(n);
		}
		
		for(int i = 1; i < data.length; i++) {
			String[] conns = data[i].split(";");
			Neuron neuron = neurons.get(Integer.parseInt(conns[0]));
			int[] neuronsToConn = Util.convertToIntArray(conns[1].split(","));
			for(int k : neuronsToConn) {
				try {
					neuron.addConnection(neurons.get(k));
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
					throw new RuntimeException("The save data is corrupt!");
				}
			}
		}
	}
	
	public double[] step(double[] inputs) {
		double[] outputs = new double[outputNeurons.size()];
		
		for(int i = 0; i < inputNeurons.size(); i++) {
			inputNeurons.get(i).setInput(inputs[i]);
		}
		
		for(Neuron n : neurons) {
			if(!(n instanceof InputNeuron)) {
				n.calculateNetInput();
			}
		}
		
		for(Neuron n : neurons) {
			if(!(n instanceof InputNeuron)) {
				n.calculateActivationLevel();
			}
		}
		
		for(int i = 0; i < outputNeurons.size(); i++) {
			outputs[i] = outputNeurons.get(i).getActivationLevel();
		}
		
		return outputs;
	}
}
