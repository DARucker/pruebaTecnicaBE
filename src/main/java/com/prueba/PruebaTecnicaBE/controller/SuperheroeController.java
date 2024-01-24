package com.prueba.PruebaTecnicaBE.controller;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import com.prueba.PruebaTecnicaBE.service.ISuperHeroeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Prueba Técnica Backend", description = "Prueba técnica realizada por Darío Rucker")
@RestController
@RequestMapping (value = "api/v1/superheroes")
public class SuperheroeController {

    @Autowired
    private ISuperHeroeService superHeroeService;


    @Operation(summary= "Crear un superhéroe", description = "Permite crear un superheroe y lo inserta en la base de datos.")
    @ApiResponse(responseCode = "201", description = "El superhéroe ha sido creado correctamente", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @PostMapping("/create")
    public ResponseEntity<Superheroe> create (@RequestBody Superheroe superheroe){
        return ResponseEntity.status(201).body(superHeroeService.create(superheroe));
    }

    @Operation(summary= "Consulta un superhéroe", description = "Permite consultar un superheroe único mediante su ID.")
    @ApiResponse(responseCode = "200", description = "El superhéroe ha sido encontrado", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @ApiResponse(responseCode = "404", description = "No se encontró un superhéroe con el ID solicitado", content = @Content)
    @GetMapping("/findById/{id}")
    public ResponseEntity<Superheroe> findById(@PathVariable("id") int id){
        Superheroe superheroeFound = superHeroeService.finById(id);
        return ResponseEntity.status(200).body(superheroeFound);
    }

    @Operation(summary= "Consulta superhéroes por nombre", description = "Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro enviado en la petición.")
    @ApiResponse(responseCode = "200", description = "Lista de superhéroes que cumplen con el criterio", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @ApiResponse(responseCode = "404", description = "No se encontró ningún superhéroe", content = @Content)
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Superheroe>> findByName(@PathVariable("name") String name){
        List<Superheroe> listaObtenida = superHeroeService.findByName(name);
        return ResponseEntity.status(200).body(listaObtenida);
    }

    @Operation(summary= "Consulta la lista de superhéroes", description = "Devuelve una lista completa de superhéroes existentes en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista completa de superhéroes.", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @ApiResponse(responseCode = "404", description = "No se encontró ningún superhéroe", content = @Content)
    @GetMapping("/findAll")
    public ResponseEntity<List<Superheroe>> findAll (){
        return ResponseEntity.status(200).body(superHeroeService.findAll());
    }

    @Operation(summary= "Actualiza el nombre de un superhéroe", description = "Encuentra un superhéroe por su ID y le actualiza el nombre.")
    @ApiResponse(responseCode = "200", description = "Actualizado correctamente", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @ApiResponse(responseCode = "404", description = "No se encontró ningún superhéroe con el ID solicitado", content = @Content)
    @PutMapping ("/update")
    public ResponseEntity<Superheroe> update (@RequestBody Superheroe superheroe) {
        return ResponseEntity.status(200).body(superHeroeService.updateSuperheroe(superheroe));
    }

    @Operation(summary= "Eliminar un superhéroe", description = "Permite eliminar un superhéroe a través del ID.")
    @ApiResponse(responseCode = "200", description = "Eliminado correctamente", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Superheroe.class))})
    @ApiResponse(responseCode = "404", description = "No se encontró ningún superhéroe", content = @Content)
    @DeleteMapping ("/delete/{id}")
    public String delete (@PathVariable ("id") int id) {
        superHeroeService.delete(id);
        return "Superheroe eliminado de la base de datos";
    }
}
