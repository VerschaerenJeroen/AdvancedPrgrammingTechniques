package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean FoodProvided;
    private boolean Indoor;
    private boolean Outdoor;
    private boolean FreeParkingAvailable;
    private String city;
    private int distanceFromPublicTransportInKm;

    public Venue(int id, String venueName, String linkMoreInfo, int capacity, boolean FoodProvided, boolean Indoor, boolean Outdoor, boolean FreeParkingAvailable, String city, int distanceFromPublicTransportInKm) {
        this.id = id;
        this.venueName = venueName;
        this.linkMoreInfo = linkMoreInfo;
        this.capacity = capacity;
        this.FoodProvided = FoodProvided;
        this.Indoor = Indoor;
        this.Outdoor = Outdoor;
        this.FreeParkingAvailable = FreeParkingAvailable;
        this.city = city;
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
    }

    public Venue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFoodProvided() {
        return FoodProvided;
    }

    public void setFoodProvided(boolean foodProvided) {
        FoodProvided = foodProvided;
    }

    public boolean isIndoor() {
        return Indoor;
    }

    public void setIndoor(boolean indoor) {
        Indoor = indoor;
    }

    public boolean isOutdoor() {
        return Outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        Outdoor = outdoor;
    }

    public boolean isFreeParkingAvailable() {
        return FreeParkingAvailable;
    }

    public void setFreeParkingAvailable(boolean freeParkingAvailable) {
        FreeParkingAvailable = freeParkingAvailable;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDistanceFromPublicTransportInKm() {
        return distanceFromPublicTransportInKm;
    }

    public void setDistanceFromPublicTransportInKm(int distanceFromPublicTransportInKm) {
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
    }
}
