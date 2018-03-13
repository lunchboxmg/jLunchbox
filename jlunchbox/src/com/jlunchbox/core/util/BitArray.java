/*******************************************************************************
 * Copyright 2011 See AUTHORS.libgdx file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
