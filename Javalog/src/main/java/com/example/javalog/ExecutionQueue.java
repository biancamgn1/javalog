package com.example.javalog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


// This class works as an Interpreter
// Interpreting each line (line by line) by using a Queue structure
public class ExecutionQueue {

    private Queue<String> queue;

    public ExecutionQueue() {
        queue = new LinkedList<String>();
    }

    //===============================================================
    // Method loads the current File into the queue (line by line)
    // filename is the currentFileName variable of FileHandler class --> "text.txt"
    public void loadFileIntoQueue(String filename) {
        try(BufferedReader br = new BufferedReader(new FileReader("Saved Projects/"+filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.add(line);
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //===============================================================
    // Helper method to get the next line in the txt file for parsing
    public String getNextLine(){
        return queue.poll();
    }

    //===============================================================
    // Helper method to check if queue not empty (has more lines)
    public boolean hasMoreLines() {
        return !queue.isEmpty();
    }


}
