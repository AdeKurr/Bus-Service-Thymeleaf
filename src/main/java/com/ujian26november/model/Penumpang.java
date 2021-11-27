package com.ujian26november.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name="Penumpang")
public class Penumpang {
	@Id
	String nik;
	String nama;
	String telepon;

}
