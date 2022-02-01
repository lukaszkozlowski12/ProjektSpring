/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lukaszk.pai.controllers;
import com.lukaszk.pai.dao.ZadanieDao;
import com.lukaszk.pai.dao.userDao;
import com.lukaszk.pai.entity.User;

import com.lukaszk.pai.entity.Zadania;
import static java.lang.System.console;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ZadaniaController {
    
  @Autowired
 private ZadanieDao dao;   
   private User daoUser;   
   @GetMapping("/addZadanie")
 public String AddPage(Model m, Principal principal) {
 //dodanie do modelu nowego zd
 Zadania zadania = new Zadania();
 zadania.setLogin(principal.getName());
 //zadania.setLogin(principal.getName());
 
 m.addAttribute("zadania", zadania);
 
 //zwrócenie nazwy widoku rejestracji - register.html
 return "dodajZadanie";
 
 
 }
    
    
    @PostMapping("/addZadanieView")
 public String addZadanieview(@Valid @ModelAttribute Zadania zd,  BindingResult binding, Principal p, Model m) {
 if (binding.hasErrors()) {
     
 //zadania.setLogin(principal.getName());

return "dodajZadanie"; // powrót do formularza
}
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
         String date = formatter.format(new Date());
         zd.setLogin(p.getName());
  zd.setData_dodania(date.toString());
 dao.save(zd);
 //przekierowanie do adresu url: /login
        System.out.println("Login principal="+zd.getZadanieId());
        System.out.println("Login"+zd.getLogin());
 return "redirect:profile";
 } 
    
    
 
 @PostMapping("/addZadanieFromAdmin")
 public String addZadanieFromAdmin(@Valid @ModelAttribute Zadania zd, BindingResult binding, Principal p, Model m){
  if (binding.hasErrors()) {
     

return "error"; // powrót do formularza
  }
 
 
  System.out.println("Login ="+zd.getNazwa_zadania());
 System.out.println("Login"+zd.getLogin());
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
         String date = formatter.format(new Date());
         zd.setLogin(zd.getLogin());
  zd.setData_dodania(date.toString());
 dao.save(zd);
 
 return "redirect:profile";}
 
 
 
 
 
 
 
 
 
    @GetMapping("/zadaniaAll")
    public String allZadania(Model m){
     
           System.out.println("all zadania");
        m.addAttribute("zadania", dao.findAll());
        
           System.out.println("all zadania info"+m.toString());
          
             m.addAttribute("stawkaall",dao.obliczStawkeAll());
        return "zadania";
    }
    
    
        @GetMapping("/zadaniaByLogin")
    public String allZadaniaByLogin(Model m, Principal p){
        m.addAttribute("zadania", dao.findAllByLogin(p.getName()));
        
        
       System.out.println("Suma stawek="+dao.obliczStawke(p.getName()));
       Double st=dao.obliczStawke(p.getName());
       if(st!= null){} else st=0.0;
           m.addAttribute("stawka",st);
           m.addAttribute("stawkaall",dao.obliczStawkeAll());
        return "zadania";

    }
    
    
  
    
    
    
 //@GetMapping("/zadania")
//definicja metody, która zwróci do widoku zadania.html listę użytkowników z bd
    
    
    
        @GetMapping("/changeZd")
    public String changeZd(@RequestParam("id") int id, Model model) {
       
   
        System.out.println("sdadsadasdad");
        // get the employee from the service
	//	Employee employee = employeeService.findById(id);
                Zadania editZd= dao.findByzadanieId(id);
		       
	
                 model.addAttribute("zadania", editZd);
		
		// send over to our form
		  return "editZadanie";
    }
    
    
    
    
     
    
    
    
    
    
       @PostMapping("/save2")
    public String save2 (@Valid Zadania zd, BindingResult binding,Principal principal){

        if (binding.hasErrors()) {
         
            return "changeZd"; // powrót do formularza
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
         Date date = new Date();  
        Zadania zdEdit = dao.findByLogin(principal.getName());
        zdEdit.setLogin(principal.getName());
        zdEdit.setNazwa_zadania(zd.getNazwa_zadania());
        zdEdit.setOpis_zadania(zd.getOpis_zadania());
        zdEdit.setStawka(zd.getStawka());
        zdEdit.setData_dodania(date.toString());
     
        
       
        dao.save(zdEdit);
        return "redirect:logout";
    }
    
    
    
        @PostMapping("/save3")
    public String save3 (@RequestParam("id") int id, @Valid @ModelAttribute("zadania") Zadania zd, BindingResult binding,Principal principal){

        if (binding.hasErrors()) {
         
            System.out.println("Save3="+zd.getLogin()+" id="+id);
            return "editZadanie"; // powrót do formularza
        }
     System.out.println("id="+id);
     
      System.out.println("zd id=="+zd.getZadanieId());
          System.out.println("zdnazwa=="+zd.getNazwa_zadania());
         
           System.out.println("loginpr=="+principal.getName());
            System.out.println("zd opis=="+zd.getOpis_zadania());
             System.out.println("zd stawka=="+zd.getStawka());
           System.out.println("zd data=="+zd.getData_dodania());
           
           
           
        Zadania zdEdit = dao.findByzadanieId(id);
        zdEdit.setLogin(principal.getName());
        zdEdit.setNazwa_zadania(zd.getNazwa_zadania());
        zdEdit.setOpis_zadania(zd.getOpis_zadania());
        zdEdit.setStawka(zd.getStawka());
        zdEdit.setData_dodania(zd.getData_dodania().toString());
     
        
       
        dao.save(zdEdit);
        return "redirect:profile";
    }
  
      @PostMapping("/saveChangeZadanie")
       public String saveChangeZadanie(@Valid @ModelAttribute("zadania") Zadania zd, BindingResult binding){
    
           if (binding.hasErrors()) {
            return "editZadanie"; // powrót do formularza
        }
           
    
                    dao.save(zd);
		
		// use a redirect to prevent duplicated submissions
		return "redirect:/zadania";
    
    }
    
    
    
    

    @GetMapping("/deleteZadanie")
    public String deleteZadanie(@RequestParam("id") int id, Principal principal) {
        dao.delete(dao.findByzadanieId(id));
        return "redirect:/profile";
    }
    
} 
