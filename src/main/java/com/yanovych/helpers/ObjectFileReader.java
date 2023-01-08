package com.yanovych.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanovych.entities.Child;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObjectFileReader<T> {
    public List<T> readListOfObjects(String fileName, Type type) {
        StringBuilder info = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                info.append(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<T> obj = new Gson().fromJson(info.toString(), type);
        return obj;
    }
}
