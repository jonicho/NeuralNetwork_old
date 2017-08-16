package de.jrk.neuralNetwork.util;

import java.util.Random;

public class Util {
	public static Random random = new Random();
	
	private Util() {}
	
	public static int[] convertToIntArray(String[] strArr) {
		int[] intArr = new int[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}
}
