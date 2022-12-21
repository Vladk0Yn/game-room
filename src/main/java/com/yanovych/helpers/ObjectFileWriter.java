package com.yanovych.helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ObjectFileWriter {
    public static void write(String fileName ,String info, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append));
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
