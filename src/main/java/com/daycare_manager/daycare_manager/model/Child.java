package com.daycare_manager.daycare_manager.model;

import javax.persistence.*;

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
    @JoinColumn(name="user_id")  //This line is not necessary bc User hast the mapping to user in the one to many
    private User user;


}
