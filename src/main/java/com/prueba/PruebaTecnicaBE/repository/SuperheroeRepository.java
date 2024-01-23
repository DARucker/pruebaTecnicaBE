package com.prueba.PruebaTecnicaBE.repository;

import com.prueba.PruebaTecnicaBE.entity.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroeRepository extends JpaRepository <Superheroe, Integer> {
}
