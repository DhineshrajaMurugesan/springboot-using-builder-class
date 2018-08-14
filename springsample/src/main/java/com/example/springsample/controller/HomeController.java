package com.example.springsample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * HomeController
 */
@RequestMapping(value="/")
public class HomeController {


public void get() {
    System.out.println("helloWorld");
}
}