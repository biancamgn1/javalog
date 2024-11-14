package com.example.javalog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class newProjectController {
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
    private AnchorPane newProjectAnchorPane;

    //===============================================================
    //If "Tumuloy" button/ proceedButton clicked, checks if not null and if valid proj name then
    //creates new project file (check FileHandler Class)
    //if file not existing in "Saved Projects" directory, creates new stage for code editor
    @FXML
    public void createNewProject() throws IOException {
        String projectName = SearchOrCreateProjectTextField.getText();

        if(projectName != null && FileHandler.isValidFileName(projectName)) {
            FileHandler.createNewFile(projectName);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("code-editor-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(projectName+".log");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            Stage currentStage = (Stage) cancelButton.getScene().getWindow();
            currentStage.close();

            stage.show();
        }

    }
    //===============================================================
    // If cancel button clicked. clear the text field
    @FXML
    public void cancelButtonClicked() throws IOException {
        SearchOrCreateProjectTextField.clear();
    }

    //===============================================================
    // Open existing project in saved projects folder
    @FXML
    public void openProjectButtonClicked() throws IOException {
        AnchorPane nextAnchorpane = FXMLLoader.load(mainApplication.class.getResource("open-project-view.fxml"));
        newProjectAnchorPane.getChildren().removeAll();
        newProjectAnchorPane.getChildren().setAll(nextAnchorpane);
    }


}
