/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lukaszk.pai.dao;

/**
 *
 * @author Lukasz
 */

import com.lukaszk.pai.entity.Zadania;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface ZadanieDao extends CrudRepository<Zadania, Integer> {
   
     public Zadania findByLogin(String login);
     public List<Zadania> findAllByLogin(String login);
     public Zadania findByzadanieId(int id);
     public void deleteByzadanieId(int theId);

     @Query("select sum(z.stawka) from Zadania z where z.login = ?1")
     public Double obliczStawke(String login);
     
     @Query("select sum(z.stawka) from Zadania z")
     public Double obliczStawkeAll();
}
