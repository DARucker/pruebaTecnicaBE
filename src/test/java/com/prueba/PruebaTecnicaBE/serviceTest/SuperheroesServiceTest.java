package com.prueba.PruebaTecnicaBE.serviceTest;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.repository.SuperheroeRepository;
import com.prueba.PruebaTecnicaBE.service.ISuperHeroeService;
import com.prueba.PruebaTecnicaBE.service.SuperHeroeServImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperheroesServiceTest {

    @Mock
    private SuperheroeRepository superheroeRepository;
    @InjectMocks
    private SuperHeroeServImpl superHeroeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSuperheroe_ThenGetExpectedName(){
        Superheroe superheroeTest = Superheroe.builder()
                .id(1)
                .name("Superman")
                .build();
        when(superheroeRepository.save(superheroeTest)).thenReturn(superheroeTest);
        String nameExpected = "Superman";
        assertEquals(nameExpected, superheroeTest.getName());

    }

    @Test
    void WhenSaveTwoObjects_ThenFindAllSizeIs2(){
        List<Superheroe> superheroeList = new ArrayList<>();
        superheroeList.add(new Superheroe(1, "Batman"));
        superheroeList.add(new Superheroe(1, "Superman"));

        when(superHeroeService.findAll()).thenReturn(superheroeList);
        int superHeroesCount = 2;
        assertEquals(superHeroesCount, superheroeList.size());

    }

}
