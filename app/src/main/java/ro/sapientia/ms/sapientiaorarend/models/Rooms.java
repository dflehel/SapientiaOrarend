package ro.sapientia.ms.sapientiaorarend.models;

public class Rooms {
    private String floor;
    private String room;

    public Rooms(String floor, String room) {
        this.floor = floor;
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "floor='" + floor + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
