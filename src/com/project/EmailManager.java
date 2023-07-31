package com.project;

import com.project.controller.services.FetchFolderService;
import com.project.controller.services.FolderUpdaterService;
import com.project.model.EmailAccount;
import com.project.model.EmailTreeItem;
import javafx.scene.control.TreeItem;
import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    //Folder handling

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
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        treeItem.setExpanded(true);  //children becoming visible
        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), treeItem, folderlist);
        fetchFolderService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}



