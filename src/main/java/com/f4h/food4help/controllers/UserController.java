package com.f4h.food4help.controllers;

import com.f4h.food4help.models.User;
import com.f4h.food4help.repositories.UserRepository;
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
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> allUser(){
        System.out.println("Get all Users...");
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @PostMapping(value="/home/register")
    public User addUser(@RequestBody User user){ //requestBody mi "rigonfia" il file JSON
        User newUser = userRepository.save(new User(user.getName(), user.getNameCEO(), user.getEmail(), user.getAddress(), user.getPassword(), user.getPhone()));
        System.out.println("New User create!");
        return newUser;
    }

    @GetMapping(value = "/home/login/{userpwd}")
    public User loginCharity(@PathVariable String userpwd) {

        if(!userpwd.contains("_")) {
            return null;
        }

        String username = userpwd.split("_")[0];
        String password = userpwd.split("_")[1];

        List<User> charities = userRepository.findByEmail(username);
        System.out.println(charities);
        boolean exists = false;
        User current_user = null;

        for (User ch : charities) {
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

    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All User...");

        userRepository.deleteAll();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }

    @GetMapping(value = "users/name/{name}")
    public List<User> findByName(@PathVariable String name) {
        List<User> userSpecific = userRepository.findByName(name);

            return userSpecific;

    }

    @GetMapping(value = "users/email/{email}")
    public List<User> findByEmail(@PathVariable String email) {
        List<User> userSpecific = userRepository.findByEmail(email);
        return userSpecific;
    }



}
