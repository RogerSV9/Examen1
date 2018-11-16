package edu.upc.eetac.dsa.services;
import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static edu.upc.eetac.dsa.MyBikeImpl.getInstance;


@Api(value = "/services", description = "Endpoint to Products Service")
@Path("/services")
@Singleton
public class RESTservice {
    @GET
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @ApiOperation(value = "get Bikes from a Station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Pedido not found")
    })
    @Path("/bikebyStation/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByStationOrderByKms(@PathParam("name") String name) throws UserNotFoundException {
        List<Bike> bikes;
        try {
            bikes = getInstance().bikesByStationOrderByKms(name);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes){};
            return Response.status(201).entity(entity).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
    @POST
    @ApiOperation(value = "New User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
    })

    @Path("/newuser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        String iduser = user.getIdUser();
        String name = user.getName();
        String surname = user.getSurname();
        getInstance().addUser(iduser,name,surname);
        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "New Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Bike.class),
    })

    @Path("/newbike/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newBike(Bike bike){
        String idBike = bike.getBikeId();
        String description = bike.getDescription();
        double kms = bike.getKms();
        String idStation = bike.getIdStation();
        try {
            getInstance().addBike(idBike,description,kms,idStation);
            return Response.status(201).build();
        } catch (StationFullException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }

    }
    @POST
    @ApiOperation(value = "New Station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Station.class),
    })

    @Path("/newstation/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStation(Station station) {
        String idStation = station.getIdStation();
        String description = station.getDescription();
        int max = station.getMax();
        double lat = station.getLat();
        double lon = station.getLon();
        getInstance().addStation(idStation,description,max,lat,lon);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Pedido not found")
    })
    @Path("/{name}/station/{station}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBike(@PathParam("name") String name,@PathParam("station") String station){
        Bike bike;
        try {
            bike = getInstance().getBike(station,name);
            return Response.status(201).entity(bike).build();
        } catch (StationNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
    @GET
    @ApiOperation(value = "get Bikes by User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Pedido not found")
    })
    @Path("/bikesbyuser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByUser(@PathParam("name") String name) throws UserNotFoundException {
        List<Bike> bikes;
        bikes = getInstance().bikesByUser(name);
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes){};
        return Response.status(201).entity(entity).build();
    }
}
