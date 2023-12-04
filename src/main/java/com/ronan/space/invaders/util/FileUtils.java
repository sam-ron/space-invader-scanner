package com.ronan.space.invaders.util;

import com.ronan.space.invaders.factory.RadarObjectFactory;
import com.ronan.space.invaders.model.RadarObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {

    public String[][] readFile(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String[]> fileContent = new ArrayList<>();
        reader.lines().forEach(line -> fileContent.add(line.split("")));

        return fileContent.toArray(String[][]::new);
    }

}
