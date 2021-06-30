package io.intellisense.minute.controller;

import io.intellisense.minute.Messages;
import io.intellisense.minute.model.MinuteRequest;
import io.intellisense.minute.service.MinuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class MinuteController {

    @Autowired
    private MinuteService minuteService;

    @PostMapping(path = Messages.ENDPOINT_MINUTE, consumes = Messages.APPLICATION_JSON, produces = Messages.APPLICATION_JSON)
    public ResponseEntity<HashMap<String, Object>> calculateAverage(@Valid @RequestBody MinuteRequest request) {
        try {
            HashMap<String, Object> minuteResponse= minuteService.calculateAverage(request.getPeriod());
            return new ResponseEntity<>(minuteResponse, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
