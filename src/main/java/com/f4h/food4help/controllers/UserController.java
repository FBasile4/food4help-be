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

    @PostMapping(value="/users/add")
    public User addUser(@RequestBody User user){ //requestBody mi "rigonfia" il file JSON
        User newUser = userRepository.save(new User(user.getName(), user.getNameCEO(), user.getEmail(), user.getAddress(), user.getPassword(), user.getPhone()));
        return newUser;
    }

    @PostMapping(value = "/home/login")
    public ResponseEntity<String> loginUser(@RequestBody User userIn){
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        String check_email = userIn.getEmail();
        String check_password = userIn.getPassword();
        String not_auth = "Utente non registrato";
        for(int i=0; i<list.size(); i++){
          User userTmp = list.get(i);
          String emailTmp = userTmp.getEmail();
          String passwordTmp = userTmp.getPassword();
          if(emailTmp.equals(check_email)){
                if(passwordTmp.equals(check_password) && !check_password.equals("")){
                    String toRet = userTmp.getId()+"";
                    System.out.println(toRet);
                    not_auth = toRet;
                }else{
                    not_auth = "registrato ma password sbagliata";
                }
          }
          System.out.println("Utente loggato" + not_auth);
        }
        return new ResponseEntity<>("Utente " + not_auth + " loggato!", HttpStatus.OK );
    }

    /*@DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        System.out.println("Delete User with ID = " + id + "...");

        userRepository.deleteById(id);

        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All User...");

        userRepository.deleteAll();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }*/

    @GetMapping(value = "users/name/{name}")
    public List<User> findByName(@PathVariable String name) {

        List<User> userSpecific = userRepository.findByName(name);
        return userSpecific;
    }

}
