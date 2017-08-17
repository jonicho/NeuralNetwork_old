package de.jrk.neuralNetwork.activationFunctions;
import java.util.ArrayList;
import java.util.List;

public abstract class ActivationFunction {
	public final static String IDENTITY = "identity";
	public final static String STEP = "step";
	public final static String SIGMOID = "sigmoid";
	public final static String BIPOLAR = "bipolar";
	public final static String BIPOLAR_SIGMOID = "bipolarSigmoid";
	public final static String TANH = "tanh";
	public final static String HARD_TANH = "hardTanh";
	public final static String ARC_TAN = "arcTan";
	public final static String ABSOLUTE = "absolute";
	public final static String RAMP = "ramp";
	public final static String SMOOTH_RAMP = "smoothRamp";
	
	private final static List<ActivationFunction> actFuncs = new ArrayList<ActivationFunction>();
	
	static {
		actFuncs.add(new Identity());
		actFuncs.add(new Step());
		actFuncs.add(new Sigmoid());
		actFuncs.add(new Bipolar());
		actFuncs.add(new BipolarSigmoid());
		actFuncs.add(new Tanh());
		actFuncs.add(new HardTanh());
		actFuncs.add(new ArcTan());
		actFuncs.add(new Absolute());
		actFuncs.add(new Ramp());
		actFuncs.add(new SmoothRamp());
	}

	private ActivationFunction() {}

	public final static ActivationFunction getActivationFunction(String actFunc) {
		for(ActivationFunction a : actFuncs) {
			if (actFunc.equalsIgnoreCase(a.toString())) return a;
		}

		throw new IllegalArgumentException("Activation Function \"" + actFunc + "\" does not exist!");
	}

	@Override
	public abstract String toString()

	public abstract float function(float x)



	private static class Identity extends ActivationFunction {

		@Override
		public String toString() {return IDENTITY;}

		@Override
		public float function(float x) {
			return x;
		}
	}

	private static class Step extends ActivationFunction {

		@Override
		public String toString() {return STEP;}

		@Override
		public float function(float x) {
			return x > 0 ? 1 : 0;
		}
	}

	private static class Sigmoid extends ActivationFunction {

		@Override
		public String toString() {return SIGMOID;}

		@Override
		public float function(float x) {
			return 1 / (1 + (float) Math.exp(-x));
		}
	}

	private static class Bipolar extends ActivationFunction {

		@Override
		public String toString() {return BIPOLAR;}

		@Override
		public float function(float x) {
			return x == 0 ? 0 : x > 0 ? 1 : -1;
		}
	}

	private static class BipolarSigmoid extends ActivationFunction {

		@Override
		public String toString() {return BIPOLAR_SIGMOID;}

		@Override
		public float function(float x) {
			float i = (float) Math.exp(-x);
			return (1 - i) / (1 + i);
		}
	}
	
	private static class Tanh extends ActivationFunction {

		@Override
		public String toString() {return TANH;}
		
		@Override
		public float function(float x) {
			return (float) Math.tanh(x);
		}
	}
	
	private static class HardTanh extends ActivationFunction {

		@Override
		public String toString() {return HARD_TANH;}
		
		@Override
		public float function(float x) {
			return Math.max(-1f, Math.min(1f, x));
		}
	}
	
	private static class ArcTan extends ActivationFunction {

		@Override
		public String toString() {return ARC_TAN;}

		@Override
		public float function(float x) {
			return (float) Math.atan(x);
		}
	}
	
	private static class Absolute extends ActivationFunction {

		@Override
		public String toString() {return ABSOLUTE;}

		@Override
		public float function(float x) {
			return Math.abs(x);
		}
	}
	
	private static class Ramp extends ActivationFunction {

		@Override
		public String toString() {return RAMP;}

		@Override
		public float function(float x) {
			return Math.max(0, x);
		}
	}
	
	private static class SmoothRamp extends ActivationFunction {

		@Override
		public String toString() {return SMOOTH_RAMP;}

		@Override
		public float function(float x) {
			return (float) Math.log(1 + Math.exp(x));
		}
	}
}
