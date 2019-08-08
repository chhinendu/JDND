package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ReviewsApplication.class)
@DataMongoTest
@RunWith(SpringRunner.class)
public class MongoDbSpringIntegrationTest {
    @Autowired
    ReviewDocumentRepository reviewDocumentRepository;

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
