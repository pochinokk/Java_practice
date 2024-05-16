package com.example.Practice24;

import com.example.Practice24.entity.Level;
import com.example.Practice24.repository.LevelRepository;
import com.example.Practice24.service.LevelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LevelServiceTest {
    @Mock
    private LevelRepository levelRepository;
    @InjectMocks
    private LevelService levelService;

    @Test
    public void testCreate() {
        Level expectedLevel = new Level(null, "easy", "test", null);
        when(levelRepository.save(any())).thenReturn(expectedLevel);
        Level createdLevel = levelService.create(expectedLevel);
        verify(levelRepository, times(1)).save(any());
        expectedLevel.setId(createdLevel.getId());
        assertEquals(expectedLevel, createdLevel);
    }

    @Test
    public void testReadAll() {
        Level expectedLevel1 = new Level(1L, "Medium", "First level", null);
        Level expectedLevel2 = new Level(2L, "Hard", "Second level", null);
        List<Level> expectedLevels = Arrays.asList(
                expectedLevel1,
                expectedLevel2
        );
        when(levelRepository.findAll()).thenReturn(expectedLevels);
        List<Level> levels = levelService.readAll();
        verify(levelRepository, times(1)).findAll();
        assertEquals(expectedLevels, levels);
    }

    @Test
    public void testRead() {
        Level expectedLevel = new Level(2L, "Hard", "Second level", null);
        when(levelRepository.findById(expectedLevel.getId())).thenReturn(Optional.of(expectedLevel));
        Level readLevel = levelService.read(2L);
        verify(levelRepository, times(1)).findById(expectedLevel.getId());
        assertEquals(expectedLevel, readLevel);
    }

    @Test
    public void testUpdate() {
        Level expectedLevel = new Level(1L, "Medium", "Updated level", null);
        when(levelRepository.existsById(1L)).thenReturn(true);
        when(levelRepository.save(any())).thenReturn(expectedLevel);
        Level updatedLevel = levelService.update(expectedLevel, 1L);
        verify(levelRepository, times(1)).save(expectedLevel);
        assertEquals(expectedLevel, updatedLevel);
    }

    @Test
    public void testDelete() {
        Long id = 37L;
        when(levelRepository.existsById(id)).thenReturn(true);
        boolean isDeleted = levelService.delete(id);
        verify(levelRepository, times(1)).deleteById(id);
        assertTrue(isDeleted);
    }

}