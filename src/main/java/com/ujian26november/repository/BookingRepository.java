package com.ujian26november.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.ujian26november.model.Booking;
import com.ujian26november.model.Penumpang;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByNik(Penumpang nik);
}