package ro.sapientia.ms.sapientiaorarend.models;

import java.util.Date;

public class Classes {
    private String teacher;
    private String material;
    private String clas;
    private Date start;
    private Date end;

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

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Classes(String teacher, String material, String clas, Date start, Date end) {

        this.teacher = teacher;
        this.material = material;
        this.clas = clas;
        this.start = start;
        this.end = end;
    }

    public Classes() {

    }
}
