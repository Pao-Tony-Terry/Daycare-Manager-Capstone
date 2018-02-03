package com.daycare_manager.daycare_manager.model;

import javax.persistence.*;

@Entity
@Table(name = "report_card")
public class ReportCard {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String feeling;

    @Column
    private String sleep;

    @Column
    private String meal;

    @Column
    private String how_ate;

    @Column(name = "`change`")
    private String change;

    @Column
    private String comments;

    @Column
    private String items_needed;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    public ReportCard() {
    }

    public ReportCard(String full_name, String feeling, String sleep, String meal, String howAte, String change, String comments, String itemsNeeded) {
        this.feeling = feeling;
        this.sleep = sleep;
        this.meal = meal;
        this.how_ate = how_ate;
        this.change = change;
        this.comments = comments;
        this.items_needed = items_needed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getHow_ate() {
        return how_ate;
    }

    public void setHow_ate(String how_ate) {
        this.how_ate = how_ate;
    }

    public String getItems_needed() {
        return items_needed;
    }

    public void setItems_needed(String items_needed) {
        this.items_needed = items_needed;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



    // Getter and Setter for child report cards



    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
