package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class User {
    String idUser;
    String name;
    String surname;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LinkedList<Bike> bikes;


    public User(){}

    public User(String idUser, String name, String surname){
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.bikes = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int size (){
        return bikes.size();
    }
    public LinkedList<Bike> getList(){
        return this.bikes;
    }
    public void addBike(Bike bike){
        bikes.add(bike);
    }

}
