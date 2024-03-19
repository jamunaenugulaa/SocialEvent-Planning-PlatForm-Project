package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.EventOrganizer;
import com.example.SocialEventApp.Model.EventOrganizerModel;
import com.example.SocialEventApp.Repository.OrganizerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServices {
    @Autowired
    private OrganizerRepository organizerRepository;


    public EventOrganizerModel saveOrganizer(EventOrganizerModel organizerModel) {
        EventOrganizer organizer=organizerRepository.findById(organizerModel.getId()).orElse(null);
        if(organizer==null){
            EventOrganizer eventOrganizer=new EventOrganizer();
            BeanUtils.copyProperties(organizerModel,eventOrganizer);
            organizerRepository.save(eventOrganizer);
            return organizerModel;

        }
        return null;
    }
}
