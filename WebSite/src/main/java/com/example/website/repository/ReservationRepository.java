package com.example.website.repository;

import com.example.website.model.ReservationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Long> {
    List<ReservationModel> findByUser_Id(Long userId);
}
