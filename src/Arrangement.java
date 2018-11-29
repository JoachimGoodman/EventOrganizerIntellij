import java.util.ArrayList;

public class Arrangement {

    private int id;
    private String name;
    private float totalPrice;
    private int duration;
    private int numberOfEvents;
    private int participants;
    private ArrayList<Event> listOfEvents;

    public Arrangement(int id, String name, float totalPrice, int duration, int numberOfEvents, int participations){
        listOfEvents = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.duration = duration;
        this.numberOfEvents = numberOfEvents;
        this.participants = participations;
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

    public void addEvents(ArrayList<Event> eventList){
        listOfEvents = eventList;
    }

    public void addEvent(Event event){
        listOfEvents.add(event);
    }

    public ArrayList<Event> getEvents(){
        return listOfEvents;
    }

    public String show(){
        return name +
                totalPrice +
                duration +
                numberOfEvents +
                participants;
    }
}