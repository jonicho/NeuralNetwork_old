package activationFunktions;

public class TangensHyperbolicus extends ActivationFunction {

	@Override
	public double function(double x) {
		return Math.tanh(x);
	}

	
}
