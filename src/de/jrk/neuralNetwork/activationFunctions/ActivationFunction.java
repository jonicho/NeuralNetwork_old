package de.jrk.neuralNetwork.activationFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides different activation functions.
 * 
 * @author Jonas Keller
 *
 */
public abstract class ActivationFunction {
	/**
	 * The identity activation function: {@code f(x)=x}
	 * 
	 * @see Identity
	 */
	public final static String IDENTITY = "identity";
	/**
	 * The step activation function: {@code f(x)=(0 for x<0; 1 for x>=0)}
	 * 
	 * @see Step
	 */
	public final static String STEP = "step";
	/**
	 * The sigmoid activation function: {@code f(x)=1/(1+exp(-x))}
	 * 
	 * @see Sigmoid
	 */
	public final static String SIGMOID = "sigmoid";
	/**
	 * The bipolar activation function:
	 * {@code f(x)=(-1 for x<0; 0 for x=0; 1 for x>0)}
	 * 
	 * @see Bipolar
	 */
	public final static String BIPOLAR = "bipolar";
	/**
	 * The bipolar sigmoid activation function: {@code f(x)=(1-exp(-x))/(1+exp(-x))}
	 * 
	 * @see BipolarSigmoid
	 */
	public final static String BIPOLAR_SIGMOID = "bipolarSigmoid";
	/**
	 * The hyperbolic tangent activation function: {@code f(x)=tanh(x)}
	 * 
	 * @see Tanh
	 */
	public final static String TANH = "tanh";
	/**
	 * The hard hyperbolic tangent activation function:
	 * {@code f(x)=max(-1, min(1, x))}
	 * 
	 * @see HardTanh
	 */
	public final static String HARD_TANH = "hardTanh";
	/**
	 * The arc tan activation function: {@code f(x)=atan(x)}
	 * 
	 * @see ArcTan
	 */
	public final static String ARC_TAN = "arcTan";
	/**
	 * The absolute activation function: {@code f(x)=x}
	 * 
	 * @see Absolute
	 */
	public final static String ABSOLUTE = "absolute";
	/**
	 * The ramp activation function: {@code f(x)=max(0, x)}
	 * 
	 * @see Ramp
	 */
	public final static String RAMP = "ramp";
	/**
	 * The smooth ramp activation function: {@code f(x)=log(1+exp(x))}
	 * 
	 * @see SmoothRamp
	 */
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

	private ActivationFunction() {
	}

	public final static ActivationFunction getActivationFunction(String actFunc) {
		for (ActivationFunction a : actFuncs) {
			if (actFunc.equalsIgnoreCase(a.toString()))
				return a;
		}

		throw new IllegalArgumentException("Activation Function \"" + actFunc + "\" does not exist!");
	}

	@Override
	public abstract String toString();

	/**
	 * The activation function's function.
	 * 
	 * @param x
	 *            x
	 * @return f(x)
	 */
	public abstract float function(float x);

	/**
	 * The identity activation function: {@code f(x)=x}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Identity extends ActivationFunction {

		@Override
		public String toString() {
			return IDENTITY;
		}

		@Override
		public float function(float x) {
			return x;
		}
	}

	/**
	 * The step activation function: {@code f(x)=(0 for x<0; 1 for x>=0)}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Step extends ActivationFunction {

		@Override
		public String toString() {
			return STEP;
		}

		@Override
		public float function(float x) {
			return x < 0 ? 0 : 1;
		}
	}

	/**
	 * The sigmoid activation function: {@code f(x)=1/(1+exp(-x))}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Sigmoid extends ActivationFunction {

		@Override
		public String toString() {
			return SIGMOID;
		}

		@Override
		public float function(float x) {
			return 1 / (1 + (float) Math.exp(-x));
		}
	}

	/**
	 * The bipolar activation function:
	 * {@code f(x)=(-1 for x<0; 0 for x=0; 1 for x>0)}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Bipolar extends ActivationFunction {

		@Override
		public String toString() {
			return BIPOLAR;
		}

		@Override
		public float function(float x) {
			return x == 0 ? 0 : x < 0 ? -1 : 1;
		}
	}

	/**
	 * The bipolar sigmoid activation function: {@code f(x)=(1-exp(-x))/(1+exp(-x))}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class BipolarSigmoid extends ActivationFunction {

		@Override
		public String toString() {
			return BIPOLAR_SIGMOID;
		}

		@Override
		public float function(float x) {
			float i = (float) Math.exp(-x);
			return (1 - i) / (1 + i);
		}
	}

	/**
	 * The hyperbolic tangent activation function: {@code f(x)=tanh(x)}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Tanh extends ActivationFunction {

		@Override
		public String toString() {
			return TANH;
		}

		@Override
		public float function(float x) {
			return (float) Math.tanh(x);
		}
	}

	/**
	 * The hard hyperbolic tangent activation function:
	 * {@code f(x)=max(-1, min(1, x))}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class HardTanh extends ActivationFunction {

		@Override
		public String toString() {
			return HARD_TANH;
		}

		@Override
		public float function(float x) {
			return Math.max(-1f, Math.min(1f, x));
		}
	}

	/**
	 * The arc tan activation function: {@code f(x)=atan(x)}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class ArcTan extends ActivationFunction {

		@Override
		public String toString() {
			return ARC_TAN;
		}

		@Override
		public float function(float x) {
			return (float) Math.atan(x);
		}
	}

	/**
	 * The absolute activation function: {@code f(x)=x}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Absolute extends ActivationFunction {

		@Override
		public String toString() {
			return ABSOLUTE;
		}

		@Override
		public float function(float x) {
			return Math.abs(x);
		}
	}

	/**
	 * The ramp activation function: {@code f(x)=max(0, x)}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class Ramp extends ActivationFunction {

		@Override
		public String toString() {
			return RAMP;
		}

		@Override
		public float function(float x) {
			return Math.max(0, x);
		}
	}

	/**
	 * The smooth ramp activation function: {@code f(x)=log(1+exp(x))}
	 * 
	 * @author Jonas Keller
	 *
	 */
	private static class SmoothRamp extends ActivationFunction {

		@Override
		public String toString() {
			return SMOOTH_RAMP;
		}

		@Override
		public float function(float x) {
			return (float) Math.log(1 + Math.exp(x));
		}
	}
}
