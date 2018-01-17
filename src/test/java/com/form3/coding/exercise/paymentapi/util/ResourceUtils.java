package com.form3.coding.exercise.paymentapi.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by Attila on 14/01/2018.
 */
public class ResourceUtils {

    public static String readFile(final String fileName) throws Exception {
        return Files
                .lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))
                .collect(Collectors.joining());
    }
}
