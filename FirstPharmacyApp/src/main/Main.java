package main;

import javafx.application.Application;

import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       GetFormManager formManager = new GetFormManager();
       formManager.getForm("loginPage");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
