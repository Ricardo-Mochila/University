package com.example.servingwebcontent;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
                   @RequestParam (name = "longitude") String longitude) {

        return repository.save(new Store(storeName, Float.parseFloat(latitude), Float.parseFloat(longitude) ));
    }

    // Single item

    @GetMapping("/Stores/{id}")
    Store one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    @PutMapping("/Stores/{id}")
    Store replaceStore(@RequestBody Store newStore, @PathVariable Long id,  @PathVariable float Longitude, @PathVariable float Latitude) {

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