package com.example.crudapplication;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fname;
    private String lname;
    private String email;
    private String number;
    private String birthdate;
    private Float markssee;
    private Float marksPlus2;
    private String course;
    private String gender;

    @Version
    private Integer version;

    //Empty constructor for JPA
    public Students(){}

    //Constructor
    public Students( String fname, String lname,String email, String number, String birthdate, Float markssee, Float marksPlus2, String course, String gender){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.number = number;
        this.birthdate = birthdate;
        this.markssee  = markssee;
        this.marksPlus2  = marksPlus2;
        this.course = course;
        this.gender = gender;
    }

    //getters & setters
    public Integer getId(){ return id; }
    public void setId(Integer id){ this.id = id; }
    public String getFname(){ return fname;}
    public void setFname(String fname){ this.fname = fname;}
    public String getLname(){ return lname;}
    public void setLname(String lname){ this.lname = lname;}
    public String getEmail(){ return email;}
    public void setEmail(String email){ this.email = email;}
    public String getNumber(){return number;}
    public void setNumber(String number){this.number = number;}
    public String getBirthdate(){return birthdate;}
    public void setBirthdate(String birthdate){this.birthdate  =birthdate;}
    public Float getMarkssee(){return markssee;}
    public void setMarkssee(Float markssee){this.markssee = markssee;}
    public Float getMarksPlus2(){ return marksPlus2;}
    public void setMarksPlus2(Float marksPlus2){this.marksPlus2 = marksPlus2; }
    public String getCourse(){return course;}
    public void setCourse(String course){this.course = course;}
    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}


}
