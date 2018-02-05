package com.daycare_manager.daycare_manager.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Child must have a first name")
    @Size(min = 3, message = "A first name must be at least 3 characters.")
    private String first_name;

    @Column(nullable = false)
    @NotBlank(message = "Child must have a last name")
    @Size(min = 3, message = "A last name must be at least 3 characters.")
    private String last_name;

    // date of birth:
    @Column(nullable = true)
    @NotBlank(message = "User must pick a date")
    private String dob;

    @Column(nullable = false)
    private String gender;

    @ManyToOne
    private User parent;

    @ManyToOne
    private User teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "child")
    private List<ReportCard> reportCards = new ArrayList<>();


    public Child(){

    }

    public Child(String first_name, String last_name, String dob, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    // Getters and Setter for parents and teachers
    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    // Getter and Setter for Report Card


    public List<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(List<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }
}

