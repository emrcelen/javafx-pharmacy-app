package FXMLPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Main;


import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPage (String fileName){
        try{

            URL fileURL = Main.class.getResource("/FXMLPage/"+fileName+".fxml");
            if(fileURL==null){
                throw new FileNotFoundException("FXML Dosyası Bulunamadı."); // Fxml file can't  be found.
            }
            view =  new FXMLLoader().load(fileURL);
        }
        catch (Exception e){
            System.out.println("ERR!\nAradığınız "+fileName+" dosyası FXMLPage paketinde bulunamadı."); //NO page "+ fileName +" please check FXMLPage.
        }
        return view;
    }
}
