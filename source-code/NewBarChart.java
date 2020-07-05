import javafx.scene.chart.*;

public class NewBarChart extends BarChart {
    private XYChart.Series seriesOutstanding = new XYChart.Series(),seriesGood=new XYChart.Series(),seriesMedium=new XYChart.Series(),
            seriesPass=new XYChart.Series(),seriesNotPass=new XYChart.Series();
    
    final static String outstanding=new String("优秀(90-100)"),good=new String("良好(80-89)"),medium=new String("中等(70-79)"),
            pass=new String("及格(60-69)"),notPass=new String("不及格(0-60)");
    
    public NewBarChart(Axis xAxis, Axis yAxis) {
        super(xAxis,yAxis);
        seriesOutstanding.setName(outstanding);
        seriesGood.setName(good);
        seriesMedium.setName(medium);
        seriesPass.setName(pass);
        seriesNotPass.setName(notPass);
        setData("0","0","0","0","0");
        getData().addAll(seriesOutstanding,seriesGood,seriesMedium,seriesPass,seriesNotPass);
    }
    
    public void setData(String data1,String data2,String data3,String data4,String data5){
        seriesOutstanding.getData().setAll(new XYChart.Data(outstanding,Double.valueOf(data1)));
        seriesGood.getData().setAll(new XYChart.Data(good,Double.valueOf(data2)));
        seriesMedium.getData().setAll(new XYChart.Data(medium,Double.valueOf(data3)));
        seriesPass.getData().setAll(new XYChart.Data(pass,Double.valueOf(data4)));
        seriesNotPass.getData().setAll(new XYChart.Data<>(notPass,Double.valueOf(data5)));
    }
}
