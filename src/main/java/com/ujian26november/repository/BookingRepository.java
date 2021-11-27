package com.ujian26november.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujian26november.model.Booking;

public interface BookingRepository extends JpaRepository <Booking, Long>{

	void deleteById(Booking formData);

}
