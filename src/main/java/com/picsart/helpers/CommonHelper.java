package com.picsart.helpers;

import java.security.SecureRandom;

public final class CommonHelper {
    private static final SecureRandom RANDOM = new SecureRandom();

    private static String getRandomString(String characters, int length) {
        int size = characters.length();
        StringBuilder stringBuilder = new StringBuilder();
        while (length-- != 0) {
            stringBuilder.append(characters.charAt(RANDOM.nextInt(size)));
        }
        return stringBuilder.toString();

    }

    public static String getRandomAlphabeticString(int length) {
        return getRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    private CommonHelper() {
        throw new UnsupportedOperationException("Not allowed to crate an instance of CommonHelper class");
    }
}
