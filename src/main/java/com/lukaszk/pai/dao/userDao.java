/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lukaszk.pai.dao;

/** interfejs dostepu do danych
 *
 * @author Lukasz
 */
import com.lukaszk.pai.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface userDao extends CrudRepository<User, Integer> {
  
    
public User findByLogin(String login);
public User findById(int id);
    
   @Query("select count(z.login) from User z where z.login=?1")
   public int sprawdzczyIstnieje(String login);

}