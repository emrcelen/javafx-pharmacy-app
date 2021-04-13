package login;

import database.DatabaseManager;
import javafx.scene.Node;
import main.GetFormManager;

import javax.swing.*;
import java.util.Random;

public class LoginSecurityCode {
    Random random = new Random();
    static int a,b;
    LoginSecurityCode(){
        a = random.nextInt(100);
        b = random.nextInt(50);
    }

    public int securityCheck(int mathResult,String securityCode,String nameCheck, String passCheck){
        String check;
        int loginCheck=0;
        check = String.valueOf(mathResult);
        if(securityCode.equalsIgnoreCase(check) == true){
            DatabaseManager db = new DatabaseManager();
            int veriable = db.getUser(nameCheck,passCheck);
            if(veriable==1){
                GetFormManager formManager = new GetFormManager();
                JOptionPane.showMessageDialog(null,"Kullanıcı Girişi Onaylandı. Yönlendiriliyorsunuz.","Bilgi Penceresi",JOptionPane.INFORMATION_MESSAGE);
                formManager.getForm("homePage");
                loginCheck = 1;
            }
            else
                JOptionPane.showMessageDialog(null,"Kullanıcı Bilgileri Yanlış.","Hata Penceresi",JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"Güvenlik kodunu yanlış girdiniz.","Hata",JOptionPane.ERROR_MESSAGE);
        }
        return  loginCheck;
    }

}
