package com.pluralsight.conference_scheduling.repository;

import com.pluralsight.conference_scheduling.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
