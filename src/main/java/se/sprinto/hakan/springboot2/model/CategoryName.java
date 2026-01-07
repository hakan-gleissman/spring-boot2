package se.sprinto.hakan.springboot2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category_name")
public class CategoryName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String name;

    public CategoryName() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}