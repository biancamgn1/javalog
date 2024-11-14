package com.example.javalog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class openProjectController {
    @FXML
    private Text ProjectLabel;

    @FXML
    private TextField SearchOrCreateProjectTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newProjectButton;

    @FXML
    private Button openProjectButton;

    @FXML
    private Button proceedButton;

    @FXML
    private AnchorPane openProjectAnchorPane;

    //===============================================================
    // Go to create project section
    @FXML
    public void newProjectButtonClicked() throws IOException {
        AnchorPane nextAnchorpane = FXMLLoader.load(mainApplication.class.getResource("new-project-view.fxml"));
        openProjectAnchorPane.getChildren().removeAll();
        openProjectAnchorPane.getChildren().setAll(nextAnchorpane);
    }

    //===============================================================
    // If proceed button is clicked
    @FXML
    public void proceedButtonClicked() throws IOException {

        String projectName = SearchOrCreateProjectTextField.getText();

        FileHandler.openFile(projectName);

        if(FileHandler.isFileExists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("code-editor-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(projectName +".log");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            Stage currentStage = (Stage) cancelButton.getScene().getWindow();
            currentStage.close();

            stage.show();

        }
    }





}