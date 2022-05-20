package com.pluralsight.conference_scheduling.service;

import com.pluralsight.conference_scheduling.model.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();
}
