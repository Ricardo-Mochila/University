package com.example.servingwebcontent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
public class Store {

    private @Id @GeneratedValue Long id;
    private String storeName;
    private float latitude;
    private float longitude;

    public Store(){ }

    public Store(String storeName, float latitude, float longitude) {
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Store))
            return false;
        Store employee = (Store) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.storeName, employee.storeName)
                && Objects.equals(this.longitude, employee.longitude) && Objects.equals(this.latitude, employee.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.storeName, this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.storeName + '\'' + ", latitude='" + this.latitude + '\'' + ", longitude='" + this.longitude + '\'' + '}';
    }
}