public class Event {

    private int id;
    private String name;
    private float totalPrice;
    private String description;
    private String eventtype;
    private String dateStart;
    private String dateEnd;
    private String facilitator;
    private String xDescription;
    private int arrangementID;

    public Event(int id, String name, float totalPrice, String description, String eventtype, String dateStart, String dateEnd, String facilitator, String xDescription, int arrangementID) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.description = description;
        this.eventtype = eventtype;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.facilitator = facilitator;
        this.xDescription = xDescription;
        this.arrangementID = arrangementID;
    }
}
