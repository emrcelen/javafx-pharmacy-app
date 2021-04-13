package otherpage;

import database.DatabaseManager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class AddMedicineManager {
    Random random = null;
    ArrayList list = null;
    int ver;
    String code;
    DatabaseManager db = null;

    public String createMedicineCode() {
        code = "";
        random = new Random();
        list = new ArrayList();
        for (int i = 0; i < 16; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 12) {
                ver = random.nextInt(26) + 65;
                if (list.contains((char) ver) == true)
                    ver = random.nextInt(26) + 65;
                if (list.contains((char) ver) == true)
                    ver = random.nextInt(26) + 65;
                list.add((char) ver);
            } else {
                ver = random.nextInt(25);
                if (ver > 9) {
                    ver = random.nextInt(26) + 65;
                    if (list.contains((char) ver) == true)
                        ver = random.nextInt(26) + 65;
                    if (list.contains((char) ver) == true)
                        ver = random.nextInt(26) + 65;
                    list.add((char) ver);
                } else {
                    if (list.contains(ver) == true)
                        ver = random.nextInt(10);
                    if (list.contains(ver) == true)
                        ver = random.nextInt(7);
                    list.add(ver);
                }
            }
        }
        int ayrac = 0;
        for (var num : list) {
            code += num;
            if (ayrac == 3 || ayrac == 7 || ayrac == 11)
                code += "-";
            ayrac++;
        }
        list.clear();
        return code;

    }

    public int countMedicine(String medicineName){
        db = new DatabaseManager();
        db.login();
        int value = db.get("SELECT count (producerName) as label FROM medicine WHERE producerName='"+medicineName+"'");
        db.close();
        return value;
    }
    public String companyCode(String medicineName){
        db = new DatabaseManager();
        db.login();
        String value = db.getInfo("SELECT (producerNo) as label FROM producer WHERE producerName='"+medicineName+"'");
        db.close();
        return value;
    }


}
