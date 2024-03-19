package com.example.SocialEventAppSecurity.Services;



import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.EventCategory;
import com.example.SocialEventAppSecurity.Model.LocationModel;
import com.example.SocialEventAppSecurity.Repository.LocationRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.LocationServicesInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
       locations.forEach(location -> {
           LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
           locationModelList.add(locationModel);
       });
        return locationModelList;
    }

    public List<LocationModel> getAllLocationsByCapacity(int capacity) {
        List<Location> locationList=locationRepository.findAll();
        List<Location> locations=locationList.stream()
                .sorted(Comparator.comparingInt(location -> Math.abs(location.getCapacity() - capacity)))
                .filter(location -> location.getCapacity()>=capacity)
                .toList();
        List<LocationModel> locationModelList=new ArrayList<>();

        locations.forEach(location -> {
            LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }

    public List<LocationModel> getAllLocationsByDate(LocalDate date) {
        List<Location> locationList=locationRepository.findAll();

        List<Location> locations = locationList.stream()
                .filter(location ->location.getBookingEvents().isEmpty() || location.getBookingEvents().stream()
                        .noneMatch(bookingEvent -> bookingEvent.getLocalDate().equals(date)))
                .toList();

        List<LocationModel> locationModelList=new ArrayList<>();
        locations.forEach(location -> {
            LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }

    public List<LocationModel> getAllLocationsByForm(LocalDate date, EventCategory category, int capacity) {
        List<Location> locationList=locationRepository.findAll();
        List<Location> locations = locationList.stream()
                .filter(location ->
                        location.getCategoryList().contains(category) &&
                                location.getCapacity() >= capacity &&
                                (location.getBookingEvents().isEmpty() ||
                                        location.getBookingEvents().stream().noneMatch(bookingEvent -> bookingEvent.getLocalDate().equals(date))))
                .collect(Collectors.toList());
        List<LocationModel> locationModelList=new ArrayList<>();
        locations.forEach(location -> {
            LocationModel locationModel=entityToModelConversion.entityToLocationModel(location);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }



}
