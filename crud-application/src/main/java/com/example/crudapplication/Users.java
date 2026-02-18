package com.example.crudapplication;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @Version
    private Integer version;

    public Users(){}

    //Constructor
    public Users(String username,String password,String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //getters & setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword( String password){
        this.password = password;
    }
    public String getEmail(){ return email; }
    public void setEmail(String email){this.email = email; }
}
