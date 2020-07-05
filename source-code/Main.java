import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
        BorderPane primaryBorderPane=new BorderPane();
        
        BorderPane borderPane=new BorderPane();
        BorderPane leftPane = new BorderPane();
        BorderPane rightPane = new BorderPane();
        
        FileMenu fileMenu=new FileMenu();
        
        borderPane.setTop(fileMenu.addMenu());
        borderPane.setCenter(fileMenu.getCheckNode());
        
        Text table = new Text();
        table.setText("成绩单");
        table.setFont(Font.font(12));
        leftPane.setTop(table);
        leftPane.setCenter(fileMenu.getGradesAnalyzer());
        Text data = new Text();
        data.setText("数据分析");
        data.setFont(Font.font(12));
        rightPane.setTop(data);
        rightPane.setCenter(fileMenu.getGradesShower());
        
        primaryBorderPane.setTop(borderPane);
        primaryBorderPane.setLeft(leftPane);
        primaryBorderPane.setRight(rightPane);
        primaryBorderPane.setBottom(fileMenu.getTextPath());
        
        primaryStage.setScene(new Scene(primaryBorderPane,650,400));
        primaryStage.setTitle("成绩分析程序");
        primaryStage.show();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
}
