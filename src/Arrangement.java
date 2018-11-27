public class Arrangement {

    String name;
    double totalPrice;
    int duration;
    int numberOfEvents;
    int participants;

    public Arrangement(String name, double totalPrice, int duration, int numberOfEvents, int participations){
        this.name = name;
        this.totalPrice = totalPrice;
        this.duration = duration;
        this.numberOfEvents = numberOfEvents;
        this.participants = participations; // comment
    }
    public String getName(){
        return name;
    }
    public String show(){
        return name +
                totalPrice +
                duration +
                numberOfEvents +
                participants;
    }
}