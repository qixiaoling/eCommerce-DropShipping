package com.nl.ecommerce.service;

import com.nl.ecommerce.Exception.BadRequestException;
import com.nl.ecommerce.Exception.NotFoundException;
import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.repository.CategoryRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;



import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


class CategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;
    private AutoCloseable autoCloseable;
    private CategoryService underTest;

    @BeforeEach
    void setUp(){
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CategoryService(categoryRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCategory() {
        //when
        underTest.getAllCategory();
        //then
        verify(categoryRepository).findAll();

    }

    @Test
    void canAddCategory() {
        //given
        Category category = new Category("testCups");
        //when
        underTest.addCategory(category);
        //then
        ArgumentCaptor<Category> categoryArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);

        verify(categoryRepository).save(categoryArgumentCaptor.capture());

        Category capturedCategory = categoryArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedCategory).isEqualTo(category);

    }
    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Category category = new Category("testCups");
        BDDMockito.given( categoryRepository.existsByName(category.getName())).willReturn(true);
        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.addCategory(category))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("category name: " + category.getName() + "taken");

        verify(categoryRepository, never()).save(category);

    }


    @Test
    @Disabled
    void getCategoryById() {
    }

    @Test
    @Disabled
    void updateCategoryById() {
    }

    @Test
    @Disabled
    void deleteCategoryById() {
        //given
        String name = "testCups";
        BDDMockito.given( categoryRepository.existsByName(name)).willReturn(true);
        //when
        underTest.deleteCategoryById(name);
        //then
        verify(categoryRepository).deleteById(name);
    }

    @Test
    void willThrowWhenDeleteCategoryNotFound(){
        //given
        String name = "testCups";
        BDDMockito.given(categoryRepository.existsByName(name)).willReturn(false);
        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.deleteCategoryById(name))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Category name: " + name + "does not exists.");

        verify(categoryRepository,never()).deleteById(name);

    }
}