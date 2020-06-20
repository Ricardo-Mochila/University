package com.example.servingwebcontent.StoreBackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Store {

    private @Id @GeneratedValue Long id;
    private String storeName;
    private float latitude;
    private float longitude;
    private int empty;
    private int enough_space;
    private int full_space;
    private int queue;
    private String username;
    private Timestamp date;

    public Store(){ }

    public Store(String storeName, float latitude, float longitude, int value, String user) {
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.empty = 0;
        this.enough_space = 0;
        this.full_space = 0;
        this.queue = 0;
        this.username = user;
        java.util.Date utilDate = new java.util.Date();

        this.date = new Timestamp(utilDate.getTime());

        switch (value){
            case 0:
                this.empty = 1;
            case 1:
                this.enough_space = 1;
            case 2:
                this.full_space = 1;
            case 3:
                this.queue = 1;
        }

    }

    public String getStoreName() {
        return storeName;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getEnough_space() {
        return enough_space;
    }

    public void setEnough_space(int enough_space) {
        this.enough_space = enough_space;
    }

    public int getFull_space() {
        return full_space;
    }

    public void setFull_space(int full_space) {
        this.full_space = full_space;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}