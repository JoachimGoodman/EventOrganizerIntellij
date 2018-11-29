import java.util.ArrayList;

public class Arrangement {

    private int id;
    private String name;
    private double totalPrice;
    private int duration;
    private int numberOfEvents;
    private int participants;

    public Arrangement(int id, String name, double totalPrice, int duration, int numberOfEvents, int participations){
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.duration = duration;
        this.numberOfEvents = numberOfEvents;
        this.participants = participations; // comment
    }
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getParticipants(){
        return participants;
    }

    public String show(){
        return name +
                totalPrice +
                duration +
                numberOfEvents +
                participants;
    }
}