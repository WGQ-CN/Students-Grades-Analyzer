import java.io.BufferedReader;
import java.io.EOFException;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FileMenu {
	private final MenuItem miOpenGrades = new MenuItem("���ı��ļ��ɼ���"), miSaveGrades = new MenuItem("���Ϊ�ı��ļ��ɼ���"),
            miOpenObjectGrades = new MenuItem("�򿪶����ı��ļ��ɼ���"), miSaveObjectGrades = new MenuItem("���Ϊ�����ı��ļ��ɼ���"),
            miClean = new MenuItem("�������");

    private final Menu menu = new Menu("�ļ�");

    private final GradesAnalyzer gradesAnalyzer  = new GradesAnalyzer();
    private final GradesShower gradesShower = new GradesShower ();
    private final TextField tfCheck = new TextField();
    private final Text textPath = new Text();
    
    FileMenu() {
    	
    }
    
    public final MenuBar addMenu() {
        MenuBar mb = new MenuBar();

        mb.getMenus().add(menu);

        menu.getItems().addAll(miOpenGrades, miSaveGrades, miOpenObjectGrades, miSaveObjectGrades, miClean);

        miOpenGrades.setOnAction(e -> {
            openGrades();
        });

        miSaveGrades.setOnAction(e -> {
            saveGrades();
        });

        miOpenObjectGrades.setOnAction(e -> {
            openObjectGrades();
        });

        miSaveObjectGrades.setOnAction(e -> {
            try {
                saveObjectGrades();
            } catch (IOException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        miClean.setOnAction(e -> {
        	gradesAnalyzer.cleanData();
        	gradesShower.setTfData("", "", "", "", "", "", "", "", "", "", "", "", "");
            textPath.setText("");
        });
        return mb;
    }

    public GradesAnalyzer getGradesAnalyzer() {
        return gradesAnalyzer;
    }

    public GradesShower getGradesShower() {
        return gradesShower;
    }

    public HBox getCheckNode() {
        HBox hBox = new HBox();
        Text text = new Text("    ����ѧ�Ż�������ɼ����س���ѯ����������ͷ�ɽ�������    ");
        text.setFill(Color.RED);
        text.setFont(Font.font(15));
        hBox.getChildren().addAll(tfCheck, text);

        tfCheck.setPromptText("�����ֶ�,���س�����ѯ");
        tfCheck.setOnAction(e -> {
        	gradesAnalyzer.updateFilterGrades(tfCheck.getText());
            analyzeData();
        });
        return hBox;
    }

    public Text getTextPath() {
        textPath.setVisible(true);
        return textPath;
    }

    public void analyzeData() {
        if (gradesAnalyzer.getObservableList().isEmpty()) {
        	gradesShower.setTfData("", "", "", "", "", "", "", "", "", "", "", "", "");
            return;
        }
        gradesAnalyzer.calculate();
        gradesShower.setTfData(gradesAnalyzer.getHightestGrade(), gradesAnalyzer.getLowestGrade(), gradesAnalyzer.getAverageGrade(),
                gradesAnalyzer.getOutStandingNumber(), gradesAnalyzer.getOutstandingPercent(), gradesAnalyzer.getGoodNumber(),
                gradesAnalyzer.getGoodPercent(), gradesAnalyzer.getMediumNumber(), gradesAnalyzer.getMediumPercent(),
                gradesAnalyzer.getPassNumber(), gradesAnalyzer.getPassPercent(), gradesAnalyzer.getNotPassNumber(),
                gradesAnalyzer.getNotPassPercent());
    }

    public void openGrades() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("�ı��ļ��ɼ���(*.txt)", "*.txt"),
                new ExtensionFilter("�����ļ�", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null && file.getName().endsWith(".txt")) {
            try {
            	gradesAnalyzer.cleanData();
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String str = new String();
                while ((str = bufferedReader.readLine()) != null) {
                    if (str.trim().length() > 0) {
                    	gradesAnalyzer.addGrades(str);
                    }
                }
                read.close();

            } catch (FileNotFoundException ex) {
                System.out.println("File is not exit");
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            analyzeData();
            textPath.setText(file.getPath() + ", ��" + gradesAnalyzer.getPeopleSum() + "��");
        } else {
            System.out.println("���ļ�����");
        }
    }

    public void saveGrades() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("�ı��ļ��ɼ���(*.txt)", "*.txt"),
                new ExtensionFilter("�����ļ�", "*.*")
        );
        fileChooser.setInitialFileName("Grades.txt");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {

            try (PrintWriter output = new PrintWriter(file,"utf-8")) {

                for (StudentData data : gradesAnalyzer.getObservableList()) {
                    String str = data.getNumber() + "," + data.getName() + "," + data.getGrade();
                    output.println(str);
                }
                System.out.println("����ɹ�");
            } catch (FileNotFoundException ex) {
                System.out.println("�����ļ��ı��ɼ�������");
                ex.printStackTrace();
            }  catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void openObjectGrades() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("�����ı��ļ��ɼ���(*.score)", "*.score"),
                new ExtensionFilter("�����ļ�", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null && file.getName().endsWith(".score")) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));) {
            	gradesAnalyzer.cleanData();
                while (true) {
                    String str = (String) input.readObject();
                    gradesAnalyzer.addGrades(str);
                }
            } catch (EOFException e) {
            	
            }catch (FileNotFoundException ex) {
                System.out.println("File is not exit");
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            analyzeData();
            textPath.setText(file.getPath() + ", ��" + gradesAnalyzer.getPeopleSum() + "��");
        } else {
            System.out.println("�򿪶����ļ�����");
        }
    }

    public void saveObjectGrades() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("�ı��ļ��ɼ���(*.score)", "*.score"),
                new ExtensionFilter("�����ļ�", "*.*")
        );
        fileChooser.setInitialFileName("Grades.score");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));) {
                for (StudentData data : gradesAnalyzer.getObservableList()) {
                    String str = data.getNumber() + "," + data.getName() + "," + data.getGrade();
                    output.writeObject(str);
                }
                System.out.println("����ɹ�");
            } catch (FileNotFoundException ex) {
                System.out.println("��������ļ��ı��ɼ�������");
                ex.printStackTrace();
            }
        }
    }
}
