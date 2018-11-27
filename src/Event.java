public class Event {
    String name;
    float totalPrice;
    String description;
    String eventtype;
    String dateStart;
    String dateEnd;
    String facilitator;
    String xDescription;

    public Event(String name, float totalPrice, String description, String eventtype, String dateStart, String dateEnd, String facilitator, String xDescription) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.description = description;
        this.eventtype = eventtype;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.facilitator = facilitator;
        this.xDescription = xDescription;
    }
}
