package otherpage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import main.GetFormManager;


import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private Label registerMemberCount;
    @FXML
    private Label medicineCount;
    @FXML
    private Label moderatorLabel;
    @FXML
    private CategoryAxis xTable;
    @FXML
    private NumberAxis yTable;
    @FXML
    private BarChart<?, ?> demoChart;

    //Action Event --> Methods:
    public void closeButtonAction(ActionEvent event){
        System.exit(0);
    }
    // Action Event --> Page Methods:
    GetFormManager formManager = new GetFormManager();
    public void pharmacologyButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("pharmacologyPage");
    }
    public void addMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("addMedicinePage");
    }
    public void searchMedicineButtonAction(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide(); // Bir önceki sayfayı gizler.
        formManager.getForm("searchMedicinePage");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Graphic
        XYChart.Series demoSet = new XYChart.Series<>();
        demoSet.setName("Demo Product 1");
        demoSet.getData().add(new XYChart.Data("Demo One",5000));
        demoSet.getData().add(new XYChart.Data("Demo Two",7000));
        demoSet.getData().add(new XYChart.Data("Demo Three",15000));
        demoSet.getData().add(new XYChart.Data("Demo Four",9000));
        XYChart.Series demoSet2 = new XYChart.Series<>();
        demoSet2.setName("Demo Product 2");
        demoSet2.getData().add(new XYChart.Data("Demo One",12000));
        demoSet2.getData().add(new XYChart.Data("Demo Two",3000));
        demoSet2.getData().add(new XYChart.Data("Demo Three",9000));
        demoSet2.getData().add(new XYChart.Data("Demo Four",29000));
        demoChart.getData().addAll(demoSet);
        demoChart.getData().addAll(demoSet2);
        // Count Statistics
        HomeStatisticsManager homeStatisticsManager = new HomeStatisticsManager();
        int memberCount = homeStatisticsManager.getMemberCount();
        int mediCount = homeStatisticsManager.getMedicineCount();
        registerMemberCount.setText(String.valueOf(memberCount));
        medicineCount.setText(String.valueOf(mediCount));
    }
}
