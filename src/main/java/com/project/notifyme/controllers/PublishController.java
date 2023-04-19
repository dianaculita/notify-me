package com.project.notifyme.controllers;

import com.project.notifyme.services.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class PublishController {

    private final PublishService publishService;

    @PostMapping
    public ResponseEntity<?> sendUpcomingCompetitions() {
        return new ResponseEntity<>(publishService.sendUpcomingCompetitions(), HttpStatus.OK);
    }

}
