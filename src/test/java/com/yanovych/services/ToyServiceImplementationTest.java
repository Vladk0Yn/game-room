package com.yanovych.services;

import com.yanovych.entities.Child;
import com.yanovych.entities.Toy;
import com.yanovych.entities.enums.*;
import com.yanovych.repositories.interfaces.ToyRepository;
import com.yanovych.services.implementations.ToyServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToyServiceImplementationTest {
    @Mock
    private ToyRepository toyRepository;
    @InjectMocks
    private ToyServiceImplementation toyServiceImplementation;

    @Test
    void getToyByIdIfToyExist() {
        Toy toyExpected = new Toy(1L, "Test1", 1, 1.0, null,
                ToyType.ANIMAL, ToySize.MEDIUM, Color.MULTI_COLOR, ToyMaterial.WOOD);
        when(toyRepository.getToyById(1L)).thenReturn(toyExpected);
        Toy actualToy = toyServiceImplementation.getToyById(1L);
        assertEquals(toyExpected, actualToy);
        verify(toyRepository, times(1)).getToyById(1L);
    }
    @Test
    void getToyByIdIfToyNotExist() {
        when(toyRepository.getToyById(1L)).thenReturn(null);
        Toy actualToy = toyServiceImplementation.getToyById(1L);
        assertNull(actualToy);
        verify(toyRepository, times(1)).getToyById(1L);
    }
    @Test
    void getAllToys() {
        List<Toy> expectedToys = new ArrayList<>();
        expectedToys.add(
                new Toy(1L, "Test1", 1, 1.0, null,
                        ToyType.ANIMAL, ToySize.MEDIUM, Color.MULTI_COLOR, ToyMaterial.WOOD));
        expectedToys.add(
                new Toy(2L, "Test2", 1, 1.0, null,
                        ToyType.ANIMAL, ToySize.MEDIUM, Color.MULTI_COLOR, ToyMaterial.WOOD));

        when(toyRepository.getAllToys()).thenReturn(expectedToys);

        List<Toy> actualToys = toyServiceImplementation.getAllToys();

        assertEquals(expectedToys, actualToys);
        verify(toyRepository, times(1)).getAllToys();
    }
    @Test
    void createToy() {
        Toy toy = new Toy(1L, "Test1", 1, 1.0, null,
                ToyType.ANIMAL, ToySize.MEDIUM, Color.MULTI_COLOR, ToyMaterial.WOOD);
        toyServiceImplementation.createToy(toy);
        verify(toyRepository, times(1)).addToy(toy);
    }
}
