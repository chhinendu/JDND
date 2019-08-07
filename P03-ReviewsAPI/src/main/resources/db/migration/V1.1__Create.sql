/* Create tables */
CREATE TABLE PRODUCT
(
    PRODUCT_ID   INT AUTO_INCREMENT,
    PRODUCT_NAME VARCHAR(300) NOT NULL,
    CONSTRAINT ORDER_PK PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE REVIEW
(
    REVIEW_ID   INT AUTO_INCREMENT,
    DESCRIPTION VARCHAR(500) NOT NULL,
    PRODUCT_ID  INT          NOT NULL,
    CONSTRAINT REVIEW_PK PRIMARY KEY (REVIEW_ID),
    CONSTRAINT REVIEW_FK FOREIGN KEY (REVIEW_ID)
        REFERENCES PRODUCT (PRODUCT_ID)
);

CREATE TABLE COMMENT
(
    COMMENT_ID  INT AUTO_INCREMENT,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    REVIEW_ID   INT           NOT NULL,
    CONSTRAINT COMMENT_PK PRIMARY KEY (COMMENT_ID),
    CONSTRAINT COMMENT_FK FOREIGN KEY (COMMENT_ID)
        REFERENCES REVIEW (REVIEW_ID)
);