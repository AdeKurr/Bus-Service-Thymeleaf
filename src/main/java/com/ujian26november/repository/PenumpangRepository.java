package com.ujian26november.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ujian26november.model.TerminalAndTanggal;
import com.ujian26november.model.Jurusan;
import com.ujian26november.model.Keberangkatan;
import com.ujian26november.model.Penumpang;

public interface PenumpangRepository extends JpaRepository<Penumpang, String> {

	List<Penumpang> findByNik(String string);

	@Query(value = "SELECT keberangkatan.id, bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga, keberangkatan.tanggal as waktu, jurusan.deskripsi FROM keberangkatan INNER JOIN jurusan on keberangkatan.id_jurusan = jurusan.id INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi WHERE jurusan.terminal_awal = ?1 AND keberangkatan.tanggal LIKE ?2% AND bus.kapasitas > (SELECT COUNT(id_keberangkatan) FROM booking WHERE booking.id_keberangkatan = keberangkatan.id)", nativeQuery = true)
	List<TerminalAndTanggal> findByTerminalAndTanggal(Keberangkatan tanggal, Jurusan terminalAwal);

}
