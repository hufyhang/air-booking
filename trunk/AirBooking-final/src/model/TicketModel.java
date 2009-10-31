
package model;

/**
 * TicketModel.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Model of ticket
 */
public class TicketModel {
    private String flightID;
    private String flightClass;
    private int tickets;
    private double cost;
    private int promotion;
    private double discount;
    private double tax;
    private double gst;

    public TicketModel(String flightID, String flightClass, int tickets, double cost, int promotion, double discount, double tax, double gst) {
        this.flightID = flightID;
        this.flightClass = flightClass;
        this.tickets = tickets;
        this.cost = cost;
        this.promotion = promotion;
        this.discount = discount;
        this.tax = tax;
        this.gst = gst;
    }

    public TicketModel() {
        this("", "", 0, 0.00, 0, 1.00, 0.00, 0.00);
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

    public int getPromotion() {
        return this.promotion;
    }

    public double getDiscount() {
        return this.discount;
    }

    public double getTax() {
        return this.tax;
    }

    public double getGst() {
        return this.gst;
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

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }
}
