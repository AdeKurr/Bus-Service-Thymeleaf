package com.ujian26november.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ujian26november.model.Booking;
import com.ujian26november.model.Jurusan;
import com.ujian26november.model.Keberangkatan;
import com.ujian26november.model.Penumpang;
import com.ujian26november.repository.PenumpangRepository;
import com.ujian26november.repository.BookingRepository;

@Controller
public class BusController {
	@Autowired
	PenumpangRepository penumpangRepo;

	@Autowired
	BookingRepository bookingRepo;

//	@GetMapping("/detailpenumpang")
//	public String getListPeserta(Model model) {
//		List<Penumpang> anu = penumpangRepo.findByNik(null);
//		model.addAttribute("formData", anu);
//		return "detailpenumpang";
//	}

	// Nomor 8
	@GetMapping("/loginpenumpang")
	public String getFormPenumpang(Model model) {
		model.addAttribute("formData", new Penumpang());
		return "formlogin";
	}

	// Bingung bagaimana cara memanggil nama dan nomor telepon dan memasukkannya ke formData
	@GetMapping("/findnik")
	public String findByNik(@ModelAttribute("formData") Penumpang nik) {
		List<Penumpang> formData = penumpangRepo.findByNik(nik.getNik());
		if (formData.size() == 0) {
			return "kenihilan";
		} else {
			return "detailpenumpang";
		}
	}

	// Nomor 9
	@GetMapping("/daftar")
	public String daftarPenumpang(Model model) {
		model.addAttribute("formData", new Penumpang());
		return "formpenumpangbaru";
	}

	@PostMapping("/createpenumpang")
	public String createPenumpang(@ModelAttribute("formData") Penumpang formData) {
		penumpangRepo.save(formData);
		return "detailpenumpang";
	}

	// Nomor 10
	// tanggal adanya di Keberangkatan, tapi terminal adanya di jurusan. Nah new nya
	// itu ke mana?
	@GetMapping("/carikeberangkatan")
	public String cariKeberangkatan(Model model) {
		model.addAttribute("formData", new  Keberangkatan());
//		model.addAttribute("formData", new Jurusan());
		return "formcarikeberangkatan";
	}

	@GetMapping("/findKeberangkatan")
	public String findKeberangkatan(@ModelAttribute("formData") Keberangkatan tanggal, Jurusan terminalAwal) {
		penumpangRepo.findByTerminalAndTanggal(tanggal, terminalAwal);
		return "listdetailkeberangkatan";
	}

	// Nomor 11
	// Kenapa setId_keberangkatan nggak ada setternya di lombok, padahal di project pak Paulus, setIdjk tuh bisa.
	@GetMapping("/booking")
	public String booking(Model model) {
		model.addAttribute("formData", new Booking());
		return "formbooking";
	}

	@PostMapping("/createbooking")
	public String createBooking(@ModelAttribute("formData") Booking formData) {
//		long idKeberangkatanBeneran = formData.getId_keberangkatan().getId();
//		formData.setId_keberangkatan(idKeberangkatanBeneran);
		bookingRepo.save(formData);
		return "bookingdetail";
	}

	// Nomor 12
	// Entah kenapa Whitelabel error nya ; No class com.ujian26november.model.Booking entity with id 1 exists! padahal ada id 1 di databasenya.
	@GetMapping("/cancel")
	public String cancelBooking(Model model) {
		model.addAttribute("formData", new Booking());
		return "formcancel";
	}

	@PostMapping("/cancelbooking")
	public String cancel(@ModelAttribute("formData") Booking formData) {
		bookingRepo.deleteById(formData.getId());
		return "cancelmessage";
	}
}
