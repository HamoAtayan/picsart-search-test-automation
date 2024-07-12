package com.picsart.helpers;

import java.util.List;
import java.util.Random;

public class ApiHelper {
    public static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        return list.stream()
                .skip(random.nextInt(list.size()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
