package com.example.SocialEventAppSecurity.Services;



import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.EventCategory;
import com.example.SocialEventAppSecurity.Model.LocationModel;
import com.example.SocialEventAppSecurity.Repository.LocationRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.LocationServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationServices implements LocationServicesInterface {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    public List<LocationModel> getAllLocations() {
        List<LocationModel> locationModelList=new ArrayList<>();
        List<Location> locationList=locationRepository.findAll();
        locationList.forEach(location -> {
            LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }

    public LocationModel addLocation(LocationModel locationModel) {
        Location location=modelToEntityConversion.modelToLocationEntity(locationModel);
        locationRepository.save(location);
        locationModel.setLocationId(location.getLocationId());
        return locationModel;
    }

    public String deleteLocation(LocationModel locationModel) {
        Location location=locationRepository.findById(locationModel.getLocationId()).orElse(null);
        if(location==null){
            return "Location Not Registered Here";
        }
        locationRepository.deleteById(location.getLocationId());
        return "Location Removed From Your List";
    }

    public Location getLocationById(int lid) {
        return locationRepository.findById(lid).orElse(null);
    }

    public List<LocationModel> getAllLocationsByCategory(EventCategory category) {
        List<Location> locationList=locationRepository.findAll();
        List<LocationModel> locationModelList=new ArrayList<>();

        List<Location> locations= locationList.stream().filter(location -> location.getCategoryList().contains(category)).toList();
        locationModelList = locations.stream()
                .map(entityToModelConversion::entityToLocationModel)
                .collect(Collectors.toList());
        return locationModelList;
    }

    public List<LocationModel> getAllLocationsByCapacity(int capacity) {
        List<Location> locationList=locationRepository.findAll();
        List<Location> locations=locationList.stream()
                .sorted(Comparator.comparingInt(location -> Math.abs(location.getCapacity() - capacity)))
                .filter(location -> location.getCapacity()>=capacity)
                .toList();
        List<LocationModel> locationModelList=new ArrayList<>();

        locationModelList = locations.stream()
                .map(entityToModelConversion::entityToLocationModel)
                .collect(Collectors.toList());

        return locationModelList;
    }

    public List<LocationModel> getAllLocationsByDate(LocalDate date) {
        List<Location> locationList=locationRepository.findAll();

        List<Location> locations = locationList.stream()
                .filter(location ->location.getBookingEvents().isEmpty() || location.getBookingEvents().stream()
                        .noneMatch(bookingEvent -> bookingEvent.getLocalDate().equals(date)&&bookingEvent.isAdvancePaid()))
                .toList();

        return locations.stream()
                .map(entityToModelConversion::entityToLocationModel)
                .collect(Collectors.toList());
    }

    public List<LocationModel> getAllLocationsByForm(LocalDate date, EventCategory category, int capacity) {
        List<Location> locationList=locationRepository.findAll();
        List<Location> locations = locationList.stream()
                .filter(location ->
                        location.getCategoryList().contains(category) &&
                                location.getCapacity() >= capacity &&
                                (location.getBookingEvents().isEmpty() ||
                                        location.getBookingEvents().stream().noneMatch(bookingEvent -> bookingEvent.getLocalDate().equals(date)&&bookingEvent.isAdvancePaid())))
                .toList();

        return locations.stream()
                .map(entityToModelConversion::entityToLocationModel)
                .collect(Collectors.toList());
    }
@Override
    public List<BookingEventModel> getAllBookingsOfLocation(Location location) {
        List<BookingEvent> bookingEvents=location.getBookingEvents().stream().filter(BookingEvent::isAdvancePaid).toList();
        return bookingEvents.stream()
                .map(entityToModelConversion::entityToBookingModel).collect(Collectors.toList());
    }

    @Override
    public List<LocationModel> viewLocationBasedOnBooking() {
        List<Location> locationList=locationRepository.findAll();
        Optional<Location> maxLocation = locationList.stream()
                .reduce((location1, location2) ->
                        location1.getBookingEvents().size() > location2.getBookingEvents().size() ? location1 : location2);
        System.out.println(maxLocation.get().getVenueName());
        //List<Location> updatedList=locationList.stream().sorted(Comparator.comparingInt(location->location.getBookingEvents().size())).toList();
        List<Location> updatedList = new ArrayList<>(locationList.stream()
                .sorted(Comparator.comparingInt(location -> (int) location.getBookingEvents().stream().filter(BookingEvent::isAdvancePaid).count()))
                .toList());
         Collections.reverse(updatedList);
         return updatedList.stream().map(entityToModelConversion::entityToLocationModel).collect(Collectors.toList());
    }

    @Override
    public List<LocationModel> getAllLocationsOfOrganizer(EventOrganizer eventOrganizer) {
        List<LocationModel> locationModelList=new ArrayList<>();
        List<Location> locationList=locationRepository.findByEventOrganizer(eventOrganizer);
        locationList.forEach(location -> {
            LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }   }



