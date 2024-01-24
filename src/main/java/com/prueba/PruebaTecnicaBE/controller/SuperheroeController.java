package com.prueba.PruebaTecnicaBE.controller;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.service.ISuperHeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "api/v1/superheroes")
public class SuperheroeController {

    @Autowired
    private ISuperHeroeService superHeroeService;

    @PostMapping("/create")
    public ResponseEntity<Superheroe> create (@RequestBody Superheroe superheroe){

        return ResponseEntity.status(201).body(superHeroeService.create(superheroe));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Superheroe> findById(@PathVariable("id") int id){
        Superheroe superheroeFound = superHeroeService.finById(id);
        return ResponseEntity.status(200).body(superheroeFound);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Superheroe>> findByName(@PathVariable("name") String name){
        List<Superheroe> listaObtenida = superHeroeService.findByName(name);
        return ResponseEntity.status(200).body(listaObtenida);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Superheroe>> findAll (){

        return ResponseEntity.status(200).body(superHeroeService.findAll());
    }

    @PutMapping ("/update")
    public ResponseEntity<Superheroe> update (@RequestBody Superheroe superheroe) {
        return ResponseEntity.status(200).body(superHeroeService.updateSuperheroe(superheroe));
    }

    @DeleteMapping ("/delete/{id}")
    public String delete (@PathVariable ("id") int id) {
        superHeroeService.delete(id);
        return "Superheroe eliminado de la base de datos";
    }
}
