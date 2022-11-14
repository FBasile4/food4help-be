package com.f4h.food4help.controllers;

import com.f4h.food4help.models.CharityCompany;
import com.f4h.food4help.repositories.CharityCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")//tutti i metodi che verranno dopo devono partire da questa baseline
public class CharityCompanyController {
    @Autowired //riferimento al mio repository automatico svolto da spring core
    CharityCompanyRepository userCharityRepository;

    @GetMapping(value = "/home/charity/login/{userpwd}")
    public CharityCompany loginCharity(@PathVariable String userpwd) {

        if(!userpwd.contains("_")) {
            return null;
        }

        String username = userpwd.split("_")[0];
        String password = userpwd.split("_")[1];

        List<CharityCompany> charities = userCharityRepository.findByEmail(username);
        System.out.println(charities);
        boolean exists = false;
        CharityCompany current_user = null;

        for (CharityCompany ch : charities) {
            if(ch.getEmail().equals(username)){
                exists = true;
                current_user = ch;
                if(!ch.getPassword().equals(password))
                    exists = false;
                break;
            }
        }

        if(exists) {
            System.out.println("ci sono utenti");
            return current_user;
        }else {
            System.out.println("NON CI sono utenti");
            return null;
        }
    }

    @PostMapping(value="/home/charity/create")
    public CharityCompany addUserCharity(@RequestBody CharityCompany user){ //requestBody mi "rigonfia" il file JSON
        CharityCompany newUser = userCharityRepository.save(new CharityCompany(user.getId(), user.getName(), user.getNameCEO(), user.getEmail(), user.getAddress(), user.getPassword(), user.getPhone()));
        System.out.println("New User create!");
        return newUser;
    }


    @GetMapping(value = "users/charity/name/{name}")
    public List<CharityCompany> findByName(@PathVariable String name) {
        List<CharityCompany> userSpecific = userCharityRepository.findByName(name);

        return userSpecific;

    }

    @GetMapping(value = "users/charity/email/{email}")
    public List<CharityCompany> findByEmail(@PathVariable String email) {
        List<CharityCompany> userSpecific = userCharityRepository.findByEmail(email);
        return userSpecific;
    }

    @GetMapping("/users/charities")
    public List<CharityCompany> allUser(){
        System.out.println("Get all Users...");
        List<CharityCompany> users = new ArrayList<>();
        userCharityRepository.findAll().forEach(users::add);
        if(users.isEmpty()){
            System.out.println("La lista è vuota");
            users = null;
        }
        System.out.println("Ci sono utentiii");
        return users;
    }


}
