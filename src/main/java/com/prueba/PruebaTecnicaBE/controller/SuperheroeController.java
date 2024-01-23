package com.prueba.PruebaTecnicaBE.controller;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.service.ISuperHeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping (value = "api/v1/superheroes")
public class SuperheroeController {

    @Autowired
    private ISuperHeroeService superHeroeService;

    @PostMapping("/create")
    public Superheroe create (@RequestBody Superheroe superheroe){

        return superHeroeService.create(superheroe);
    }

    @GetMapping("/findAll")
    public List<Superheroe> findAll (){

        return superHeroeService.findAll();
    }



}
