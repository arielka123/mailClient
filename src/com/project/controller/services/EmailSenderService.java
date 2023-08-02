package com.project.controller.services;

import com.project.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmailSenderService extends Service {
    private EmailAccount emailAccount;
    private String subject;
    private String recipient;
    private String content;

    public EmailSenderService(EmailAccount emailAccount, String subject, String recipient, String content) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                return null;
            }
        };
    }
}
