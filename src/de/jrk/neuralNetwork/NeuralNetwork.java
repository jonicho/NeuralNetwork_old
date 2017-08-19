package de.jrk.neuralNetwork;

import de.jrk.neuralNetwork.activationFunctions.ActivationFunction;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NeuralNetwork {
	private static final float STANDARD_MUTATION_RATE = 0.01f;

	private ArrayList<InputNeuron> inputNeurons;
	private ArrayList<Neuron> neurons;
	private ArrayList<Neuron> outputNeurons;
	private float mutationRate;

	public NeuralNetwork() {}

	public NeuralNetwork(int loadLevel, InputStream inputStream) {
		load(loadLevel, inputStream);
	}

	public float[] step(float[] inputs) {
		float[] outputs = new float[outputNeurons.size()];

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

	public void load(int loadLevel, InputStream inputStream) {
		boolean loadActFunc = loadLevel > 0;
		boolean loadWeights = loadLevel > 1;
		boolean loadActLevel = loadLevel > 2;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
			Element network = doc.getDocumentElement();
			int inputs = Integer.parseInt(network.getAttribute("inputs"));
			int hiddens = Integer.parseInt(network.getAttribute("hiddens"));
			int outputs = Integer.parseInt(network.getAttribute("outputs"));
			String mutationRateAttr = network.getAttribute("mutationRate");
			int saveLevel = Integer.parseInt(network.getAttribute("saveLevel"));
			if (!mutationRateAttr.equals("")) {
				mutationRate = Float.parseFloat(mutationRateAttr);
			} else {
				mutationRate = STANDARD_MUTATION_RATE;
			}

			inputNeurons = new ArrayList<InputNeuron>();
			neurons = new ArrayList<Neuron>();
			outputNeurons = new ArrayList<Neuron>();

			for (int i = 0; i < inputs; i++) {
				InputNeuron inputNeuron = new InputNeuron();
				inputNeurons.add(inputNeuron);
				neurons.add(inputNeuron);
			}

			for (int i = 0; i < hiddens; i++) {
				neurons.add(new Neuron());
			}

			for (int i = 0; i < outputs; i++) {
				Neuron neuron = new Neuron();
				outputNeurons.add(neuron);
				neurons.add(neuron);
			}

			NodeList neuronNodes = network.getElementsByTagName("neuron");
			for (int i = 0; i < neuronNodes.getLength(); i++) {
				Element neuronElement = (Element) neuronNodes.item(i);
				int id = Integer.parseInt(neuronElement.getAttribute("id"));
				String actLevel = loadActLevel ? neuronElement.getAttribute("actLevel") : "";
				String actFunc = loadActFunc ? neuronElement.getAttribute("actFunc") : "";
				Neuron neuron = neurons.get(id);
				if (!actLevel.equals("")) {
					neuron.setActivationLevel(Float.parseFloat(actLevel));
				}
				neuron.setActivationFunction(actFunc.equals("") ? ActivationFunction.TANH : actFunc);

				NodeList connectionNodes = neuronElement.getElementsByTagName("connection");
				for (int k = 0; k < connectionNodes.getLength(); k++) {
					Element connectionElement = (Element) connectionNodes.item(k);
					String neuronId = connectionElement.getAttribute("neuron");
					String weight = loadWeights ? connectionElement.getAttribute("weight") : "";
					if (neuronId.equalsIgnoreCase("b")) {
						if (weight.equals("")) {
							neuron.addBiasConnection();
						} else {
							neuron.addBiasConnection(Float.parseFloat(weight));
						}
					} else {
						Neuron n = neurons.get(Integer.parseInt(neuronId));
						if (weight.equals("")) {
							neuron.addConnection(n);
						} else {
							neuron.addConnection(n, Float.parseFloat(weight));
						}
					}
				}
				if (saveLevel < 2 || loadLevel < 2) {
					neuron.initWeights();
				}
			}
			sortConnections();
		} catch (SAXException | IOException | ParserConfigurationException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void save(int saveLevel, OutputStream outputStream) {
		boolean saveActFunc = saveLevel > 0;
		boolean saveWeights = saveLevel > 1;
		boolean saveActLevel = saveLevel > 2;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element network = doc.createElement("network");
			network.setAttribute("inputs", String.valueOf(inputNeurons.size()));
			network.setAttribute("hiddens", String.valueOf(neurons.size() - (inputNeurons.size() + outputNeurons.size())));
			network.setAttribute("outputs", String.valueOf(outputNeurons.size()));
			if (mutationRate != STANDARD_MUTATION_RATE) {
				network.setAttribute("mutationRate", String.valueOf(mutationRate));
			}
			network.setAttribute("saveLevel", String.valueOf(saveLevel));
			doc.appendChild(network);

			for (int i = 0; i < neurons.size(); i++) {
				Neuron neuron = neurons.get(i);
				if (!(neuron instanceof InputNeuron)) {
					if (neuron.getConnections().size() > 0 || saveActLevel) {
						Element neuronElement = doc.createElement("neuron");
						neuronElement.setAttribute("id", String.valueOf(i));
						if (saveActFunc) neuronElement.setAttribute("actFunc", neuron.getActivationFunction().toString());
						if (saveActLevel) neuronElement.setAttribute("actLevel", String.valueOf(neuron.getActivationLevel()));
						network.appendChild(neuronElement);

						for (Connection c : neuron.getConnections()) {
							Element connectionElement = doc.createElement("connection");
							connectionElement.setAttribute("neuron", c instanceof BiasConnection ? "b" : String.valueOf(getIndex(c.getNeuron())));
							if (saveWeights) connectionElement.setAttribute("weight", String.valueOf(c.getWeight()));
							neuronElement.appendChild(connectionElement);
						}
					}
				}
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(new DOMSource(doc), new StreamResult(outputStream));
		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	private void sortConnections() {
		ConnectionComperator comp = new ConnectionComperator();
		for (Neuron n : neurons) {
			if (!(n instanceof InputNeuron)) {
				Collections.sort(n.getConnections(), comp);
			}
		}
	}

	public void mutate() {
		mutate(mutationRate);
	}

	public void mutate(float mutationRate) {
		if (mutationRate == 0) throw new IllegalArgumentException("Mutation rate can't be 0!");
		for (Neuron n : neurons) {
			if (!(n instanceof InputNeuron)) {
				n.mutate(mutationRate);
			}
		}
	}

	public NeuralNetwork getClone() {
		return (NeuralNetwork) clone();
	}

	@Override
	protected Object clone() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		save(2, baos);
		return new NeuralNetwork(2, new ByteArrayInputStream(baos.toByteArray()));
	}

	private class ConnectionComperator implements Comparator<Connection> {

		@Override
		public int compare(Connection c1, Connection c2) {
			return getIndex(c1.getNeuron()) - getIndex(c2.getNeuron());
		}
	}
}
