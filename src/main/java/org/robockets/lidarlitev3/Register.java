package org.robockets.lidarlitev3;

public enum Register {
	ACQ_COMMAND(0x00),
	STATUS(0x01),
	SIG_COUNT_VAL(0x02),
	ACQ_CONFIG_REG(0x04),
	VELOCITY(0x09),
	PEAK_CORR(0x0c),
	NOISE_PEAK(0x0d),
	SIGNAL_STRENGTH(0x0e),
	FULL_DELAY_HIGH(0x0f),
	FULL_DELAY_LOW(0x10),
	OUTER_LOOP_COUNT(0x11),
	REF_COUNT_VAL(0x12),
	LAST_DELAY_HIGH(0x14),
	LAST_DELAY_LOW(0x15),
	UNIT_ID_HIGH(0x16),
	UNIT_ID_LOW(0x17),
	I2C_ID_HIGH(0x18),
	I2C_ID_LOW(0x19),
	THRESHOLD_BYPASS(0x1c),
	I2C_CONFIG(0x1e),
	COMMAND(0x40),
	MEASURE_DELAY(0x45),
	PEAK_BCK(0x4c),
	CORR_DATA(0x52),
	CORR_DATA_SIGN(0x53),
	ACQ_SETTINGS(0x5d),
	POWER_CONTROL(0x65);
	
	private final int address;
	public int getAddress() { return this.address; }
	Register(int address) { this.address = address; }
}
