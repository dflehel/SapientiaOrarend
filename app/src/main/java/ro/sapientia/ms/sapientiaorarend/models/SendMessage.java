package ro.sapientia.ms.sapientiaorarend.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.TreeSet;

public class SendMessage {

    private String sender;
    private String content;
    private TreeSet<String> recivers;
    private Long timestamp;
    private DatabaseReference databaseReference;


    public SendMessage(String sender, String content, TreeSet<String> recivers, long timestamp) {
        this.sender = sender;
        this.content = content;

        this.recivers = new TreeSet<>();
        for (String s : recivers) {
            this.recivers.add(s.replace(" ", "/"));
        }
        this.timestamp = timestamp;
    }

    public SendMessage(String sender, String content, TreeSet<String> recivers) {
        this.sender = sender;
        this.content = content;
        this.recivers = recivers;
    }

    public SendMessage() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public TreeSet<String> getRecivers() {
        return recivers;
    }

    public void setRecivers(TreeSet<String> recivers) {
        this.recivers = recivers;
    }

    public void sendingmesage() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("/messages");
        for (String s : this.recivers) {
            this.databaseReference.child(s).child(this.timestamp.toString()).child("sender").setValue(this.sender);
            this.databaseReference.child(s).child(this.timestamp.toString()).child("content").setValue(this.content);
        }
    }
}
