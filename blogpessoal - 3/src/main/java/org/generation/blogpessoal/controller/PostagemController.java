package org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogpessoal.model.Postagem;
import org.generation.blogpessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	// getAll() 🡪 Retorna todos os Objetos da Classe Postagem persistidos no Banco de dados.
		@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}		
	
	// getById() com a função de trazer uma única Postagem identificada pelo id.
		
		@GetMapping("/{id}")
		public ResponseEntity<Postagem> getById(@PathVariable Long id){
			return postagemRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

		// getByTitulo() com a função de trazer todas Postagens cujo titulo possua a palavra pesquisada.
		
		@GetMapping("/titulo/{titulo}")	
		public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
			return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
			}
			
			// post() com a função de gravar (persistir) uma nova Postagem no banco de dados. 
		
		@PostMapping
		public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
		}
						
			// put() com a função de atualizar os dados de uma Postagem.
		
		@PutMapping
		public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
			return postagemRepository.findById(postagem.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
							.body(postagemRespository.save(postagem)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)).build());	
		}
			
			
			// delete() com a função de apagar uma Postagem no banco de dados  
		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id){
			Optional<Postagem> postagem = postagemRepository.findById(id);
			
			if(postagem.isEmpty())
				throw new RespondeStatusExcepetion(HttpStatus.NOT_FOUND);
			
			postagemRepository.deleteById(id);
			
		}
}
