package me.Tamaninja.test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "location")
public class Location implements Serializable {

    @Id
    @Column(name = "location_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "location_desc", nullable = true)
    private String desc;

    public Location(Long id,String desc) {
        this.id = id;
        this.desc = desc;
    }


    public Location() {}

    public Long getId() {
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
