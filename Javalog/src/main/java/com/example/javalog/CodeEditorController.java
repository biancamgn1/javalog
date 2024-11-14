package com.example.javalog;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CodeEditorController {
    @FXML
    private Text projectNameLabel;

    @FXML
    private ImageView runButton;

    @FXML
    private ImageView saveButton;

    @FXML
    private TextArea textArea;

    @FXML
    private VBox lineNumberBox;

    private ExecutionQueue executionQueue;

    @FXML
    private void initialize() throws FileNotFoundException {
        projectNameLabel.setText(FileHandler.getCurrentFileName());

        if(FileHandler.isOpenFile()){
            String textFromFile = FileHandler.writeFileToTextArea();
            textArea.setText(textFromFile);
        }

        textArea.textProperty().addListener((observable, oldText, newText) -> updateLineNumbers());

        updateLineNumbers();
    }

    //===============================================================
    // Method used to display the number of lines in the code editor
    // each time user hits enter, a new number wud pop up
    private void updateLineNumbers() {
        // Clear current line numbers
        lineNumberBox.getChildren().clear();

        // Count lines in the TextArea
        int lineCount = textArea.getText().split("\n", -1).length;

        // Add line numbers to the VBox
        for (int i = 1; i <= lineCount; i++) {
            Text lineNumber = new Text(String.valueOf(i));
            lineNumber.setStyle("-fx-fill: #49d0d0;");
            lineNumber.setFont(new Font("Lato Semibold",12));
            //StackPane lineNumberContainer = new StackPane(lineNumber);
            //lineNumberContainer.setPadding(new Insets(5, 0, 10, 0));
            lineNumberBox.getChildren().add(lineNumber);
        }
    }

    //===============================================================
    // Method used when save button is clicked
    // the text inside the text area is saved onto the currentTextFile (overwrites the text file)
    @FXML
    private void saveButtonOnAction() throws IOException {
        String text = textArea.getText();
        FileHandler.saveFile(text);
    }

    //===============================================================
    // Method used when run button is clicked
    // the text inside the text area is saved onto the currentTextFile (overwrites the text file)
    // Execution Queue object is initialized for parsing
    @FXML
    private void runButtonOnAction() throws IOException {
        String text = textArea.getText();
        FileHandler.saveFile(text);

        executionQueue = new ExecutionQueue();
        executionQueue.loadFileIntoQueue(FileHandler.getCurrentFileName());
        // gets the file name from fileHandler class  ^^^^^
        // and uses it to load file to queue in ExecutionQueue class

        while(executionQueue.hasMoreLines()){ //start of parsing
            String line = executionQueue.getNextLine();
            System.out.println("Parsing line: " + line);
        }

    }


}
