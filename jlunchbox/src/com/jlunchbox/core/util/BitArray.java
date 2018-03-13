/*******************************************************************************
 * Inspired from artemis ecs -> libgdx
 ******************************************************************************/

package com.jlunchbox.core.util;

public class BitArray {
	
	long[] bits = {0};

	/** Creates a bit array with initial size set to contain num amount of bits.
	 * @param num initial number of bits in the array.
	 */
	public BitArray(int num) { checkCapacity(num >>> 6); }
	
	public boolean get(int index) {
		int record = index >>> 6;
		if (record >= bits.length) return false;
		return (bits[record] & (1L << (index & 0x3F))) != 0;
	}
	
	public void set(int index) {
		int record = index >>> 6;
		checkCapacity(record);
		bits[record] |= 1L << (index & 0x3F);
	}
	
	public void toggle(int index) {
		int record = index >>> 6;
		checkCapacity(record);
		bits[record] ^= 1L << (index & 0x3F);
	}
	
	private void checkCapacity(int size) {
		
		if (size >= bits.length) {
			long[] newBits = new long[size + 1];
			System.arraycopy(bits, 0, newBits, 0, bits.length);
			bits = newBits;
		}
	}

}
