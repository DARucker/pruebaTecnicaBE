package com.prueba.PruebaTecnicaBE.service;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;

import java.util.List;

public interface ISuperHeroeService {

    Superheroe create(Superheroe superheroe);

    List <Superheroe> findAll();

    Superheroe finById(int id);

    Superheroe updateSuperheroe(Superheroe superheroe);

    void delete(int id);

    List<Superheroe> findByName(String name);
}
