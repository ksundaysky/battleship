package com.wkbp.battleship.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wiktor Rup
 */


@RestController
class TestController {

    @GetMapping("/testEndpoint")
    public List<Integer> getOccupiedAuditoriums() {
        return new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    }
}
