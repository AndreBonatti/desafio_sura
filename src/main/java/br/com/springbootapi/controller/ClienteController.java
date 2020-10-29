package br.com.springbootapi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootapi.dto.ClienteDto;
import br.com.springbootapi.entity.Authoritie;
import br.com.springbootapi.entity.Cliente;
import br.com.springbootapi.repository.AuthoritieRepository;
import br.com.springbootapi.repository.ClienteRepository;
import br.com.springbootapi.utils.DtoUtils;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthoritieRepository authoritieRepository;

	@GetMapping(value = "/")
	public List<Cliente> Get() {
		return clienteRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") long id) {
		Optional<Cliente> pessoa = clienteRepository.findById(id);
		if (pessoa.isPresent())
			return new ResponseEntity<Cliente>(pessoa.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Cliente> Post(@RequestBody ClienteDto novo) {

		Cliente cliente = DtoUtils.clienteNew(novo);
		cliente.setSenha(passwordEncoder.encode(novo.getSenha()));

		// Credenciais de Cliente
		Authoritie authoritie = new Authoritie();
		authoritie.setAuthority("CLIENTE");
		authoritie.setUsername(cliente.getNome());
		authoritieRepository.save(authoritie);
		
		log.info(cliente.toString());

		return new ResponseEntity<>(clienteRepository.save(cliente), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> Put(@PathVariable(value = "id") long id, @RequestBody ClienteDto update) {
		Optional<Cliente> old = clienteRepository.findById(id);
		if (old.isPresent()) {
			Cliente cliente = old.get();
			cliente = DtoUtils.clienteUpdate(cliente, update);
			cliente.setSenha(passwordEncoder.encode(update.getSenha()));
			clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Cliente> Cliente = clienteRepository.findById(id);
		
		if (Cliente.isPresent()) {
			Optional<Authoritie> auth = authoritieRepository.findById(Cliente.get().getNome());
			clienteRepository.delete(Cliente.get());						
			authoritieRepository.delete(auth.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}