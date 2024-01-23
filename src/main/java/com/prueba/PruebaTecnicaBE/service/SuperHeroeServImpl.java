package com.prueba.PruebaTecnicaBE.service;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.repository.SuperheroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroeServImpl implements ISuperHeroeService{

    @Autowired
    private SuperheroeRepository superheroeRepository;
    @Override
    public Superheroe create(Superheroe superheroe) {

        Superheroe superheroeToSave = new Superheroe();
        superheroeToSave.setId(superheroe.getId());
        superheroeToSave.setName(superheroe.getName());

        return superheroeRepository.save(superheroeToSave) ;
    }

    @Override
    public List<Superheroe> findAll() {
        return superheroeRepository.findAll();
    }
}
