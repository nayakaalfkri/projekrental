package com.camp.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.camp.rental.model.Alat;
import com.camp.rental.repository.AlatRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired private AlatRepository alatRepository;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("daftarAlat", alatRepository.findAll()); return "admin/dashboard";
    }

    @GetMapping("/tambah")
    public String formTambah(Model model) {
        model.addAttribute("alat", new Alat()); return "admin/form-alat";
    }

    @GetMapping("/edit/{id}")
    public String formEdit(@PathVariable Long id, Model model) {
        model.addAttribute("alat", alatRepository.findById(id).orElseThrow()); return "admin/form-alat";
    }

    @PostMapping("/simpan")
    public String simpanAlat(@ModelAttribute Alat alat) {
        alatRepository.save(alat); return "redirect:/admin";
    }

    @GetMapping("/hapus/{id}")
    public String hapusAlat(@PathVariable Long id) {
        alatRepository.deleteById(id); return "redirect:/admin";
    }
}
