package com.f4h.food4help.repositories;

import com.f4h.food4help.models.CharityCompany;
import com.f4h.food4help.models.WorkingActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkingActivityRepository extends CrudRepository<WorkingActivity, Long> {

    List<WorkingActivity> findByName(String name);
    List<WorkingActivity> findByEmail(String email);

}
