package com.example.crudapplication;

import jakarta.persistence.*;

@Entity
@Table(name ="details")
public class Details{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Version
    private Integer version;
    public Details(){}
    // Constructor
    public Details(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    //getters & setters
    public Integer getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
