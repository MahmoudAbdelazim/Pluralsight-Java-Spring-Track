package com.pluralsight.conference.repositories;

import com.pluralsight.conference.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    public Speaker getPersonByAgeGreaterThan15(String name);
}
