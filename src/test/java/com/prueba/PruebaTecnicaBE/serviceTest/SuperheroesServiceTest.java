package com.prueba.PruebaTecnicaBE.serviceTest;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.exception.ListaVaciaException;
import com.prueba.PruebaTecnicaBE.exception.SuperheroeNotFoundException;
import com.prueba.PruebaTecnicaBE.repository.SuperheroeRepository;
import com.prueba.PruebaTecnicaBE.service.SuperHeroeServImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SuperheroesServiceTest {

    @Mock
    private SuperheroeRepository superheroeRepository;
    @InjectMocks
    private SuperHeroeServImpl superHeroeService;

    private Superheroe superheroeTest;

    @BeforeAll
    void beforeAll(){

        superheroeTest = Superheroe.builder()
                .id(1)
                .name("Superman")
                .build();

    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("Save")
    void WhenSaveSuperheroe_ThenGetExpectedName(){

        when(superheroeRepository.save(superheroeTest)).thenReturn(superheroeTest);
        String nameExpected = "Superman";
        assertEquals(nameExpected, superheroeTest.getName());

    }

    @Test
    @Order(2)
    @DisplayName("Find by Id")
    void WhenFindById_ThenCompareName(){

        when(superheroeRepository.findById(1)).thenReturn(Optional.of(superheroeTest));
        String nameExpected = "Superman";
        assertEquals(nameExpected, superheroeTest.getName());
    }

    @Test
    @Order(3)
    @DisplayName("Find by Id - Exception")
    void WhenFindByIdNotFound_ThenThrowException(){

        int idNotPresent = 0;
        when(superheroeRepository.findById(idNotPresent)).thenReturn(Optional.empty());
        assertThrows(SuperheroeNotFoundException.class, ()-> {superHeroeService.finById(idNotPresent);});
        }


    @Test
    @Order(4)
    @DisplayName("Find All")
    void WhenSaveTwoObjects_ThenFindAllSizeIs2(){
        List<Superheroe> superheroeList = new ArrayList<>();
        superheroeList.add(new Superheroe(1, "Batman"));
        superheroeList.add(new Superheroe(1, "Superman"));

        when(superHeroeService.findAll()).thenReturn(superheroeList);
        int superHeroesCount = 2;
        assertEquals(superHeroesCount, superheroeList.size());

    }

    @Test
    @Order(5)
    @DisplayName("Update")
    void WhenFindUpdate_ThenCheckNewName(){

        Superheroe superheroeUpdate = new Superheroe(1, "SuperUpdate");

        when(superheroeRepository.save(superheroeUpdate)).thenReturn(superheroeUpdate);
        when(superheroeRepository.findById(1)).thenReturn(Optional.of(superheroeUpdate));

        Superheroe superheroeToTest = superHeroeService.updateSuperheroe(superheroeUpdate);
        String nameExpected = "SuperUpdate";
        assertEquals(nameExpected, superheroeToTest.getName());
    }
    @Test
    @Order(6)
    @DisplayName("Update - Exception")
    void WhenUpdateByIdNotFound_ThenThrowException(){

        int idNotPresent = 0;
        Superheroe superheroeUpdate = new Superheroe(1, "SuperUpdate");

        when(superheroeRepository.save(superheroeUpdate)).thenReturn(superheroeUpdate);
        when(superheroeRepository.findById(idNotPresent)).thenReturn(Optional.empty());
        assertThrows(SuperheroeNotFoundException.class, ()-> {superHeroeService.updateSuperheroe(superheroeUpdate);});
    }

    @Test
    @Order(7)
    @DisplayName("Delete")
    void WhenDelete_ThenThrowException(){

        int idPresent = 1;
        when(superheroeRepository.findById(idPresent)).thenReturn(Optional.of(superheroeTest));
        superHeroeService.delete(idPresent);
        verify(superheroeRepository, times(1)).deleteById(idPresent);
    }

    @Test
    @Order(8)
    @DisplayName("Find by Name")
    void WhenFindByName_ThenReturnListOfSuperheroes(){

        Superheroe superheroeTest1 = new Superheroe(2, "Batman");
        Superheroe superheroeTest2 = new Superheroe(3, "Samanta");
        List<Superheroe> listaTest = Arrays.asList(superheroeTest, superheroeTest1,superheroeTest2);
        String nameContains = "man";
        when(superheroeRepository.findByNameContaining(nameContains)).thenReturn(listaTest);
        List<Superheroe> result = superHeroeService.findByName(nameContains);
        assertEquals(listaTest, result);
    }
    @Test
    @Order(9)
    @DisplayName("Find by Name - Exception")
    void WhenFindByNameReturnsEmptyList_ThenThrowException(){

        Superheroe superheroeTest1 = new Superheroe(2, "Batman");
        Superheroe superheroeTest2 = new Superheroe(3, "Samanta");
        String nameContains = "man";
        when(superheroeRepository.findByNameContaining(nameContains)).thenReturn(Arrays.asList());
        ListaVaciaException ex = assertThrows(ListaVaciaException.class, () -> {superHeroeService.findByName(nameContains);});

        assertEquals("No se encontraron superheroes que contengan ese nombre", ex.getMessage());
    }
}
