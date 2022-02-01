/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lukaszk.pai.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 *
 * @author Lukasz
 */


@Entity
@Table(name = "Zadania")
public class Zadania {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int zadanieId;
 
 
 //private Integer id_user;
  @NotBlank(message = "Login nie może być pusty!")
 
  private String login;
  
 @NotNull(message = "Nazwa zadania nie może być pusta")
 @Size(min=2, max=239, message = "Rozmiar min 2 znaki dla nazwy!")
 private String nazwa_zadania;
@NotEmpty(message = "Opis nie może byc pusty")
@Size(min = 2, max = 232, message = "Opis musi mieć min 2 max 32 znaków") 
 private String opis_zadania;
//@NotEmpty(message = "Date may not be empty")
//@Size(min = 2, max = 222, message = "Data musi mieć min 2 max 40 znaków ") 
 private String data_dodania;
 
@Min(1)
@NotNull(message = "Wpisz stawke")
private Double stawka;

    public Zadania() {
    }


    
      public Zadania(String login, String nazwa_zadania, String opis_zadania, String data_dodania, Double stawka) {
        
        this.login = login;
        this.nazwa_zadania = nazwa_zadania;
        this.opis_zadania = opis_zadania;
        this.data_dodania = data_dodania;
        this.stawka = stawka;
    }

    public int getZadanieId() {
        return zadanieId;
    }

    public String getLogin() {
        return login;
    }

    public String getNazwa_zadania() {
        return nazwa_zadania;
    }

    public String getOpis_zadania() {
        return opis_zadania;
    }

    public String getData_dodania() {
        return data_dodania;
    }

    public Double getStawka() {
        return stawka;
    }

    public void setZadanieId(int zadanieId) {
        this.zadanieId = zadanieId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNazwa_zadania(String nazwa_zadania) {
        this.nazwa_zadania = nazwa_zadania;
    }

    public void setOpis_zadania(String opis_zadania) {
        this.opis_zadania = opis_zadania;
    }

    public void setData_dodania(String data_dodania) {
        this.data_dodania = data_dodania;
    }

    public void setStawka(Double stawka) {
        this.stawka = stawka;
    }
 

 
 
 
 
 
 
 
 
 
 
 
 
 
}
