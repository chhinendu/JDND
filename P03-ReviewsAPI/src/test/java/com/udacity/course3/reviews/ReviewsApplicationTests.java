package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void contextLoads() {
    }

    @Before
    public void before() {
        productRepository.save(getProduct());
        reviewRepository.save(getReview());
        commentRepository.save(getComment());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Laptop");
        product.setDescription("Description");
        return product;
    }

    private Review getReview() {
        Review review = new Review();
        review.setReviewId(1);
        review.setDescription("Review TEST");
        review.setTitle("Review");
        review.setProduct(getProduct());
        return review;
    }

    private Comment getComment() {
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setDescription("Comment TEST");
        comment.setTitle("Comment");
        comment.setReview(getReview());
        return comment;
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void testCreateProduct(){
        assertThat(productRepository.save(getProduct())).isNotNull();
    }

    @Test
    public void testFindProduct(){
        List<Product> products = productRepository.findAll();
        assertThat(products).isNotEmpty();
    }

    @Test
    public void testCreateReview(){

        assertThat(reviewRepository.save(getReview())).isNotNull();
    }

    @Test
    public void testFindReview(){
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).isNotEmpty();
    }

    @Test
    public void testCreateComment(){

        assertThat(commentRepository.save(getComment())).isNotNull();
    }

    @Test
    public void testFindComment(){
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isNotEmpty();
    }

}
