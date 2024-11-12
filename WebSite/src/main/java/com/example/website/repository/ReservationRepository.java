package com.example.website.repository;

import com.example.website.model.ReservationModel;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationModel, Long> {
}
