package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
public class ReviewDocument {

    @Id
    private String id;

    @NotNull(message = "Please provide description")
    private String description;

    private Product product;

    private List<String> comments = new ArrayList<>();

    @NotNull(message = "Please provide title")
    private String title;

    public List<String> getComments() {
            return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
