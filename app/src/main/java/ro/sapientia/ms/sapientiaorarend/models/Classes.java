package ro.sapientia.ms.sapientiaorarend.models;

import java.io.Serializable;

public class Classes implements Serializable {
    private String teacher;
    private String material;
    private String classroom;
    private String start;
    private String end;

    public Classes() {

    }


    public Classes(String teacher, String material, String classroom, String start, String end) {
        this.teacher = teacher;
        this.material = material;
        this.classroom = classroom;
        this.start = start;
        this.end = end;
    }

    public String getTeacher() {

        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "teacher='" + teacher + '\'' +
                ", material='" + material + '\'' +
                ", classroom='" + classroom + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
