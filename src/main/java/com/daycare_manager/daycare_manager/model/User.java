package com.daycare_manager.daycare_manager.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id  @GeneratedValue
    private long id;


    @Column(nullable = false)
    @NotBlank(message = "Users must have a first name")
    @Size(min = 3, message = "A first name must be at least 3 characters.")
    private String first_name;


    @Column(nullable = false)
    @NotBlank(message = "Users must have a last name")
    @Size(min = 3, message = "A last name must be at least 3 characters.")
    private String last_name;


    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username can't be empty")
    @Size(min = 8, message = "Username must be at least 8 characters.")
    private String username;


    @Column(nullable = false)
    @NotBlank(message = "Username can't be empty")
    @Size(min = 8, message = "Username must be at least 8 characters.")
    private String password;


    @Column(nullable = false, unique = true)
    @NotBlank(message = "email can't be empty")
    @Size(min = 8, message = "email must be valid.")
    private String email;


    @Column(nullable = false, length = 20)
    @NotBlank(message = "phone must be numeric")
    @Size(min = 10, message = "Phone must be at least 10 numeric characters.")
    private String phone;


    @Column(nullable = false)
    private String gender;

    //Employee 0 (or false) is for parents
    //Employee 1 (or true) is for teachers
    @Column(nullable = false)
    private boolean employee;

    @Column(nullable = false)
    private boolean isAdmin;



    // one user can have multiple children.
    // Cascade all means is not going to allow to have empty users for a child.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Child> students;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Child> children;

    // Contructors:

    public User(){

    }

    // It is cloning the user (for security measures):
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        first_name = copy.first_name;
        last_name = copy.last_name;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        this.phone = copy.phone;
        this.gender = copy.gender;
        this.employee = copy.employee;
        this.isAdmin = copy.isAdmin;
    }

    public User(String first_name, String last_name, String username, String password, String email, String phone, String gender, boolean employee) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.employee = employee;
    }


    // Getters and setters:
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    //Getters and setters for List of students
    public List<Child> getStudents() {
        return students;
    }

    public void setStudents(List<Child> students) {
        this.students = students;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void cleanUpPhoneFormat(){
        this.phone = this.phone.replace("(", "").replace(") ", "").replace("-", "");
    }
}
