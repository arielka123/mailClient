package com.project.controller;

import com.project.EmailManager;
import com.project.controller.services.EmailSenderService;
import com.project.model.EmailAccount;
import com.project.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ComposeMessageController extends BaseController implements Initializable {

    @FXML
    private Label errorLabel;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private TextField recipientTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    void sendButtonAction() {
//        System.out.println("send!!!!");
//        System.out.println(htmlEditor.getHtmlText());
        EmailSenderService emailSenderService = new EmailSenderService(
                emailAccountChoiceBox.getValue(),
                subjectTextField.getText(),
                recipientTextField.getText(),
                htmlEditor.getHtmlText()
        );
        emailSenderService.start();
        emailSenderService.setOnSucceeded(event -> {
            EmailSendingResult emailSendingResult = (EmailSendingResult) emailSenderService.getValue();
            switch (emailSendingResult){
                case SUCCESS:
                    Stage stage =(Stage) recipientTextField.getScene().getWindow();
                    viewFactory.closeStage(stage);
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    errorLabel.setText("Unexpected error!");
                    break;
                case FAILED_BY_PROVIDER:
                    errorLabel.setText("Provider error");
                    break;
            }
        });
    }

    @FXML
    private ChoiceBox<EmailAccount> emailAccountChoiceBox;

    public ComposeMessageController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAccountChoiceBox.setItems(emailManager.getEmailAccounts());
        emailAccountChoiceBox.setValue(emailManager.getEmailAccounts().get(0));
    }
}
