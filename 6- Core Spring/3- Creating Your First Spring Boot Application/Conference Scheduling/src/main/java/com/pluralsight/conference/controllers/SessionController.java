package com.pluralsight.conference.controllers;

import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getById(id);
    }

    @PostMapping
    public Session create(@RequestBody Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
