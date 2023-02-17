package org.generation.farmacia.repository;

import java.util.List;

import org.generation.farmacia.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;



public interface PostagemRepository extends JpaRepository<Postagem, Long>{

    List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
