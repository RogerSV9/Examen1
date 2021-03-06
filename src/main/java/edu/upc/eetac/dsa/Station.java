package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class Station {
    String idStation;
    String description;
    int max;
    double lat;
    double lon;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LinkedList<Bike> bikes;
    public Station(){}
    public Station(String idStation, String description, int max,double lat,double lon){
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        bikes = new LinkedList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    public int size (){
        return bikes.size();
    }
    public void addBike(Bike bike){
        bikes.add(bike);
    }
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public LinkedList<Bike> getList(){
        return bikes;
    }

}
