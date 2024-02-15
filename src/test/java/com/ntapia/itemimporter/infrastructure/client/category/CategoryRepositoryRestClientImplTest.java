package com.ntapia.itemimporter.infrastructure.client.category;

import com.ntapia.itemimporter.domain.CategoryRepository;
import com.ntapia.itemimporter.infrastructure.config.MeliRestClient;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryRestClientImplTest {

    @Mock
    private MeliRestClient meliRestClient;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init() {
        categoryRepository = new CategoryRepositoryRestClientImpl(meliRestClient);
    }

    @Test
    void testFindByIdHappyPath() {
        var categoryId = UUID.randomUUID().toString();
        var categoryResponse = new CategoryResponse(categoryId, "myCategory");

        when(meliRestClient.getCategory(categoryId)).thenReturn(categoryResponse);

        var categoryOptional = categoryRepository.findById(categoryId);

        verify(meliRestClient, times(1)).getCategory(categoryId);

        assertTrue(categoryOptional.isPresent());
        var category = categoryOptional.get();
        assertEquals(categoryResponse.id(), category.getId());
        assertEquals(categoryResponse.name(), category.getName());
    }

    @Test
    void testFindByIdWhenReturnNotFound() {
        var categoryId = UUID.randomUUID().toString();
        var exception = mock(FeignException.NotFound.class);

        when(meliRestClient.getCategory(categoryId)).thenThrow(exception);

        var categoryOptional = categoryRepository.findById(categoryId);

        verify(meliRestClient, times(1)).getCategory(categoryId);
        assertTrue(categoryOptional.isEmpty());
    }

    @Test
    void testFindByIdWhenTrowsClientException() {
        var categoryId = UUID.randomUUID().toString();
        var message = "MyMessage";

        var exception = mock(FeignException.class);
        when(exception.getMessage()).thenReturn(message);
        when(meliRestClient.getCategory(categoryId)).thenThrow(exception);

        try {
            categoryRepository.findById(categoryId);
            fail("should have thrown");
        } catch (FindCategoryException findCategoryException) {
            verify(meliRestClient, times(1)).getCategory(categoryId);
            assertEquals(findCategoryException.getMessage(), String.format("Id: %s - %s", categoryId, message));
        }
    }
}