package org.generation.blogpessoal.repository;

import java.util.Optional;

import java.util.List;

import org.generation.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario(String email);

	public List <Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);	
	
}

---- 
	
	

package org.generation.db_blogpessoal.repository;

import java.util.List;

import org.generation.db_blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface postagemRepository extends JpaRepository<Postagem, Long> {

    List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
