package org.robockets.lidarlitev3;

public enum AcqCommandFunction {
	RESET_FGPA(0x00),
	TAKE_DISTANCE_NO_BIAS_CORRECTION(0x03),
	TAKE_DISTANCE_BIAS_CORRECTION(0x04);
	
	private final int data;
	AcqCommandFunction(int bit) {
		this.data = bit;
	}
	
	public int getData() {
		return this.data;
	}
}
