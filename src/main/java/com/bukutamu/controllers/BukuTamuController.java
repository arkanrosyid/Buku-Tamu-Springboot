package com.bukutamu.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bukutamu.models.Tamu;
import com.bukutamu.repositories.TamuRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })

public class BukuTamuController {

    @Autowired
    private TamuRepository tamuRepo;

    @RequestMapping(value = "/tamu/tambah", method = RequestMethod.POST)
    public @ResponseBody String addTamu(@RequestBody Map json) {

        Tamu tamu = new Tamu();

        String name = toString(json.get("name"));
        String ins = toString(json.get("instansi"));
        String email = toString(json.get("email"));
        String desc = toString(json.get("desc"));
        String date = toString(json.get("date"));
        Integer idAgenda = toInt(json.get("idAgenda"));

        tamu.setName(name);
        tamu.setAgency(ins);
        tamu.setEmail(email);
        tamu.setDesctription(desc);
        tamu.setIdAgenda(idAgenda);
        tamu.setDate(date);
        if (tamu.getName() == "" || tamu.getEmail() == "" || tamu.getAgency() == "") {
            String response = new Gson().toJson("failure");
            return response;
        } else {
            tamuRepo.save(tamu);
            String response = new Gson().toJson("success");
            return response;
        }

    }

    public String toString(Object json) {
        StringBuilder mapAsString = new StringBuilder();
        mapAsString.append(json);
        return mapAsString.toString();
    }

    public Integer toInt(Object json) {

        int value = Integer.parseInt(json.toString());
        return value;
    }

    @GetMapping("/getAllTamu/{id}")
    public @ResponseBody String getAll(@PathVariable Integer id) {

        List<Tamu> tamu = tamuRepo.findAllByIdAgenda(id);

        String response = new Gson().toJson(tamu);

        return response;
    }

    @GetMapping("/tamu/delete/{id}")
    public @ResponseBody String deleteTamu(@PathVariable Integer id) {
        // Tamu tamu = tamuRepo.findByID(id);
        tamuRepo.deleteById(id);
        String response = new Gson().toJson("success");
        // String response = new Gson().toJson("cconnect");

        return response;
    }

    @GetMapping("tamu/info/{id}")
    ModelAndView showSingleAgenda(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            ModelAndView mav = new ModelAndView("redirect:/login");

            return mav;
        }

        ModelAndView mav = new ModelAndView("admin/tamuS");

        Tamu data = tamuRepo.findByID(id);

        mav.addObject("tamu", data);
        mav.addObject("adminName", session.getAttribute("adminName"));
        return mav;
    }

}
