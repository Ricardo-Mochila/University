package com.example.servingwebcontent;

public class StoreNotFoundException extends RuntimeException  {
    public StoreNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
