package com.example.javalog;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    // use this to get the current file when saving work etc
    private static String currentFileName;
    private static String currentFilePath;
    private static boolean openFile = false; // used to check if open file function was used instead of creating new file

    //===============================================================
    // Method create new file (used in the new project view)
    public static void createNewFile(String fileName) {

        fileName = fileName + ".txt";

        // Define the "Saved Projects" directory
        String directoryPath = "Saved Projects";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesnt exist
        }

        // Define the file path
        File file = new File(directory, fileName);

        // Check if the file already exists
        if (file.exists()) {
            System.out.println("File already exists in " + directoryPath);
            return;
        }

        // Create the new file
        try (FileWriter fileWriter = new FileWriter(file)) {
            System.out.println("File created: " + file.getAbsolutePath());
            currentFileName = fileName;
            currentFilePath = directoryPath+"/"+fileName;
            System.out.println("filename: "+ currentFileName);
            System.out.println("filepath: "+ currentFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //===============================================================
    // Helper Method of createNewFile(String fileName) to check if the file name is valid
    public static boolean isValidFileName(String fileName) {
        char[] fileNameArray = fileName.toCharArray();

        Character[] illegalSymbolsArray = {'#', '%', '&', '{', '}', '/', '>', '<'};
        List<Character> illegalSymbols = Arrays.asList(illegalSymbolsArray);

        for(char c: fileNameArray){
            if (illegalSymbols.contains(c) ){
                return false;
            }
            if (c == ' '){
                return false;
            }
        }
        return true;
    }

    //===============================================================
    // Method used in CodeEditorController when run/save button clicked saves the current file
    public static void saveFile(String textAreaString) throws IOException {
        if (currentFilePath != null && currentFileName != null ) {

            try(FileWriter filewriter = new FileWriter(currentFilePath)){
                filewriter.write(textAreaString);
                System.out.println(currentFileName+" Saved!");
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            System.err.println("Error: No file selected");
        }
    }

    //===============================================================
    // Method used OpenProjectController wherein it checks if the entered filename is existing
    // which assigns the existing file as currentFileName
    public static void openFile(String fileName) {

        fileName = fileName + ".txt";

        String directoryPath = "Saved Projects";
        File directory = new File(directoryPath);

        File file = new File(directory, fileName);

        if (file.exists()) {
            openFile = true;
            currentFileName = fileName;
            currentFilePath = directoryPath+"/"+fileName;
            System.out.println("filename: "+ currentFileName);
            System.out.println("filepath: "+ currentFilePath);
        }

    }

    //===============================================================
    // Helper method to check if current file name exists
    // This is used to check if it can proceed to the codeEditor screen
    public static boolean isFileExists() {
        String directoryPath = "Saved Projects";
        File directory = new File(directoryPath);

        File file = new File(directory, currentFileName);
        return file.exists();
    }
    //===============================================================
    // Helper method when switching to Code Editor UI, copy the text in txt file and paste it to Code Editor text area
    public static String writeFileToTextArea() throws FileNotFoundException {
        List<String> textLines = new ArrayList<>();
        String StringContent = "";
        try(BufferedReader br = new BufferedReader(new FileReader("Saved Projects/"+currentFileName))){
            String linebr;
            while ((linebr = br.readLine()) != null){
                textLines.add(linebr);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        for(String line: textLines){
            StringContent = StringContent + line+"\n";
        }
        return StringContent;


    }


    public static String getCurrentFileName() {
        return currentFileName;

    }

    public static void setCurrentFileName(String currentfilename) {
        currentFileName = currentfilename;
    }

    public static boolean isOpenFile() {
        return openFile;
    }

    public static void setOpenFile(boolean openFile) {
        FileHandler.openFile = openFile;
    }
}
