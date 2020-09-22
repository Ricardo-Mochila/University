package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.servingwebcontent.StoreBackend.StoreController.repository;

@SpringBootApplication
public class SecuringWebApplication {

    public static void main(String[] args) throws Throwable {

        SpringApplication.run(SecuringWebApplication.class, args);


       Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    repository.clearOldPlaces();
                }catch (Exception e){
                    System.out.println("There was nothing to remove");
                };
            }
        }, 0, 600000);

    }




}