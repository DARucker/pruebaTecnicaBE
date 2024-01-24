package com.prueba.PruebaTecnicaBE.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Superheroe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Es un número de identificación del superhéroe")
    private int id;
    @Schema(description = "Este campo contiene el nombre  del superhéroe")
    private String name;

}
