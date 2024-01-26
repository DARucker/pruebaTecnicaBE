package com.prueba.PruebaTecnicaBE.service;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.exception.ListaVaciaException;
import com.prueba.PruebaTecnicaBE.exception.SuperheroeNotFoundException;
import com.prueba.PruebaTecnicaBE.repository.SuperheroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroeServImpl implements ISuperHeroeService{

    @Autowired
    private SuperheroeRepository superheroeRepository;
    @Override
    @CacheEvict(value = "superheroes", allEntries = true)
    public Superheroe create(Superheroe superheroe) {

        Superheroe superheroeToSave = new Superheroe();
        superheroeToSave.setId(superheroe.getId());
        superheroeToSave.setName(superheroe.getName());

        return superheroeRepository.save(superheroeToSave) ;
    }

    @Override
    public Superheroe finById(int id) {
        Optional <Superheroe> superheroeFound = superheroeRepository.findById(id);
        if(!superheroeFound.isPresent()){
            throw new SuperheroeNotFoundException("No existe un superheroe con id solicitado");
        }
        return superheroeFound.get() ;
    }

    @Override
    @Cacheable("superheroes")
    public Page <Superheroe> findAll(Pageable pageable) {

        Page <Superheroe> listEncontrada = superheroeRepository.findAll(pageable);
        Long cantPagina = listEncontrada.getTotalElements();

        if(listEncontrada.isEmpty()){
            if(!superheroeRepository.findAll().isEmpty()){
                throw new ListaVaciaException("La página solicitada no tiene datos");
            } else {
                throw new ListaVaciaException("No hay superhéroes cargados en la base de datos");
            }
        }

        return listEncontrada;
    }

    @Override
    public Superheroe updateSuperheroe(Superheroe superheroe) {

        Optional <Superheroe> superheroeFound = superheroeRepository.findById(superheroe.getId());
        if(!superheroeFound.isPresent()){
            throw new SuperheroeNotFoundException("No existe un superheroe con id solicitado");
        }
            Superheroe superheroeToUpdate = superheroeFound.get();
            superheroeToUpdate.setId(superheroe.getId());
            superheroeToUpdate.setName(superheroe.getName());

        return superheroeRepository.save(superheroeToUpdate);

    }

    @Override
    public void delete(int id) {
        Optional <Superheroe> superheroeFound = superheroeRepository.findById(id);
        if(!superheroeFound.isPresent()){
            throw new SuperheroeNotFoundException("No existe un superheroe con id solicitado");
        }
        superheroeRepository.deleteById(id);
    }

    @Override
    public List<Superheroe> findByName(String nameContaining) {
        List <Superheroe> listEncontrada = superheroeRepository.findByNameContaining(nameContaining);
        if(listEncontrada.isEmpty()){
            throw new ListaVaciaException("No se encontraron superheroes que contengan ese nombre");
        }
        return listEncontrada;
    }
}
