package utils;

import java.util.Base64;

/**
 * StringDecodeImpl class to decode Base64 Encoded strings
 */
public final class StringDecodeImpl {

    private StringDecodeImpl() {
    }

    /**
     * Method to get Title for specified page
     *
     * @param value - The Encoded String as parameter
     * @return String - The decoded String
     */
    public static String stringDecode(String value) {

        return new String(Base64.getDecoder().decode(value));
    }
}
