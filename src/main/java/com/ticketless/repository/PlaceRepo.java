package com.ticketless.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketless.model.Place;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long> {

    @Query(value = "select p from Place as p where p.city LIKE %:cityName% and p.state LIKE %:stateName% and p.placeName LIKE %:placeName%",countQuery = "select count(p) from Place as p where p.city=?1 and p.state=?2 and p.placeName=?3")
    Page<Place> getPlaceCityState(@Param("cityName") String cityName,@Param("stateName") String stateName,@Param("placeName") String placeName, Pageable pageable);

    @Query(value = "select p from Place as p",countQuery = "select count(p) from Place as p")
    Page<Place> getPlaceCityStateTopTen(Pageable pageable);

}
//select p from Place as p where p.city LIKE %:cityName% or p.state LIKE %:stateName% or p.placeName LIKE %:placeName%