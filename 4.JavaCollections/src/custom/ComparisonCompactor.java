package custom;

import junit.framework.Assert;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparisonCompactor {

    public static void main(String[] args) throws IOException {
        File[] fileNames = new File[3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = new File(scanner.nextLine());
        }
        scanner.close();
        writeToFile(fileNames[0], readFile(fileNames[1]));
        writeToFile(fileNames[0], readFile(fileNames[2]), true);
    }

    public static String readFile(File file) throws IOException {
        String collect;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            try (Stream<String> lines = reader.lines();) {
                collect = lines.collect(Collectors.joining("\n"));
            }
        }
        return collect;
    }

    public static void writeToFile(File file, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        }
    }

    public static void writeToFile(File file, String text, boolean info) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, info))) {
            writer.newLine();
            writer.write(text);
        }
    }
}

