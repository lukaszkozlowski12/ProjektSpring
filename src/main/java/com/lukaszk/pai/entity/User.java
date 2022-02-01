/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lukaszk.pai.entity;

/**
 *
 * @author Lukasz
 */
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name = "Users")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Integer userid;
 
@NotBlank(message = "IS empty")
@Pattern(regexp = "[a-zA-Z]{2,30}",
message="Podaj poprawnie imię")
 private String name;
 
@Pattern(regexp = "[a-zA-Z]{2,30}",
message="Podaj poprawnie nazwisko")
@NotBlank(message = "IS empty")
 private String surname;
 
@Size(min=3, max=10, message = "Rozmiar od 3 do 10!")
 @NotBlank(message = "Nie może być puste")

 private String login;
 
 @NotBlank(message = "Nie może być puste hasło")
  @Size(min=3, max=10, message = "Rozmiar od 3 do 10!")
// @Pattern(regexp = "[a-zA-Z]{2,30}",message="Podaj poprawnie haslo")
 private String password;
 

 
 public User() {
 }
 public User(String name, String surname,String login, String password) {
 this.name = name;
 this.surname = surname;
 this.login = login;
 this.password = password;
 }
 //getters and setters

 

 

 
    public Integer getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
 
 
 
}