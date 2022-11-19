package com.bukutamu.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bukutamu.models.Admin;
import com.bukutamu.repositories.AdminRepository;
// import com.google.gson.Gson;

@Controller
@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })

public class AdminController {

    @Autowired
    private AdminRepository repo;

    // show login
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            model.addAttribute("admin", new Admin());

            return "admin/login";
        } else {
            return "redirect:/home";
        }

    }

    // login
    @RequestMapping(value = "/login_up", method = RequestMethod.POST)
    public String prosesLogin(Admin admin, HttpServletRequest request) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        String hash = "";
        Admin verif = new Admin();
        //
        if (email != null) {

            try {
                verif = repo.findByEmail(email);
                return "redirect:/login";
            } catch (Exception e) {
            } finally {

                try {
                    hash = verif.getPassword();
                    return "redirect:/login";
                } catch (Exception e) {

                } finally {

                    Boolean verified = cekPassword(password, hash);

                    if (verified == true) {
                        request.getSession().setAttribute("admin", email);
                        request.getSession().setAttribute("adminName", verif.getName());
                        return "redirect:/home";

                    } else {
                        return "redirect:/login";
                    }
                }

            }

        } else {
            return "redirect:/login";
        }

    }

    // cek Pass
    private boolean cekPassword(String pass, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {

        } else {
            password_verified = BCrypt.checkpw(pass, stored_hash);
        }

        return (password_verified);

    }

    // Logout
    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {

        request.getSession().invalidate();
        return "redirect:/login";
    }

    // home
    @GetMapping("/home")
    ModelAndView home(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            ModelAndView mav2 = new ModelAndView("redirect:/login");

            return mav2;
        }
        ModelAndView mav = new ModelAndView("admin/home");
        mav.addObject("adminName", session.getAttribute("adminName"));

        return mav;
    }
}
