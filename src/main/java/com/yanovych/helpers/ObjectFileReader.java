package com.yanovych.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ObjectFileReader {
    public static String read(String fileName) {
        StringBuilder info = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while(reader.ready()) {
                info.append(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }
}
