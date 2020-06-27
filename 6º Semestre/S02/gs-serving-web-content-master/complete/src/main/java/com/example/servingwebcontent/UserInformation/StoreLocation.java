package com.example.servingwebcontent.UserInformation;

import com.example.servingwebcontent.StoreBackend.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class StoreLocation {

    private final StoreRepository repository;

    StoreLocation(StoreRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/StoreByLocation")
    public String userNameSearch(@RequestParam float latitude, @RequestParam float longitude, Model model) {

        model.addAttribute("StoreByLocation",  repository.findByLocation(latitude, longitude));
        return "StoreByLocation";
    }

}
