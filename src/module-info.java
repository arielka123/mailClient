module EmailClient {
        requires javafx.fxml;
        requires javafx.controls;
        requires javafx.graphics;
        requires javafx.web;
        requires activation;
        requires java.mail;

        opens com.project;
        opens com.project.view;
        opens com.project.controller;
        opens com.project.model;
}