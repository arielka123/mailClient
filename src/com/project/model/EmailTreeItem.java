package com.project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailTreeItem <String> extends TreeItem {

    private String name;
    private ObservableList<EmailMessage> emailMessages;
    private int unreadMassagesCount;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }

    public ObservableList<EmailMessage> getEmailMessages(){
        return emailMessages;
    }

    public void addEmail(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(emailMessage);
    }

    public void addEmailToTop(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(0,emailMessage);

    }

    private EmailMessage fetchMessage(Message message) throws MessagingException {
        boolean messageIsRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                message.getFrom()[0].toString(),
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                message.getSentDate(),
                messageIsRead,
                message
                );
        if(!messageIsRead){
            incrementMessages();
        }
        return emailMessage;
    }

    public void incrementMessages(){
        unreadMassagesCount ++;
        updateName();
    }

    public void decrementMessages(){
        unreadMassagesCount --;
        updateName();
    }

    private void updateName (){
        if(unreadMassagesCount > 0){
            this.setValue((String)(name + " (" + unreadMassagesCount + ")"));
        }
        else setValue(name);
    }
}
