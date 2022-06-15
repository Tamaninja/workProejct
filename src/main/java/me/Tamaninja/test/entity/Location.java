package me.Tamaninja.test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "location")
public class Location {

    @Id
    @Column(name = "location_id", nullable = false, unique = true)
    private int id;

    @Column(name = "location_desc", nullable = true)
    private String desc;

    public Location(int id,String desc) {
        this.id = id;
        this.desc = desc;
    }


    public Location() {}

    public int getId() {
        return (id);
    }
    public String getDescription() {
        return (this.desc);
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
