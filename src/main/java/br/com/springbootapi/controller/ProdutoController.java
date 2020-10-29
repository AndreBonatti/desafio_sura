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

import br.com.springbootapi.dto.ProdutoDto;
import br.com.springbootapi.entity.Produto;
import br.com.springbootapi.repository.ProdutoRepository;
import br.com.springbootapi.utils.DtoUtils;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping(value = "/")
	public List<Produto> Get() {
		return produtoRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable(value = "id") long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent())
			return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Produto> Post(@RequestBody ProdutoDto produtoDto) {
		Produto produto = DtoUtils.produtoNew(produtoDto);
		log.info(produto.toString());
		return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> Put(@PathVariable(value = "id") long id, @RequestBody ProdutoDto update) {
		Optional<Produto> oldObject = produtoRepository.findById(id);
		if (oldObject.isPresent()) {
			Produto produto = oldObject.get();
			produtoRepository.save(DtoUtils.produtoUpdate(produto, update));
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Produto> categoria = produtoRepository.findById(id);
		if (categoria.isPresent()) {
			produtoRepository.delete(categoria.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}