package com.project;

import com.project.controller.services.FetchFolderService;
import com.project.model.EmailAccount;
import com.project.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManager {

    //Folder handling

    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        treeItem.setExpanded(true);  //children becoming visible
        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), treeItem);
        fetchFolderService.start();

        foldersRoot.getChildren().add(treeItem);
    }
}



