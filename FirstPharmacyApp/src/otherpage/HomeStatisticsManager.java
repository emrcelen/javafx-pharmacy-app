package otherpage;

import database.DatabaseManager;

public class HomeStatisticsManager extends DatabaseManager {

    public  int getMemberCount(){
        login();
        int count = get("SELECT count (accountNo) as label FROM account");
        close();
        return  count;
    }
    public int getMedicineCount(){
        login();
        int count = get("SELECT count (medicineNo) as label FROM medicine");
        close();
        return  count;
    }

}
