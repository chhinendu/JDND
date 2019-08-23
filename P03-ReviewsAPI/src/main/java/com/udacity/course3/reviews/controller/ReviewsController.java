package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ReviewDocumentRepository reviewDocumentRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    /**
     * Creates a review for a product.
     * @param productId The id of the product.
     * @param review Request body
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @Valid @RequestBody Review review) {

        return productRepository.findById(productId)
                .map(product -> {
                    review.setProduct(product);
                    Review savedReview = reviewRepository.save(review);
                    ReviewDocument reviewDocument = new ReviewDocument();
                    reviewDocument.setReviewId(savedReview.getReviewId());
                    reviewDocument.setTitle(review.getTitle());
                    reviewDocument.setDescription(review.getDescription());
                    reviewDocument.setProduct(review.getProduct());
                    reviewDocumentRepository.save(reviewDocument);
                    return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found for: " + productId));
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDocument>> listReviewsForProduct(@PathVariable("productId") Integer productId) {

        return productRepository.findById(productId)
                .map(product -> new ResponseEntity<>(reviewDocumentRepository.findByProduct(product)
                        .orElseThrow(() -> new ResourceNotFoundException("Reviews not Found for Product: " + productId)), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found for: " + productId));
    }
}
