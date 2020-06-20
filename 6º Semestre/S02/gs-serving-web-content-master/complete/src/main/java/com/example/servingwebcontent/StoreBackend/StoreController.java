package com.example.servingwebcontent.StoreBackend;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController {

    private final StoreRepository repository;

    StoreController(StoreRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Stores")
    List<Store> all() {
        return repository.findAll();
    }

    @PostMapping("/Stores")
    Store newStore(@RequestParam (name = "StoreName") String storeName,
                   @RequestParam (name = "latitude") String latitude,
                   @RequestParam (name = "longitude") String longitude,
                   @RequestParam (name = "ocupationLevel") String ocupationLevel, Principal principal) {

        System.out.println(ocupationLevel);
        int value = 0;

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

        return repository.save(new Store(storeName, Float.parseFloat(latitude), Float.parseFloat(longitude), value, principal.getName()));
    }

    // Single item

    @GetMapping("/Stores/{id}")
    Store one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    @PutMapping("/Stores/{id}")
    Store replaceStore(@RequestBody Store newStore, @PathVariable Long id,  @PathVariable float longitude, @PathVariable float latitude) {

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

    @DeleteMapping("/Stores/{id}")
    void deleteStore(@PathVariable Long id) {
        repository.deleteById(id);
    }
}