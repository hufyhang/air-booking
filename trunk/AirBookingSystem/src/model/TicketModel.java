
package model;

/**
 *
 * @author HANG Feifei
 */
public class TicketModel {
    private String flightID;
    private String flightClass;
    private int tickets;
    private double cost;

    public TicketModel(String flightID, String flightClass, int tickets, double cost) {
        this.flightID = flightID;
        this.flightClass = flightClass;
        this.tickets = tickets;
        this.cost = cost;
    }

    public TicketModel() {
        this("", "", 0, 0.00);
    }

    public String getFlightID() {
        return this.flightID;
    }

    public String getFlightClass() {
        return this.flightClass;
    }

    public int getTickets() {
        return this.tickets;
    }

    public double getCost() {
        return this.cost;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
