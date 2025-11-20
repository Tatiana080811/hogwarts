package ru.hogwarts.school.model;

public class Faculty {
    private String name;
    private Long id;
    private String color;

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
}
