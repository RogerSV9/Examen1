package edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyBikeImpl implements MyBike {

    private static MyBike instance;
    public List<Station> stations;
    public LinkedList<Bike> stationbikes;
    public HashMap<String,User> users;
    public LinkedList<Bike> userbikes;

    public MyBikeImpl(){
        stations = new ArrayList<>();
        stationbikes = new LinkedList<>();
        users = new HashMap<>();
        userbikes = new LinkedList<>();
    }

    public static MyBike getInstance(){
        if(instance==null){
            instance = new MyBikeImpl();
        }
        return instance;
    }

    @Override
    public void addUser(String idUser, String name, String surname) {
        User user = new User(idUser,name,surname);
        this.users.put(idUser,user);
    }

    @Override
    public void addStation(String idStation, String description, int max, double lat, double lon) {
        Station station = new Station(idStation,description,max,lat,lon);
        this.stations.add(station);
    }

    @Override
    public void addBike(String idBike, String description, double kms, String idStation) {

    }

    @Override
    public List<Bike> bikesByStationOrderByKms(String idStation) {
        return null;
    }

    @Override
    public Bike getBike(String stationId, String userId) {
        return null;
    }

    @Override
    public List<Bike> bikesByUser(String userId) {
        return null;
    }

    @Override
    public int numUsers() {
        return 0;
    }

    @Override
    public int numStations() {
        return 0;
    }

    @Override
    public int numBikes(String idStation) {
        return 0;
    }

    @Override
    public void clear() {

    }
}
