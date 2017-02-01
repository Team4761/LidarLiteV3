package org.robockets.lidarlitev3;

import edu.wpi.first.wpilibj.I2C;

public class LidarLiteV3 {
	private I2C i2c;
	
	public LidarLiteV3(I2C.Port port, int address, Configuration config) {
		i2c = new I2C(port, address);
	}
	
	public void configure(Configuration config) {
		// set some default values
		int sigCountValBuffer = 0x80;
		int acqConfigRegBuffer = 0x08;
		int thresholdBypassBuffer = 0x00;
		
		//change them if needed, based on configuration
		switch(config) {
		case BALANCED:
		case SHORT_AND_FASTEST:
			sigCountValBuffer = 0x1d; break;
		case DEFAULT_AND_FASTER:
			acqConfigRegBuffer = 0x00; break;
		case MAX_AND_SLOWEST:
			sigCountValBuffer = 0xff; break;
		case HIGH_SENSITIVITY:
			thresholdBypassBuffer = 0x80; break;
		case LOW_SENSITIVITY:
			thresholdBypassBuffer = 0xb0; break;
		}
		
		i2cWrite(Register.SIG_COUNT_VAL,    sigCountValBuffer);
		i2cWrite(Register.ACQ_CONFIG_REG,   acqConfigRegBuffer);
		i2cWrite(Register.THRESHOLD_BYPASS, thresholdBypassBuffer);
	}
	
	public void resetFgpa() {
		i2cWrite(Register.ACQ_COMMAND, AcqCommandFunction.RESET_FGPA.getData());
	}
	
	public static final int defaultMaxAcquisitionCount = 0x80;
	
	public void setMaxAcquisitionCount(int count) {
		i2cWrite(Register.SIG_COUNT_VAL, count);
	}
	
	public int getDistance(boolean usingBiasCorrection) {
		if(usingBiasCorrection) {
			i2cWrite(Register.ACQ_COMMAND, AcqCommandFunction.TAKE_DISTANCE_BIAS_CORRECTION.getData());
		}
		else {
			i2cWrite(Register.ACQ_COMMAND, AcqCommandFunction.TAKE_DISTANCE_NO_BIAS_CORRECTION.getData());
		}
		
		byte[] distanceArray = i2cRead(0x8f, 2);
		int distance = (distanceArray[0] << 8) + distanceArray[1];
		return distance;
	}
	
	public void i2cWrite(int registerAddress, int data) {
		i2c.write(registerAddress, data);
	}
	
	public void i2cWrite(Register register, int data) {
		i2cWrite(register.getAddress(), data);
	}
	
	public byte[] i2cRead(Register register, int count) {
		return i2cRead(register.getAddress(), count);
	}
	
	public byte[] i2cRead(int registerAddress, int count) {
		byte[] buffer = new byte[count];
		i2c.read(registerAddress, count, buffer);
		return buffer;
	}
}
