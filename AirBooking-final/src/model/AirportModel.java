
package model;

/**
 * AirportModel.java
 * @author HANG Feifei, ZHU Hao, WANG Xi
 * Model of airport
 */
public class AirportModel {
    private String airport;
    private double tax;
    private double gst;

    public AirportModel(String airport, double tax, double gst) {
        this.airport = airport;
        this.tax = tax;
        this.gst = gst;
    }

    public AirportModel() {
        this("", 0, 0);
    }

    public String getAirport() {
        return this.airport;
    }

    public double getTax() {
        return this.tax;
    }

    public double getGst() {
        return this.gst;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }
}
