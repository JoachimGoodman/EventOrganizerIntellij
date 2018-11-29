public class Event {
    private String name;
    private float totalPrice;
    private String description;
    private String eventtype;
    private String dateStart;
    private String dateEnd;
    private String facilitator;
    private String xDescription;

    public Event(String name, float totalPrice, String description, String eventtype, String dateStart, String dateEnd, String facilitator, String xDescription)
    {
        this.name = name;
        this.totalPrice = totalPrice;
        this.description = description;
        this.eventtype = eventtype;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.facilitator = facilitator;
        this.xDescription = xDescription;
    }

    public String getEventName()
    {
        return name;
    }

    public String showEvent()
    {
        return name +
                totalPrice +
                description +
                eventtype +
                dateStart +
                dateEnd +
                facilitator +
                xDescription;
    }
}
