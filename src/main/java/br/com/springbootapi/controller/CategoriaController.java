package br.com.springbootapi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootapi.dto.CategoriaDto;
import br.com.springbootapi.entity.Categoria;
import br.com.springbootapi.repository.CategoriaRepository;
import br.com.springbootapi.utils.DtoUtils;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping(value = "/")
	public List<Categoria> Get() {
		return categoriaRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable(value = "id") long id) {
		Optional<Categoria> pessoa = categoriaRepository.findById(id);
		if (pessoa.isPresent())
			return new ResponseEntity<Categoria>(pessoa.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Categoria> Post(@RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaRepository.save(DtoUtils.categoriaNew(categoriaDto));
		log.info(categoria.toString());
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> Put(@PathVariable(value = "id") long id, @RequestBody CategoriaDto update) {
		Optional<Categoria> oldObject = categoriaRepository.findById(id);
		if (oldObject.isPresent()) {
			Categoria categoria = oldObject.get();
			log.info("Old "+ categoria.toString());
			categoriaRepository.save(DtoUtils.categoriaUpdate(categoria, update));
			log.info("New "+ categoria.toString());
			return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			categoriaRepository.delete(categoria.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}