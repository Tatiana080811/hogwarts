package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    public Faculty() {
    }

    public Faculty(String name, Long id, String color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", color='" + color + '\'' +
                '}';
    }

    public Long getId() {
        return null;
    }

    public void setId(Long id) {

    }
}
