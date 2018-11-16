package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class Bike {

    String bikeId;
    String description;
    double kms;
    String idStation;

    public Bike(){}

    public Bike (String bikeId, String description, double kms, String idStation){
        this.bikeId = bikeId;
        this.description = description;
        this.kms = kms;
        this.idStation = idStation;
    }


    public void setbikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public String getBikeId() {
        return bikeId;
    }

}
