package com.prueba.PruebaTecnicaBE.repository;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroeRepository extends JpaRepository <Superheroe, Integer> {

    List<Superheroe> findByNameContaining(String nameContaining);
}
