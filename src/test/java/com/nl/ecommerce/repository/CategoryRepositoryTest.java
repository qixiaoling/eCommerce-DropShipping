package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Category;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//will do the autowire
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }


    @Test
    void itShouldCheckIfCategoryNameExists(){
        //given
        Category category = new Category("testCups");
        underTest.save(category);
        //when
        Boolean exists = underTest.existsByName("testCups");
        //then
        AssertionsForClassTypes.assertThat(exists).isTrue();

    }
    @Test
    void itShouldCheckIfCategoryNameDoesNotExists(){
        //given
        Category category = new Category("testCups");

        //when
        Boolean exists = underTest.existsByName("testCups");
        //then
        AssertionsForClassTypes.assertThat(exists).isFalse();

    }

}