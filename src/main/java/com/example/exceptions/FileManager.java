package com.example.exceptions;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    private final static String READ_FILE_PATH
            = "C:\\projects\\java-core\\src\\main\\java\\com\\example\\exceptions\\source.txt";
    private final static String WRITE_FILE_PATH
            = "C:\\projects\\java-core\\src\\main\\java\\com\\example\\exceptions\\destination.txt";

    public static String readWithBufferedReader(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return stringBuilder.toString();
    }

    public static void writeWithBufferedWriter(String pathName, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter((
                new FileWriter(pathName, StandardCharsets.UTF_8, true)))) {
            bufferedWriter.newLine();
            bufferedWriter.write(text);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void main(String[] args) {
        String textForWrite =
                """
                        Быть или не быть, вот в чем вопрос. Достойно ль
                        Смиряться под ударами судьбы,
                        Иль надо оказать сопротивленье
                        И в смертной схватке с целым морем бед
                        Покончить с ними? Умереть. Забыться.""";

        writeWithBufferedWriter(WRITE_FILE_PATH, textForWrite);
        String secondText = readWithBufferedReader(READ_FILE_PATH);

        System.out.println("\n" + secondText);
    }
}
