/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lukaszk.pai.controllers;

/**
 *
 * @author Lukasz
 */






import com.lukaszk.pai.dao.userDao;

import com.lukaszk.pai.entity.User;
import com.lukaszk.pai.entity.Zadania;

import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
 @Autowired
 private PasswordEncoder passwordEncoder;
 @Autowired
 private userDao dao;

     
 @GetMapping("/login")
 public String loginPage() {
 //zwrócenie nazwy widoku logowania - login.html
 return "login";
 }
 @GetMapping("/register")
 public String registerPage(Model m, User user) {
 //dodanie do modelu nowego użytkownika
 m.addAttribute("user", new User());
 //zwrócenie nazwy widoku rejestracji - register.html
 return "register";
 
 
 }
 @PostMapping("/register")
 public String registerPagePOST(@Valid @ModelAttribute User user,  BindingResult binding) {
 if (binding.hasErrors()) {
return "register"; // powrót do formularza
}
  System.out.println("Czy user istnieje ="+dao.sprawdzczyIstnieje(user.getLogin()));
 if(dao.sprawdzczyIstnieje(user.getLogin())>0){
 return "error";
 }
 
    
     user.setPassword(passwordEncoder.encode(user.getPassword()));
 dao.save(user);
 //przekierowanie do adresu url: /login
 return "redirect:/login";
 }
 
 
 
 @GetMapping("/profile")
 public String profilePage(Model m, Principal principal, Authentication a) {
 //dodanie do modelu obiektu user - aktualnie zalogowanego użytkownika:
 m.addAttribute("user", dao.findByLogin(principal.getName()));
 //zwrócenie nazwy widoku profilu użytkownika - profile.html
     System.out.println("Role="+a.getAuthorities());  
 return "profile";
 }
 
 
 @GetMapping("/users")
    public String allUsers(Model m){
        m.addAttribute("users", dao.findAll());
        return "users";
    }
 //@GetMapping("/users")
//definicja metody, która zwróci do widoku users.html listę użytkowników z bd

 @GetMapping("/delete")
    public String deleteUser(Principal principal) {
        dao.delete(dao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }
    
    
   @GetMapping("/deleteUser")
    public String deleteUserByid(@RequestParam("id") int id, Principal principal) {
        dao.delete(dao.findById(id));
        return "redirect:/profile";
    }
    
    @GetMapping("/edit")
    public String editUser(Principal principal, Model model) {
        User user = dao.findByLogin(principal.getName());
        model.addAttribute("user", user);
      
        return "edit";
    }
    
    
     @GetMapping("/wyslijZadanie")
    public String wyslijZadanko(Model m, Principal p){
     m.addAttribute("users", dao.findAll());
     m.addAttribute("zadania", new Zadania());
  
return "zadanieDlaUsera";
    } 
    
    
    
    @PostMapping("/save")
    public String save (@Valid User user, BindingResult binding,Principal principal){

        if (binding.hasErrors()) {
            return "edit"; // powrót do formularza
        }

        User userEdit = dao.findByLogin(principal.getName());
        userEdit.setName(user.getName());
        userEdit.setPassword(passwordEncoder.encode(user.getPassword()));
        userEdit.setLogin(user.getLogin());
        userEdit.setSurname(user.getSurname());
        dao.save(userEdit);
        return "redirect:logout";
    }

 
   
     
       
 
    
    
    
  
    
    
    
}
