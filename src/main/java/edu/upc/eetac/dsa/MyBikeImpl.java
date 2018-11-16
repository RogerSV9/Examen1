package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class MyBikeImpl implements MyBike {
    final static Logger log = Logger.getLogger(MyBikeImpl.class.getName());
    private static MyBike instance;
    private List<Station> stations;
    private HashMap<String,User> users;

    private MyBikeImpl(){
        this.stations = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public static MyBike getInstance(){
        if(instance==null){
            instance = new MyBikeImpl();
        }
        return instance;
    }

    @Override
    public void addUser(String idUser, String name, String surname) {
        log.debug("Entering addUser");
        User user = new User(idUser,name,surname);
        this.users.put(idUser, user);
        log.info("addUser completed");
    }

    @Override
    public void addStation(String idStation, String description, int max, double lat, double lon) {
        log.debug("Entering addStation");
        Station station = new Station(idStation,description,max,lat,lon);
        this.stations.add(station);
        log.debug("addStation completed");
    }

    @Override
    public void addBike(String idBike, String description, double kms, String idStation) {
        log.debug("Entering addBike");
        Bike bike = new Bike(idBike,description,kms,idStation);
        for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                a.addBike(bike);
            }
        }
        log.debug("addBike completed");
    }

    @Override
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException{
        log.debug("Entering bikesByStationOrderbyKms");
        List<Bike> x = new ArrayList<>();
        LinkedList<Bike> ex = new LinkedList<>();
        Bike bike = null;
        for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                ex = a.getList();
                x.addAll(ex);
            }
        }
        Collections.sort(x, new Comparator<Bike>() {
            public int compare(Bike o1, Bike o2) {
                return (int)(o1.getKms()-o2.getKms());
            }
        });
        log.debug("bikesByStationOrderbyKms completed");
        //log.error("StationNotFound",StationNotFoundException);
        return x;
    }

    @Override
    public Bike getBike(String stationId, String userId) {
        log.debug("Entering getBike");
        List<Bike> x = new LinkedList<>();
        //x.addAll(this.stationbikes);
        Bike bike = null;
        Station station = null;
        User user = users.get(userId);
        for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(stationId)) {
                x = a.getList();
                bike = ((LinkedList<Bike>) x).pop();
            }
        }
        user.addBike(bike);
        log.debug("getBike completed");
        return bike;
    }

    @Override
    public List<Bike> bikesByUser(String userId) {
        log.debug("Entering bikesByUser");
        List<Bike> x = new ArrayList<>();
        User user = users.get(userId);
        x = user.getList();
        log.debug("bikesByUser completed");
        return x;
    }

    @Override
    public int numUsers() {
        return this.users.size();
    }

    @Override
    public int numStations() {
        return this.stations.size();
    }

    @Override
    public int numBikes(String idStation) {
        int num = 0;
        //x.addAll(this.stationbikes);
        Bike bike = null;
        for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                num = a.size();
            }
        }
        return num;
    }

    @Override
    public void clear() {
        users.clear();
        stations.clear();
        //stations = new Station[S];
    }
}
