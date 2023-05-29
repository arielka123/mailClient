module EmailClient {
        requires javafx.fxml;
        requires javafx.controls;
        requires javafx.graphics;
        requires javafx.web;
        opens com.project;
        opens com.project.view;
        opens com.project.controller;
}