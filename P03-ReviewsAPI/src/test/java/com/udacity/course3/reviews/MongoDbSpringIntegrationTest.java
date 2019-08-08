package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.List;

@ContextConfiguration(classes = ReviewsApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDbSpringIntegrationTest {
    @Autowired
    ReviewDocumentRepository reviewDocumentRepository;

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")

    @Before
    public void before() {
        reviewDocumentRepository.save(getReviewDocument());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Laptop");
        product.setDescription("Description");
        return product;
    }

    private ReviewDocument getReviewDocument() {
        ReviewDocument reviewDocument = new ReviewDocument();
        reviewDocument.setProduct(getProduct());
        reviewDocument.setDescription("Best Review");
        reviewDocument.setTitle("Review");
        return reviewDocument;
    }

    @Test
    public void testCreateAndFetch() {
        /*Test Create*/
        assertThat(reviewDocumentRepository.save(getReviewDocument())).isNotNull();

        /*Test fetch*/
        List<ReviewDocument> reviews = reviewDocumentRepository.findAll();
        assertThat(reviews).isNotEmpty();
    }
}
