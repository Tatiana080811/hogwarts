package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String color;
    @Column(nullable = false)
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

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

        @Override
        public String toString() {
            return "Faculty{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", color='" + color + '\'' +
                    '}';


    }
}
