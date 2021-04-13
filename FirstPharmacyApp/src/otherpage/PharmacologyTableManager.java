package otherpage;

public class PharmacologyTableManager {
    String medicineNo, medicineName, companyName, medicineMoney;

    PharmacologyTableManager(String medicineNo, String medicineName,String companyName, String medicineMoney){
        this.medicineNo = medicineNo;
        this.medicineName = medicineName;
        this.companyName = companyName;
        this.medicineMoney = medicineMoney;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setMedicineMoney(String medicineMoney) {
        this.medicineMoney = medicineMoney;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicineNo(String medicineNo) {
        this.medicineNo = medicineNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMedicineMoney() {
        return medicineMoney;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineNo() {
        return medicineNo;
    }
}
