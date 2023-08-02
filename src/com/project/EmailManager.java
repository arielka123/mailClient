package com.project;

import com.project.controller.services.FetchFolderService;
import com.project.controller.services.FolderUpdaterService;
import com.project.model.EmailAccount;
import com.project.model.EmailMessage;
import com.project.model.EmailTreeItem;
import com.project.view.IconResolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private EmailMessage selectedMessage;
    private EmailTreeItem<String> selectedFolder;
    private ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();

    public ObservableList<EmailAccount> getEmailAccounts(){
        return emailAccounts;
    }

    private IconResolver iconResolver = new IconResolver();

    public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }
    public EmailTreeItem<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedMessage(EmailMessage selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
    public EmailMessage getSelectedMessage() {
        return selectedMessage;
    }

    private FolderUpdaterService folderUpdaterService;
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    private List<Folder> folderlist = new ArrayList<Folder>();

    public List<Folder> getfolderlist(){
        return this.folderlist;
    }

    public EmailManager(){
        folderUpdaterService = new FolderUpdaterService(folderlist);
        folderUpdaterService.start();
    }

    public void addEmailAccount(EmailAccount emailAccount){
        emailAccounts.add(emailAccount);
        EmailTreeItem<String> treeItem = new EmailTreeItem(emailAccount.getAddress());
        treeItem.setGraphic(iconResolver.getIconForFolder(emailAccount.getAddress()));
        treeItem.setExpanded(true);  //children becoming visible
        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), treeItem, folderlist);
        fetchFolderService.start();
        foldersRoot.getChildren().add(treeItem);
    }

    public void setRead() {
        try {
            selectedMessage.setIsRead(true);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
            selectedFolder.decrementMessages();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setUnRead() {
        try {
            selectedMessage.setIsRead(false);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, false);
            selectedFolder.incrementMessages();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSelectedMessage() {
        try {
            selectedMessage.getMessage().setFlag(Flags.Flag.DELETED, true);
            selectedFolder.getEmailMessages().remove(selectedMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



