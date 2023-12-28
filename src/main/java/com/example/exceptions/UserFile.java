package com.example.exceptions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserFile {
    private final static String READ_FILE_PATH
            = "C:\\projects\\java-core\\src\\main\\java\\com\\example\\exceptions\\source.txt";
    private final static String WRITE_FILE_PATH
            = "C:\\projects\\java-core\\src\\main\\java\\com\\example\\exceptions\\destination.txt";

    public static List<String> readWithBufferedReader(String path) {
        List<String> readText = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                readText.add(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return readText;
    }

    public static List<Character> readWithFileReader(String path) {
        List<Character> readText = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path, StandardCharsets.UTF_8)) {
            int ch;
            while ((ch = fileReader.read()) > 0) {
                System.out.print((char) ch);
                readText.add((char) ch);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return readText;
    }

    public static void writeWithBufferedWriter(String pathName, String[] text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter((
                new FileWriter(pathName, StandardCharsets.UTF_8, true)))) {
            System.out.println("===Запись с помощью BufferedWriter:===");
            bufferedWriter.newLine();
            for (String line : text) {
                bufferedWriter.write(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void writeWithFileWriter(String filePath, String[] text) {
        try (FileWriter fileWriter = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            System.out.println("===Запись с помощью FileWriter:===");
            for (String line : text) {
                fileWriter.write(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void main(String[] args) {
        String[] firstTextForWrite =
                {
                        "Быть или не быть, вот в чем вопрос. Достойно ль\n"
                                + "Смиряться под ударами судьбы,\n"
                                + "Иль надо оказать сопротивленье\n"
                                + "И в смертной схватке с целым морем бед\n"
                                + "Покончить с ними? Умереть. Забыться."
                };
        writeWithFileWriter(WRITE_FILE_PATH, firstTextForWrite);
        List<Character> firstText = readWithFileReader(READ_FILE_PATH);

        System.out.println();

        String[] secondTextToWrite =
                {
                        "Я не волоку его тело к Богу на руках\n"
                                + "Я без лишних мук, усекаю круг, шагаю дальше\n"
                                + "Через \"не могу\", через вьюгу, через детский страх\n"
                                + "Через нищету, через стресс, бедности овраг"
                };
        writeWithBufferedWriter(WRITE_FILE_PATH, secondTextToWrite);
        List<String> secondText = readWithBufferedReader(READ_FILE_PATH);
    }
}
