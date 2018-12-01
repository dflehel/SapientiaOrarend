package ro.sapientia.ms.sapientiaorarend.models;

public class Rooms {
    private String floor;
    private String room;

    public String getFloor() {
        return floor;
    }

    public String getRoom() {
        return room;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Rooms(String floor, String room) {
        this.floor = floor;
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
