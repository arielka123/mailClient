package com.project;

import com.project.model.EmailAccount;
import javafx.scene.control.TreeItem;

public class EmailManager {

    //Folder handling

    private TreeItem<String> foldersRoot = new TreeItem<>("");

    public TreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        TreeItem<String> treeItem = new TreeItem<String>(emailAccount.getAddress());
        treeItem.setExpanded(true);  //children becoming visible
            treeItem.getChildren().add(new TreeItem<String>("Inbox"));
            treeItem.getChildren().add(new TreeItem<String>("Sent"));
            treeItem.getChildren().add(new TreeItem<String>("Folder"));
            treeItem.getChildren().add(new TreeItem<String>("Spam"));
        foldersRoot.getChildren().add(treeItem);
    }
}



