/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lukaszk.pai.configuration;

/**
 *
 * @author Lukasz
 */


import com.lukaszk.pai.dao.userDao;
import com.lukaszk.pai.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserAuthenticationDetails implements UserDetailsService {
 @Autowired
 private userDao dao;
 @Override
 public UserDetails loadUserByUsername(String login)
throws UsernameNotFoundException {
 User user = dao.findByLogin(login);
 if (user != null) {
 List <GrantedAuthority> grupa = new ArrayList<>();
 if(user.getLogin()=="admin"){ grupa.add(new SimpleGrantedAuthority("adminUser"));}
 else{
 grupa.add(new SimpleGrantedAuthority("normalUser"));
 }
 return new
org.springframework.security.core.userdetails.User(user.getLogin(),
user.getPassword(), true,
 true, true, true, grupa);
 } else {
 throw new UsernameNotFoundException("Zły login lub hasło...");
 }
 }
 
 
 
 
 
}