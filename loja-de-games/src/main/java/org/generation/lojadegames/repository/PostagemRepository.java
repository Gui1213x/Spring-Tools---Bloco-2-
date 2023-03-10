package org.generation.lojadegames.repository;

import java.util.List;

import org.generation.lojadegames.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
