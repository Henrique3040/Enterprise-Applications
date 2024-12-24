package com.example.website.service;

import com.example.website.model.ReservationModel;
import com.example.website.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<ReservationModel> getReservation(Long userId) {
        return reservationRepository.findByUser_Id(userId);
    }

}
