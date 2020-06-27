package com.example.servingwebcontent.UserInformation;

import com.example.servingwebcontent.StoreBackend.Store;
import com.example.servingwebcontent.StoreBackend.StoreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;

@Controller
public class UserInformation {

    private final StoreRepository repository;

    UserInformation(StoreRepository repository) {
        this.repository = repository;
    }


    @RequestMapping("/userInformation")
    public String userNameSearch(Principal principal, Model model) {

        model.addAttribute("userInformation",  repository.findByUsername(principal.getName()));
        return "userInformation";
    }

}
