package com.f4h.food4help.controllers;

import com.f4h.food4help.models.WorkingActivity;
import com.f4h.food4help.repositories.WorkingActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")//tutti i metodi che verranno dopo devono partire da questa baseline
public class WorkingActivityController {

    @Autowired //riferimento al mio repository automatico svolto da spring core
    WorkingActivityRepository userWorkingRepository;

    @GetMapping(value = "/home/workingActivity/login/{userpwd}")
    public WorkingActivity loginWorkingActivity(@PathVariable String userpwd) {

        if(!userpwd.contains("_"))
            return null;

        String username = userpwd.split("_")[0];
        String password = userpwd.split("_")[1];

        List<WorkingActivity> wactivities = userWorkingRepository.findByEmail(username);

        boolean exists = false;
        WorkingActivity current_user = null;


        for (WorkingActivity wa : wactivities) {
            if(wa.getEmail().equals(username)){
                exists = true;
                current_user = wa;
                if(!wa.getPassword().equals(password))
                    exists = false;
                break;
            }
        }

        if(exists)
            return current_user;
        else
            return null;
    }

    @PostMapping(value="/home/workingActivity/create")
    public WorkingActivity addUserWorkingActivity(@RequestBody WorkingActivity user){ //requestBody mi "rigonfia" il file JSON
        WorkingActivity newUser = userWorkingRepository.save(new WorkingActivity(user.getId(), user.getName(), user.getNameCEO(), user.getEmail(), user.getAddress(), user.getPassword(), user.getPhone(), user.getClientId()));
        System.out.println("New User create!");
        return newUser;
    }


    @GetMapping(value = "users/workingActivity/name/{name}")
    public List<WorkingActivity> findByName(@PathVariable String name) {
        List<WorkingActivity> userSpecific = userWorkingRepository.findByName(name);

        return userSpecific;

    }

    @GetMapping(value = "users/workingActivity/email/{email}")
    public List<WorkingActivity> findByEmail(@PathVariable String email) {
        List<WorkingActivity> userSpecific = userWorkingRepository.findByEmail(email);
        return userSpecific;
    }

    @GetMapping("/users/workingActivities")
    public List<WorkingActivity> allUser(){
        System.out.println("Get all Users...");
        List<WorkingActivity> users = new ArrayList<>();
        userWorkingRepository.findAll().forEach(users::add);
        if(users.isEmpty()){
            System.out.println("La lista è vuota");
            users = null;
        }
        System.out.println("Ci sono utentiii");
        return users;
    }

    @GetMapping(value = "/workingactivity/login/google/{clientid}")
    public WorkingActivity loginGoogle(@PathVariable String clientid) {

        List<WorkingActivity> wactivities = new ArrayList<>();
        userWorkingRepository.findAll().forEach(wactivities::add);

        boolean exists = false;
        WorkingActivity current_user = null;


        for (WorkingActivity wa : wactivities) {
            if(wa.getClientId().equals(clientid)){
                exists = true;
                current_user = wa;
                break;
            }
        }

        if(exists)
            return current_user;
        else
            return null;
    }


}
