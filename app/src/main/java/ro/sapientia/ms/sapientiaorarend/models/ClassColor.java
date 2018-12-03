package ro.sapientia.ms.sapientiaorarend.models;

public class ClassColor {

    private String teacher;
    private String classcolor;


    public ClassColor(String teacher, String classcolor) {
        this.teacher = teacher;
        this.classcolor = classcolor;
    }


    public ClassColor() {
    }


    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClasscolor() {
        return classcolor;
    }

    public void setClasscolor(String classcolor) {
        this.classcolor = classcolor;
    }
}
