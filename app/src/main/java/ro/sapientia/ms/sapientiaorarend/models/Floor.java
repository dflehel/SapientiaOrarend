package ro.sapientia.ms.sapientiaorarend.models;

public class Floor {

    private String name;
    private String url;


    public Floor() {
    }


    public Floor(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUri(String url) {
        this.url = url;
    }
}
