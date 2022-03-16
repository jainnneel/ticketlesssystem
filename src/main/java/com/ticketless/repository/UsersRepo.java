package com.ticketless.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticketless.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

    @Query(value = "select us from Users as us where mobileNo=?1")
    Users findUserByMobile(String mobile);

}
