import javafx.scene.chart.*;
import javafx.collections.FXCollections;

public class NewPieChart extends PieChart {
    public NewPieChart() {
        super(FXCollections.observableArrayList(
                new PieChart.Data("优秀(90-100)", 5),
                new PieChart.Data("良好(80-89)", 5),
                new PieChart.Data("中等(70-79)", 5),
                new PieChart.Data("及格(60-69)", 5),
                new PieChart.Data("不及格(0-60)", 5)));
    }
    
    public void setPieData(String data1,String data2,String data3,String data4,String data5){
        super.setData(FXCollections.observableArrayList(
                new PieChart.Data("优秀(90-100)", Double.valueOf(data1)),
                new PieChart.Data("良好(80-89)", Double.valueOf(data2)),
                new PieChart.Data("中等(70-79)", Double.valueOf(data3)),
                new PieChart.Data("及格(60-69)", Double.valueOf(data3)),
                new PieChart.Data("不及格(0-60)", Double.valueOf(data5))));
    }
}