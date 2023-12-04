package com.ronan.space.invaders.util;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FileUtilsTest {

    @Test
    void testReadFile(){
        FileUtils fileUtils = new FileUtils();
        String[][] result = fileUtils.readFile(GameConstants.SQUID_PATH);


        // Assertions
        assertArrayEquals(new String[]{"-", "-", "-","o", "o", "-","-", "-"}, result[0]);
        assertArrayEquals(new String[]{"o", "o", "o", "o", "o", "o", "o", "o" }, result[4]);
        assertEquals(8, result.length);
        assertEquals(8, result[0].length);
    }

    @Test
    void testReadFileWithNonExistentFile() {
        FileUtils fileUtils = new FileUtils();
        assertThrows(RuntimeException.class, () -> fileUtils.readFile("squidcrab.txt"));
    }
}

