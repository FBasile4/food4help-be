package com.f4h.food4help.repositories;

import com.f4h.food4help.models.CharityCompany;
import com.f4h.food4help.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharityCompanyRepository extends CrudRepository<CharityCompany, Long> {

    List<CharityCompany> findByName(String name);
    List<CharityCompany> findByEmail(String email);

}
