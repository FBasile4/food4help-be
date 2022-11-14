package com.f4h.food4help.controllers;

import com.f4h.food4help.models.User;
import com.f4h.food4help.repositories.CharityCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")//tutti i metodi che verranno dopo devono partire da questa baseline
public class UserController {
    @Autowired //riferimento al mio repository automatico svolto da spring core
    CharityCompanyRepository userRepository;

    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All User...");

        userRepository.deleteAll();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }




}
