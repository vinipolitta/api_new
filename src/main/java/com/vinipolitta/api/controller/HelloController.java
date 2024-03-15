package com.vinipolitta.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "oiie";
    }

    @PostMapping
    public String addString(@RequestBody String algo) {
        return algo;
    }
}
