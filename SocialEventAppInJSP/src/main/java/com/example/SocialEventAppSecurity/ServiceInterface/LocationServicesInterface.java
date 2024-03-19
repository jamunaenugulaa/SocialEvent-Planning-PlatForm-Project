package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.EventCategory;
import com.example.SocialEventAppSecurity.Model.LocationModel;

import java.time.LocalDate;
import java.util.List;

public interface LocationServicesInterface {
    public List<LocationModel> getAllLocations() ;
    public LocationModel addLocation(LocationModel locationModel) ;
    public String deleteLocation(LocationModel locationModel) ;
    public Location getLocationById(int lid) ;
    public List<LocationModel> getAllLocationsByCategory(EventCategory category) ;
    public List<LocationModel> getAllLocationsByCapacity(int capacity) ;
    public List<LocationModel> getAllLocationsByDate(LocalDate date) ;
    public List<LocationModel> getAllLocationsByForm(LocalDate date, EventCategory category, int capacity) ;

    }
