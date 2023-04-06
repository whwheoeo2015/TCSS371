/*
 * Convert.java
 * 
 * TCSS 371 assignment 1
 */

package code;

/**
 * A class to provide static methods for converting numbers between bases.
 * 
 * @author Alan Fowler
 * @author Your name(s) here
 * @version 1.1
 */
public final class Convert {
    
    /**
     * A private constructor to inhibit instantiation of this class.
     */
    private Convert() {
        // Objects should not be instantiated from this class.
        // This class is just a home for static methods (functions).
        // This design is similar to the Math class in the Java language.
    }
    
    /**
     * Accepts an array of characters representing the bits in a two's complement number
     * and returns the decimal equivalent.
     *
     * precondition:
     * This method requires that the maximum length of the parameter array is 16.
     *
     * postcondition:
     * The value returned is the decimal equivalent of the two's complement parameter.
     * The parameter array is unchanged.
     * 
     * @param theBits an array representing the bits in a two's complement number
     * @throws IllegalArgumentException if the length of the parameter array > 16.
     * @return the decimal equivalent of the two's complement parameter
     */
    public static int convert2sCompToDecimal(final char[] theBits) {
        
	if(theBits.length > 16) {
	    throw new IllegalArgumentException("The bits should be less than equal to 16 bits");
	}
	
	final int totalBits = theBits.length;
	int result = 0;
	
	if(theBits[0] == '1') {
            result = (int) Math.pow(2, totalBits-1) * -1;
	}
	
        for(int i = 1; i < totalBits; i++) {
            if(theBits[i] == '1') {
        	result = result + (int) Math.pow(2, totalBits - 1 - i );
            }
        }
	
        return result; // Replace the zero return value with a correct return value.
    }
    

    
    /**
     * Accepts a decimal parameter and returns an array of characters
     * representing the bits in the 16 bit two's complement equivalent.
     * 
     * precondition:
     * This method requires that the two's complement equivalent
     * won't require more than 16 bits
     *
     * postcondition:
     * The returned array represents the 16 bit two's complement equivalent
     * of the decimal parameter.
     * 
     * @param theDecimal the decimal number to convert to two's complement
     * @throws IllegalArgumentException if the parameter cannot be represented in 16 bits.
     * @return a 16 bit two's complement equivalent of the decimal parameter
     */
    public static char[] convertDecimalTo2sComp(final int theDecimal) {
        
	char[] result = new char[16];
	
	if(theDecimal > 32767 || theDecimal < -32768) {
	    throw new IllegalArgumentException("The input can't be represented by 16 bits");
	}
	int theValue;
	
	if(theDecimal >= 0) {
	    theValue = theDecimal;
	} else {
	    theValue = theDecimal * -1;
	}
	
	for(int i = 15; i >= 0; i--) {
	    if(theValue > 0) {
		
	    	if(theValue % 2 == 1) {
	    	    result[i] = '1';
	    	} else {
	    	result[i] = '0';
	    	}
	    	
	    	theValue = theValue / 2;
	    }
	    else {
		result[i] = '0';
	    }
	}
	
	if(theDecimal >= 0) {
	    return result;
	} else {
	    int index = 15;
	    
	    while(result[index] != '1') {
		index = index -1;
	    }
	    
	    for(int i = index-1; i >= 0; i--) {
		if(result[i] == '1') {
		    result[i] = '0';
		} else {
		    result[i] ='1';
		}
	    }
	    
	    return result;
	}
    }
    
    
    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */
}
