package com.prueba.PruebaTecnicaBE.service;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISuperHeroeService {

    Superheroe create(Superheroe superheroe);

    Page <Superheroe> findAll(Pageable pageable);

    Superheroe finById(int id);

    Superheroe updateSuperheroe(Superheroe superheroe);

    void delete(int id);

    List<Superheroe> findByName(String name);
}
