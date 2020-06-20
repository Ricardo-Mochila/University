package com.example.servingwebcontent.UserInformation;

import com.example.servingwebcontent.StoreBackend.Store;
import com.example.servingwebcontent.StoreBackend.StoreRepository;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
public class UserInformation {

    private final StoreRepository repository;

    UserInformation(StoreRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/userInformation")
    public List<Store> userNameSearch(Principal principal) {

        return repository.findByUsername(principal.getName());
    }

}
