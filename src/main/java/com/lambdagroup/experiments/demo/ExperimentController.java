package com.lambdagroup.experiments.demo;

import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExperimentController {

    @GetMapping(value = "/v1/experiments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Experiment[]> experimentsV1(){

        val experiment1 = new Experiment(1, "v1-phoenix-1");
        val experiment2 = new Experiment(2, "v1-bubble-2");

        val result = new Experiment[]{experiment1, experiment2};

        return ResponseEntity.ok(result);

    }

    @GetMapping(value = "/v2/experiments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Experiment[]> experimentsV2(){

        val experiment1 = new Experiment(1, "v2-phoenix-1");
        val experiment2 = new Experiment(2, "v2-bubble-2");

        val result = new Experiment[]{experiment1, experiment2};

        return ResponseEntity.ok(result);

    }
}
