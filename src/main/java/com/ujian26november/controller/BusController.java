package com.ujian26november.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ujian26november.model.Booking;
import com.ujian26november.model.Keberangkatan;
import com.ujian26november.model.KeberangkatanDetail;
import com.ujian26november.model.Penumpang;
import com.ujian26november.repository.PenumpangRepository;
import com.ujian26november.repository.BookingRepository;
import com.ujian26november.repository.KeberangkatanRepository;

@Controller
public class BusController {
	@Autowired
	PenumpangRepository penumpangRepo;

	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	KeberangkatanRepository keberangkatanRepo;

	// Nomor 8
	@GetMapping("/loginpenumpang")
	public String getFormPenumpang(Model model) {
		model.addAttribute("formData", new Penumpang());
		return "formlogin";
	}

	@GetMapping("/findnik")
	public String findByNik(@ModelAttribute("formData") Penumpang nik, Model model2) {
		List<Penumpang> formData = penumpangRepo.findByNik(nik.getNik());
		if (formData.size() == 0) {
			return "kenihilan";
		} else {
			model2.addAttribute("formData", formData.get(0));
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
	@GetMapping("/carikeberangkatan")
	public String cariKeberangkatan(Model model) {
		model.addAttribute("formData", new Keberangkatan());
		return "formcarikeberangkatan";
	}

	@PostMapping("/carikeberangkatanresult")
	public String cariKeberangkatanResult(@ModelAttribute("data") Keberangkatan formData, Model model2) {
		String alamatHasil = "";
		List<KeberangkatanDetail> keberangkatanBeneran = keberangkatanRepo
				.getDetail(formData.getId_jurusan().getTerminal_awal(), formData.getTanggal());
		if (keberangkatanBeneran.size() == 0) {
			alamatHasil = "kenihilankeberangkatan";
		} else {
			model2.addAttribute("data", keberangkatanBeneran);
			alamatHasil = "listdetailkeberangkatan";
		}
		return alamatHasil;
	}

	@GetMapping("/booking")
	public String booking(Model model) {
		model.addAttribute("formData", new Booking());
		return "formbooking";
	}

	@PostMapping("createbooking")
	public String bookingResult(@ModelAttribute("formData") Booking formData, Model model) {
		String nik = formData.getNik().getNik();
		long idKeberangkatan = formData.getId_keberangkatan().getId();
		List<Penumpang> penumpangSementara = penumpangRepo.findByNik(nik);
		formData.setNik(penumpangSementara.get(0));
		Keberangkatan keberangkatanSementara = keberangkatanRepo.getById(idKeberangkatan);
		formData.setId_keberangkatan(keberangkatanSementara);
		bookingRepo.save(formData);
		List<Booking> hasilSimpan = bookingRepo.findByNik(formData.getNik());
		model.addAttribute("data", hasilSimpan.get(hasilSimpan.size() - 1));
		return "bookingdetail";
	}

	@GetMapping("/cancel")
	public String cancelBooking(Model model) {
		model.addAttribute("formData", new Booking());
		return "formcancel";
	}

	@PostMapping("/cancelbooking")
	public String cancelBooking(@ModelAttribute("formData") Booking dataBooking) {
		bookingRepo.deleteById(dataBooking.getId());
		return "cancelmessage";
	}
}
