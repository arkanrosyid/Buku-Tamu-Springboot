package com.bukutamu.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bukutamu.models.Agenda;
import com.bukutamu.repositories.AgendaRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })

public class AgendaController {
    @Autowired
    private AgendaRepository agendaRepo;

    // show page
    @GetMapping("/agenda")
    ModelAndView agenda(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            ModelAndView mav = new ModelAndView("redirect:/login");
            return mav;
        }
        ModelAndView mav = new ModelAndView("admin/agenda");
        mav.addObject("adminName", session.getAttribute("adminName"));
        return mav;
    }

    @GetMapping("/getAllAgenda")
    public @ResponseBody String getAll() {
        List<Agenda> agenda = agendaRepo.findAll();

        String response = new Gson().toJson(agenda);

        return response;
    }

    @RequestMapping(value = "/agenda/tambah", method = RequestMethod.POST)
    public @ResponseBody String addAgenda(@RequestBody Map json) {

        Agenda agenda = new Agenda();

        String name = toString(json.get("name"));
        String date = toString(json.get("date"));

        agenda.setName(name);
        agenda.setDate(date);
        agendaRepo.save(agenda);

        String response = new Gson().toJson("success");
        return response;
    }

    public String toString(Object json) {
        StringBuilder mapAsString = new StringBuilder();
        mapAsString.append(json);
        return mapAsString.toString();
    }

    @GetMapping("agenda/play/{id}")
    ModelAndView showBukuTamu(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            ModelAndView mav2 = new ModelAndView("redirect:/login");

            return mav2;
        }

        ModelAndView mav = new ModelAndView("admin/bukuTamu");
        Optional<Agenda> data = agendaRepo.findById(id);
        Agenda agenda = data.get();

        mav.addObject("agenda", agenda);
        return mav;
    }

    @GetMapping("agenda/info/{id}")
    ModelAndView showSingleAgenda(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            ModelAndView mav = new ModelAndView("redirect:/login");

            return mav;
        }

        ModelAndView mav = new ModelAndView("admin/agendaS");

        Agenda data = agendaRepo.findByID(id);

        mav.addObject("agenda", data);
        mav.addObject("adminName", session.getAttribute("adminName"));
        return mav;
    }

    @GetMapping("/agenda/delete/{id}")
    public @ResponseBody String deleteTamu(@PathVariable Integer id) {
        // Tamu tamu = tamuRepo.findByID(id);
        agendaRepo.deleteById(id);
        String response = new Gson().toJson("success");
        // String response = new Gson().toJson("cconnect");

        return response;
    }
}
