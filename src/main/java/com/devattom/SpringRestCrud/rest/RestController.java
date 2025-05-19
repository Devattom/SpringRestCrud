package com.devattom.SpringRestCrud.rest;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/home")
    public String getStudents() {
        return "home";
    }
}
