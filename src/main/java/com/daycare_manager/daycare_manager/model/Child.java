package com.daycare_manager.daycare_manager.model;

import antlr.collections.List;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    // date of birth:
    @Column(nullable = true)
    private String dob;

    @Column(nullable = false)
    private String gender;

    @ManyToOne
    private User parent;

    @ManyToOne
    private User teacher;




}

