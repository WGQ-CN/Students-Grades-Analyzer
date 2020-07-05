import gradesanalyzer.NewBarChart;
import gradesanalyzer.NewPieChart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.*;

public class GradesShower extends VBox {
	private TextField tfHighestGrade = new TextField(), tfLowestGrade = new TextField(), tfAverageGrade = new TextField(), tfOutStandingNumber = new TextField(),
            tfOutstandingPercent = new TextField(), tfGoodNumber = new TextField(), tfGoodPercent = new TextField(), tfMediumNumber = new TextField(),
            tfMediumPercent = new TextField(), tfPassNumber = new TextField(), tfPassPercent = new TextField(), tfNotPassNumber = new TextField(),
            tfNotPassPercent = new TextField();
	
	private Button btBarChart = new Button("显示柱形分析图"), btPieChart = new Button("显示饼形分析图");
	
	private GridPane gridPane = new GridPane();
	
	public GradesShower() {
        tfHighestGrade.setEditable(false);
        tfLowestGrade.setEditable(false);
        tfAverageGrade.setEditable(false);
        tfOutStandingNumber.setEditable(false);
        tfOutstandingPercent.setEditable(false);
        tfGoodNumber.setEditable(false);
        tfGoodPercent.setEditable(false);
        tfMediumNumber.setEditable(false);
        tfMediumPercent.setEditable(false);
        tfPassNumber.setEditable(false);
        tfPassPercent.setEditable(false);
        tfNotPassNumber.setEditable(false);
        tfNotPassPercent.setEditable(false);

        tfHighestGrade.setMaxWidth(110);
        tfLowestGrade.setMaxWidth(110);
        tfAverageGrade.setMaxWidth(110);
        tfOutStandingNumber.setMaxWidth(110);
        tfOutstandingPercent.setMaxWidth(60);
        tfGoodNumber.setMaxWidth(110);
        tfGoodPercent.setMaxWidth(60);
        tfMediumNumber.setMaxWidth(110);
        tfMediumPercent.setMaxWidth(60);
        tfPassNumber.setMaxWidth(110);
        tfPassPercent.setMaxWidth(60);
        tfNotPassNumber.setMaxWidth(110);
        tfNotPassPercent.setMaxWidth(60);
        
        gridPane.addRow(0, new Label("最高分"), tfHighestGrade);
        gridPane.addRow(1, new Label("最低分"), tfLowestGrade);
        gridPane.addRow(2, new Label("平均分"), tfAverageGrade);
        gridPane.addRow(3, new Label("优秀(90-100)"), tfOutStandingNumber, new Label("人,占"), tfOutstandingPercent, new Label("%"));
        gridPane.addRow(4, new Label("良好(80-89)"), tfGoodNumber, new Label("人,占"), tfGoodPercent, new Label("%"));
        gridPane.addRow(5, new Label("中等(70-79)"), tfMediumNumber, new Label("人,占"), tfMediumPercent, new Label("%"));
        gridPane.addRow(6, new Label("及格(60-69)"), tfPassNumber, new Label("人,占"), tfPassPercent, new Label("%"));
        gridPane.addRow(7, new Label("不及格(0-60)"), tfNotPassNumber, new Label("人,占"), tfNotPassPercent, new Label("%"));

        gridPane.setVgap(10);
        gridPane.setHgap(5);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btBarChart, btPieChart);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        setMargin(gridPane, new Insets(10));
        getChildren().addAll(gridPane, hBox);
        setSpacing(10);

        Stage pieStage = new Stage();
        NewPieChart pieChart = new NewPieChart();
        pieStage.setScene(new Scene(pieChart));
        btPieChart.setOnAction(e -> {
            if (tfOutStandingNumber.getText().trim().length()>0) {
                pieChart.setPieData(tfOutStandingNumber.getText(), tfGoodNumber.getText(), tfMediumNumber.getText(), tfPassNumber.getText(), tfNotPassNumber.getText());
                if (pieChart.getScene() != null) {
                    pieChart.getScene().setRoot(new VBox());
                }
                pieStage.setScene(new Scene(pieChart));
                pieStage.show();
            }
        });

        Stage barStage = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("成绩");
        yAxis.setLabel("人数/(人)");
        NewBarChart barChart = new NewBarChart(xAxis, yAxis);
        barStage.setScene(new Scene(barChart));
        btBarChart.setOnAction(e -> {
            if (tfOutStandingNumber.getText().trim().length()>0) {
                if (barChart.getScene() != null) {
                    barChart.getScene().setRoot(new VBox());
                }
                barChart.setData(tfOutStandingNumber.getText(), tfGoodNumber.getText(), tfMediumNumber.getText(), tfPassNumber.getText(), tfNotPassNumber.getText());
                barStage.setScene(new Scene(barChart));
                barStage.show();
            }
        });
    }
	
	public void setTfData(String hightestGrade, String lowesGrade, String averageGrade, String outStandingNumber, String outstandingPercent,
			String goodNumber, String goodPercent, String mediumNumber, String mediumPercent, String passNumber, String passPercent,
			String notPassNumber, String notPassPercent) {
        tfHighestGrade.setText(hightestGrade);
        tfLowestGrade.setText(lowesGrade);
        tfAverageGrade.setText(averageGrade);
        tfOutStandingNumber.setText(outStandingNumber);
        tfOutstandingPercent.setText(outstandingPercent);
        tfGoodNumber.setText(goodNumber);
        tfGoodPercent.setText(goodPercent);
        tfMediumNumber.setText(mediumNumber);
        tfMediumPercent.setText(mediumPercent);
        tfPassNumber.setText(passNumber);
        tfPassPercent.setText(passPercent);
        tfNotPassNumber.setText(notPassNumber);
        tfNotPassPercent.setText(notPassPercent);
    }
}
