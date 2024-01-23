package com.prueba.PruebaTecnicaBE.service;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;

import java.util.List;

public interface ISuperHeroeService {

    Superheroe create(Superheroe superheroe);

    List <Superheroe> findAll();
}
