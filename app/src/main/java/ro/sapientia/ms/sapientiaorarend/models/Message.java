package ro.sapientia.ms.sapientiaorarend.models;

import java.util.ArrayList;

public class Message {

    private String sender;
    private String content;
    private ArrayList<String> recivers;


    public Message(String sender, String content, ArrayList<String> recivers) {
        this.sender = sender;
        this.content = content;
        this.recivers = recivers;
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getRecivers() {
        return recivers;
    }

    public void setRecivers(ArrayList<String> recivers) {
        this.recivers = recivers;
    }
}
