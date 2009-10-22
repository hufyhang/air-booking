
package model;

/**
 *
 * @author HANG Feifei
 */
public class FlightModel {
    private String id;
    private String departure;
    private String destination;
    private String date;
    private double first;
    private double business;
    private double economy;
    private int promotion;
    private double discount;

    public FlightModel(String id, String departure, String destination, String date, double first, double business, double economy,
            int promotion, double discount) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.first = first;
        this.business = business;
        this.economy = economy;
        this.promotion = promotion;
        this.discount = discount;
    }

    public FlightModel() {
        this("", "", "", "", 0, 0, 0, 999, 1);
    }

    public String getID() {
        return this.id;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDate() {
        return this.date;
    }

    public double getFisrt() {
        return this.first;
    }

    public double getBusiness() {
        return this.business;
    }

    public double getEconomy() {
        return this.economy;
    }

    public int getPromotion() {
        return this.promotion;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public void setBusiness(double business) {
        this.business = business;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


