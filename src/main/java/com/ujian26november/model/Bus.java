package com.ujian26november.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Bus")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Bus {
	@Id
	String no_polisi;
	int kapasitas;
	String nama_supir;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nama_perusahaan", referencedColumnName = "nama")
	Perusahaan nama_perusahaan;

}
