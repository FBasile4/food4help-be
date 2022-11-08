package com.f4h.food4help.repositories;


import com.f4h.food4help.models.User;
import org.springframework.data.jpa.repository.JpaRepository; //pacchetto di spring utile per la gestione dei dati
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
}
