package de.jrk.neuralNetwork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import de.jrk.neuralNetwork.util.Util;

public class NeuralNetwork {
	private ArrayList<InputNeuron> inputNeurons;
	private ArrayList<Neuron> neurons;
	private ArrayList<Neuron> outputNeurons;

	public NeuralNetwork(File file) {
		this(FileHandler.load(file));
	}

	public NeuralNetwork(String[] data) {
		int[] neuronAmount = Util.convertToIntArray(data[0].split(";"));
		if (neuronAmount.length != 3) {
			throw new RuntimeException("The save data is corrupt!");
		}

		try {
			inputNeurons = new ArrayList<InputNeuron>();
			neurons = new ArrayList<Neuron>();
			outputNeurons = new ArrayList<Neuron>();

			for (int i = 0; i < neuronAmount[0]; i++) {
				InputNeuron in = new InputNeuron();
				inputNeurons.add(in);
				neurons.add(in);
			}

			for (int i = 0; i < neuronAmount[1]; i++) {
				neurons.add(new Neuron());
			}

			for (int i = 0; i < neuronAmount[2]; i++) {
				Neuron n = new Neuron();
				outputNeurons.add(n);
				neurons.add(n);
			}

			for (int i = 1; i < data.length; i++) {
				String[] conns = data[i].split(";");
				Neuron neuron = neurons.get(Integer.parseInt(conns[0]));
				String[] neuronsToConn = conns[1].split(",");
				for (String k : neuronsToConn) {
					String[] conn = k.split(":");
					int neuronIndex = Integer.parseInt(conn[0]);
					if (conn.length == 1) neuron.addConnection(neurons.get(neuronIndex));
					else {
						double weight = Double.parseDouble(conn[1]);
						neuron.addConnection(neurons.get(neuronIndex), weight);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("The save data is corrupt!");
		}
	}

	public double[] step(double[] inputs) {
		double[] outputs = new double[outputNeurons.size()];

		for (int i = 0; i < inputNeurons.size(); i++) {
			inputNeurons.get(i).setInput(inputs[i]);
		}

		for (Neuron n : neurons) {
			if (!(n instanceof InputNeuron)) {
				n.calculateNetInput();
			}
		}

		for (Neuron n : neurons) {
			if (!(n instanceof InputNeuron)) {
				n.calculateActivationLevel();
			}
		}

		for (int i = 0; i < outputNeurons.size(); i++) {
			outputs[i] = outputNeurons.get(i).getActivationLevel();
		}

		return outputs;
	}

	private int getIndex(Neuron neuron) {
		for (int i = 0; i < neurons.size(); i++) {
			if (neurons.get(i) == neuron) {
				return i;
			}
		}
		return -1;
	}

	public void save(File file, boolean saveWeights) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(inputNeurons.size() + ";" + 
					 (neurons.size() - (inputNeurons.size() + outputNeurons.size())) + ";" + 
					 outputNeurons.size());
			bw.newLine();
			for (int i = inputNeurons.size(); i < neurons.size(); i++) {
				String data = "";
				Neuron n = neurons.get(i);
				data += i + ";";
				ArrayList<Connection> connections = n.getConnections();
				for (Connection c : connections) {
					data += getIndex(c.getNeuron()) + (saveWeights ? ":" + c.getWeight() : "") + ",";
				}
				bw.write(data);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}