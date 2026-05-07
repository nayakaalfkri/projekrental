package com.camp.rental.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.camp.rental.model.Alat;
import com.camp.rental.repository.AlatRepository;

@Controller
public class RentalController {
    @Autowired private AlatRepository alatRepository;

    @GetMapping("/")
    public String index(Model model) {
        setupModel(model); return "index";
    }

    @PostMapping("/hitung")
    public String hitungSewa(@RequestParam String alatNama, @RequestParam String tglAmbil,
                             @RequestParam int durasi, @RequestParam int jumlah,
                             @RequestParam String pengiriman, Model model) {
        
        Alat terpilih = alatRepository.findAll().stream()
                .filter(a -> a.getNama().equals(alatNama)).findFirst().orElse(null);

        if (terpilih != null) {
            double total = (terpilih.getHarga() * jumlah * durasi) + (pengiriman.equals("Kurir Lokal") ? 25000 : 0);
            model.addAttribute("terpilih", terpilih); model.addAttribute("tglAmbil", tglAmbil);
            model.addAttribute("durasi", durasi); model.addAttribute("jumlah", jumlah);
            model.addAttribute("pengiriman", pengiriman); model.addAttribute("total", total);
        }
        setupModel(model); return "index";
    }

    private void setupModel(Model model) {
        List<Alat> daftarAlat = alatRepository.findAll();
        model.addAttribute("daftarAlat", daftarAlat);
        model.addAttribute("kategoriList", daftarAlat.stream().map(Alat::getKategori).distinct().collect(Collectors.toList()));
    }
}
