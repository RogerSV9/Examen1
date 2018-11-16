package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class MyBikeImpl implements MyBike {
    final static Logger log = Logger.getLogger(MyBikeImpl.class.getName());
    private static MyBike instance;
    private int numstations;
    private Station stations[];
    //private List<Station> stations;
    private HashMap<String,User> users;

    private MyBikeImpl(){
        //this.stations = new ArrayList<>();
        this.users = new HashMap<>();
        numstations = 0;
        this.stations = new Station[S];
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
        log.info("number of stations"+numstations);
        Station station = new Station(idStation,description,max,lat,lon);
        this.stations[this.numstations] = station;
        this.numstations++;
        //Station station = new Station(idStation,description,max,lat,lon);
        //this.stations.add(station);
        log.debug("addStation completed");
    }

    @Override
    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException {
        log.debug("Entering addBike");
        //Bike bike = new Bike(idBike,description,kms,idStation);
        /*for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                a.addBike(bike);
            }
        }*/
        Station station = null;
        for(int i = 0; i<this.numstations; i++) {
            if(idStation.equals(this.stations[i].idStation)){
                station = this.stations[i];
            }
        }


        if(station != null) {
            LinkedList<Bike> bikes = station.getList();
            if (bikes.size() < station.max) {
                station.addBike(new Bike(idBike, description, kms, idStation));
            }
            else {
                log.error("The station is full");
                throw new StationFullException();
            }
        }
        else{
            log.error("Station doesn't exist");
            throw new StationNotFoundException();
        }

        log.debug("addBike completed");
    }

    @Override
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException{
        log.debug("Entering bikesByStationOrderbyKms");
        List<Bike> x = new ArrayList<>();
        LinkedList<Bike> ex = new LinkedList<>();
        Bike bike = null;
        Station station = null;
        /*for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                ex = a.getList();
                x.addAll(ex);
            }
        }*/
        for(int i = 0; i<this.numstations; i++) {
            if(idStation.equals(this.stations[i].idStation)){
                station = this.stations[i];
            }
        }
        /*Collections.sort(x, new Comparator<Bike>() {
            public int compare(Bike o1, Bike o2) {
                return (int)(o1.getKms()-o2.getKms());
            }
        });*/
        if(station != null) {
            x = station.getList();

            Collections.sort(x, new Comparator<Bike>() {
                @Override
                public int compare(Bike o1, Bike o2) {
                    return (int) (o1.getKms() - o2.getKms());
                }
            });
        }
        else{
            log.error("The station doesn't exist");
            throw new StationNotFoundException();
        }
        log.debug("bikesByStationOrderbyKms completed");
        //log.error("StationNotFound",StationNotFoundException);
        return x;
    }

    @Override
    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException {
        log.debug("Entering getBike");
        List<Bike> x = new LinkedList<>();
        //x.addAll(this.stationbikes);
        Bike bike = null;
        Station station = null;
        User user = users.get(userId);
        /*for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(stationId)) {
                x = a.getList();
                bike = ((LinkedList<Bike>) x).pop();
            }
        }*/
        for(int i = 0; i<this.numstations; i++) {
            if(stationId.equals(this.stations[i].idStation)){
                station = this.stations[i];
            }
        }

        if(user != null) {
            if (station != null) {
                bike = station.getList().pop();
            }
            else {
                log.error("The station doesn't exist");
                throw new StationNotFoundException();
            }
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        user.addBike(bike);
        log.debug("getBike completed");
        return bike;
    }

    @Override
    public List<Bike> bikesByUser(String userId) throws UserNotFoundException{
        log.debug("Entering bikesByUser");
        List<Bike> x;
        User user = users.get(userId);
        x = user.getList();
        if(user!=null){
            x = user.getList();
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.debug("bikesByUser completed");
        return x;
    }

    @Override
    public int numUsers() {
        return this.users.size();
    }

    @Override
    public int numStations() {
        return this.numstations;
    }

    @Override
    public int numBikes(String idStation) throws StationNotFoundException{
        int num = 0;
        //x.addAll(this.stationbikes);
        Bike bike = null;
        /*for (ListIterator<Station> iter = this.stations.listIterator(); iter.hasNext(); ) {
            Station a = iter.next();
            if (a.getIdStation().equals(idStation)) {
                num = a.size();
            }
        }*/
        Station station = null;
        for(int i = 0; i<this.numstations; i++) {
            if(idStation.equals(this.stations[i].idStation)){
                station = this.stations[i];
            }
        }
        if (station != null){
            num = station.getList().size();
        }
        else{
            throw new StationNotFoundException();
        }
        return num;
    }

    @Override
    public void clear() {
        users.clear();
        //stations.clear();
        stations = new Station[S];
        instance = null;
    }
}
