package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {

        return productRepository.findById(productId)
                .map(product -> {
                    review.setProduct(product);
                    return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);
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
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {

        return productRepository.findById(productId)
                .map(product -> new ResponseEntity<>(reviewRepository.findByProduct(product)
                        .orElseThrow(() -> new ResourceNotFoundException("Reviews not Found for Product: " + productId)), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found for: " + productId));
    }
}
