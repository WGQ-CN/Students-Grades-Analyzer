import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class GradesAnalyzer extends TableView {
	private TableColumn tcNumber = new TableColumn("学号"), tcName = new TableColumn("姓名"), tcGrade = new TableColumn("成绩");

    private final ObservableList<StudentData> olOriginalGrades = FXCollections.observableArrayList(), olFilterGrades = FXCollections.observableArrayList();
    private double hightestGrade = 0, lowesGrade = 0, averageGrade = 0, outStandingNumber = 0, outstandingPercent = 0, goodNumber = 0, goodPercent = 0,
            mediumNumber = 0, mediumPercent = 0, passNumber = 0, passPercent = 0, notPassNumber = 0, notPassPercent = 0, outstandingSum = 0, goodSum = 0,
            mediumSum = 0, passSum = 0, notPassSum = 0,peopleSum=0;

    public GradesAnalyzer() {
        this(",".split("[,]"));
    }

    public GradesAnalyzer(String[] strGrades) {
        getColumns().addAll(tcNumber, tcName, tcGrade);
        tcNumber.setCellValueFactory(new PropertyValueFactory<StudentData, String>("number"));
        tcName.setCellValueFactory(new PropertyValueFactory<StudentData, String>("name"));
        tcGrade.setCellValueFactory(new PropertyValueFactory<StudentData, String>("grade"));
        tcNumber.setMinWidth(110);
        tcName.setMinWidth(110);
        tcGrade.setMinWidth(110);
        addGrades(strGrades);
        setItems(olFilterGrades);
    }

    public void addGrades(String[] strGrades) {
        for (String str : strGrades) {
            addGrades(str);
        }
    }

    public void addGrades(String strGrade) {
        if(strGrade.trim().length()<1)
            return ;
        String[] strTableData = strGrade.trim().split("[,]");
        olFilterGrades.add(new StudentData(strTableData[0], strTableData[1], strTableData[2]));
        olOriginalGrades.add(new StudentData(strTableData[0], strTableData[1], strTableData[2]));
    }

    public void cleanData() {
        olFilterGrades.clear();
        olOriginalGrades.clear();
    }

    public void setZero() {
        outStandingNumber = 0;
        goodNumber = 0;
        mediumNumber = 0;
        passNumber = 0;
        notPassNumber = 0;
        outstandingSum = 0;
        goodSum = 0;
        mediumSum = 0;
        passSum = 0;
        notPassSum = 0;
        peopleSum=0;
    }

    public void calculate() {
        setZero();
        hightestGrade = 0;
        lowesGrade = 100;
        for (StudentData data : olFilterGrades) {
            double grade = Double.valueOf(data.getGrade());
            if (grade >= 90) {
                outStandingNumber++;
                outstandingSum += grade;
            } else if (grade >= 80) {
                goodNumber++;
                goodSum += grade;
            } else if (grade >= 70) {
                mediumNumber++;
                mediumSum += grade;
            } else if (grade >= 60) {
                passNumber++;
                passSum += grade;
            } else {
                notPassNumber++;
                notPassSum += grade;
            }
            if (grade > hightestGrade) {
                hightestGrade = grade;
            }
            if (grade < lowesGrade) {
                lowesGrade = grade;
            }
        }
        peopleSum = outStandingNumber + goodNumber + mediumNumber + passNumber + notPassNumber;
        averageGrade = (outstandingSum + goodSum + mediumSum + passSum + notPassSum) / peopleSum;
        outstandingPercent = outStandingNumber * 100.0 / peopleSum;
        goodPercent = goodNumber * 100.0 / peopleSum;
        mediumPercent = mediumNumber * 100.0 / peopleSum;
        passPercent = passNumber * 100.0 / peopleSum;
        notPassPercent = notPassNumber * 100.0 / peopleSum;
    }
    
    public void updateFilterGrades(String reserveString) {
        olFilterGrades.clear();
        if (reserveString.trim().length() < 1) {
            olFilterGrades.addAll(olOriginalGrades);
            System.out.println("字段为空");
        } else {
            for (StudentData data : olOriginalGrades) {
                if (data.getNumber().contains(reserveString.trim()) || data.getName().contains(reserveString.trim())
                        || data.getGrade().contains(reserveString.trim())) {
                    olFilterGrades.add(data);
                }
            }
        }
    }
    
    public ObservableList<StudentData> getObservableList() {
        return olFilterGrades;
    }
    
    public String getHightestGrade() {
        return String.format("%.2f", hightestGrade);
    }

    public String getLowestGrade() {
        return String.format("%.2f", lowesGrade);
    }

    public String getAverageGrade() {
        return String.format("%.2f", averageGrade);
    }

    public String getOutStandingNumber() {
        return String.format("%.0f", outStandingNumber);
    }

    public String getOutstandingPercent() {
        return String.format("%.2f", outstandingPercent);
    }

    public String getGoodNumber() {
        return String.format("%.0f", goodNumber);
    }

    public String getGoodPercent() {
        return String.format("%.2f", goodPercent);
    }

    public String getMediumNumber() {
        return String.format("%.0f", mediumNumber);
    }

    public String getMediumPercent() {
        return String.format("%.2f", mediumPercent);
    }

    public String getPassNumber() {
        return String.format("%2.0f", passNumber);
    }

    public String getPassPercent() {
        return String.format("%2.2f", passPercent);
    }

    public String getNotPassNumber() {
        return String.format("%2.0f", notPassNumber);
    }

    public String getNotPassPercent() {
        return String.format("%2.2f", notPassPercent);
    }
    
    public int getPeopleSum(){
        return olOriginalGrades.size();
    }
}