import javafx.beans.property.SimpleStringProperty;

public class StudentData {
	SimpleStringProperty number, name, grade;

    public StudentData(String number, String name, String grade) {
        this.number = new SimpleStringProperty(number);
        this.name = new SimpleStringProperty(name);
        this.grade = new SimpleStringProperty(grade);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }
}
