package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    ReviewDocumentRepository reviewDocumentRepository;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") String reviewId, @Valid @RequestBody Comment comment) {
        return reviewDocumentRepository.findById(reviewId)
                .map(review -> {
                    review.getComments().add(comment.getDescription());
                    return new ResponseEntity<>(reviewDocumentRepository.save(review), HttpStatus.CREATED);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Review not Found for: " + reviewId));
    }

    /**
     * List comments for a review.
     * <p>
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listCommentsForReview(@PathVariable("reviewId") String reviewId) {
        return reviewDocumentRepository.findById(reviewId)
                .map(review ->
                        new ResponseEntity<>(review.getComments(), HttpStatus.OK)
                )
                .orElseThrow(() -> new ResourceNotFoundException("Review not Found for: " + reviewId));
    }
}
