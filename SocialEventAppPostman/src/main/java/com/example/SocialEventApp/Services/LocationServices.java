package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.Location;
import com.example.SocialEventApp.Model.LocationModel;
import com.example.SocialEventApp.Repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServices {
    @Autowired
    private LocationRepository locationRepository;

    public List<LocationModel> getAllLocations() {
        List<LocationModel> locationModelList=new ArrayList<>();
        List<Location> locationList=locationRepository.findAll();
        locationList.forEach(location -> {
            LocationModel locationModel=new LocationModel();
            BeanUtils.copyProperties(location,locationModel);
            locationModelList.add(locationModel);
        });
        return locationModelList;
    }

    public LocationModel addLocation(LocationModel locationModel) {
        Location location=new Location();
        BeanUtils.copyProperties(locationModel,location);
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
}
