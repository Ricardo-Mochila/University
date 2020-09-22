package com.example.servingwebcontent.StoreBackend;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class StoreController {

    public static StoreRepository repository = null;

    StoreController(StoreRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Stores")
    List<Store> all() {

        return repository.selectItems();
    }

    @PostMapping("/Stores")
    public RedirectView newStore(@RequestParam (name = "StoreName") String storeName,
                   @RequestParam (name = "latitude") String latitude,
                   @RequestParam (name = "longitude") String longitude,
                   @RequestParam (name = "ocupationLevel") String ocupationLevel, Principal principal) {

        System.out.println(ocupationLevel);
        int value = -1;

        if (ocupationLevel.equals("empty")){
            value = 0;

        }
        else if (ocupationLevel.equals("enough_space")){
            value = 1;
        }
        else if (ocupationLevel.equals("full_space")){
            value = 2;

        }
        else if (ocupationLevel.equals("queue")){
            value = 3;

        }


        repository.save(new Store(storeName, Float.parseFloat(latitude), Float.parseFloat(longitude), value, principal.getName()));
        return new RedirectView("/home");
    }

    // Single item

    @GetMapping("/Stores/{id}")
    Store one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    @PutMapping("/Stores/{id}")
    Store replaceStore(@RequestBody Store newStore, @PathVariable Long id) {

        return repository.findById(id)
                .map(store -> {
                    store.setStoreName(newStore.getStoreName());
                    store.setId(newStore.getId());
                    store.setLatitude(newStore.getLongitude());
                    store.setLongitude(newStore.getLongitude());
                    return repository.save(store);
                })
                .orElseGet(() -> {
                    newStore.setId(id);
                    return repository.save(newStore);
                });
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteStore(@PathVariable Long id) {

        repository.deleteById(id);

        return new RedirectView("/home");
    }

}