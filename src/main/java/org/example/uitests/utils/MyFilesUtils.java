package org.example.uitests.utils;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyFilesUtils {
    public static File generateLoremFile() throws IOException {
        Faker faker = new Faker();
        List<String> words = faker.lorem().words(100);
        File generatedFile = new File("files", RandomStringUtils.randomAlphabetic(10) + ".txt");
        FileUtils.writeLines(generatedFile, words);
        return generatedFile;
    }

    public static File waitTillFileIsLoaded(File file) throws InterruptedException {
        int count = 0;
        while (count != 60) {
            if (!file.exists()) {
                Thread.sleep(1000);
                count++;
            } else {
                break;
            }
        }

        count = 0;
        while (count < 60) {
            long lengthBefore = file.length();
            Thread.sleep(1000);
            long lengthAfter = file.length();
            if (lengthBefore == lengthAfter) {
                return file;
            } else {
                count++;
            }
        }
        return null;
    }

//    public static void main(String[] args) throws IOException {
//        generateLoremFile();
//    }

}
