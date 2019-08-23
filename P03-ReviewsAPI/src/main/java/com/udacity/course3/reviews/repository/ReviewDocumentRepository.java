package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ReviewDocumentRepository extends MongoRepository<ReviewDocument, Integer> {

    Optional<List<ReviewDocument>> findByProduct(Product product);

    Optional<ReviewDocument> findByReviewId(int id);
}
