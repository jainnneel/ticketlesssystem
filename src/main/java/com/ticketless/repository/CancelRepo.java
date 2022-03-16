package com.ticketless.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketless.model.CancelOrder;
import com.ticketless.model.Users;

@Repository
public interface CancelRepo extends JpaRepository<CancelOrder, Long> {

    List<CancelOrder> findByusers(Users users);

}
