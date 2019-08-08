package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document
public class ReviewDocument {

    @Id
    private String reviewId;

    @NotNull(message = "Please provide description")
    private String description;

    private Product product;

    private List<String> comments;

    public List<String> getComments() {
        if (comments == null) {
            return new ArrayList<>();
        } else {
            return comments;
        }

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

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
