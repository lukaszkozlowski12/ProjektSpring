package com.lukaszk.pai;


import com.lukaszk.pai.dao.ZadanieDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lukaszk.pai.dao.userDao;
import com.lukaszk.pai.entity.User;
import com.lukaszk.pai.entity.Zadania;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class PaiApplication {
    
 @Autowired
 private userDao dao;
 @Autowired
private ZadanieDao zdDao;
 @Autowired
 private PasswordEncoder passwordEncoder;
  
 public static void main(String[] args) {
 SpringApplication.run(PaiApplication.class, args);
 
 
 

 }
 
 

 
 
 @PostConstruct
 public void init() {
 dao.save(new User("Jan", "Kowalski","admin", 
passwordEncoder.encode("admin")));
 dao.save(new User("Ania", "Annowska","ania",
passwordEncoder.encode("ania")));
  dao.save(new User("Lukasz", "Kozlowski","login",
passwordEncoder.encode("login")));
  
 zdDao.save(new Zadania("login","NazwaZd1","Sprzątanie","01:01:2020 15:03",200.0));
 
  zdDao.save(new Zadania("test","Nazwa zadania2 ","Programowanie","01:01:2020 12:53",1200.0));
    zdDao.save(new Zadania("login","nazwa_zadania3 ","opis_zd3","11:01:2022 18:53",500.0));
 }}