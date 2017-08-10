package de.jrk.neuralNetwork.util;

public class Util {
	private Util() {}
	
	public static int[] convertToIntArray(String[] strArr) {
		int[] intArr = new int[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}
}
