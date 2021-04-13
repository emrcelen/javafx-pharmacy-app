package main;

import FXMLPage.FxmlLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GetFormManager {
    private double xcord;
    private double ycord;
    public void getForm(String fileName){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage(fileName);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); // --> Üst Kapatma barını çıkartıyor
        stage.setScene(new Scene(view));
        view.setOnMousePressed(event -> {
            xcord = event.getSceneX();
            ycord = event.getSceneY();
        });
        view.setOnMouseDragged(e ->{
            stage.setY(e.getScreenY()-ycord);
            stage.setX(e.getScreenX()-xcord);
        });
        stage.show();
    }
}
