package com.pluralsight.conference_scheduling.repository;

import com.pluralsight.conference_scheduling.model.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {

    @Autowired
    private Calendar cal;

    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Mahmoud");
        speaker.setLastName("Abdelazim");
        System.out.println("Cal: " + cal.getTime());
        speakers.add(speaker);
        return speakers;
    }
}
