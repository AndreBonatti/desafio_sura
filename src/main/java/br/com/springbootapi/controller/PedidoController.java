package br.com.springbootapi.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import br.com.springbootapi.dto.PedidoDto;
import br.com.springbootapi.entity.Pedido;
import br.com.springbootapi.repository.PedidoRepository;
import br.com.springbootapi.utils.DtoUtils;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping(value = "/")
	public List<Pedido> Get() {
		return pedidoRepository.findAllStatus("PEDIDO");
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> GetById(@PathVariable(value = "id") long id) {
		Optional<Pedido> produto = pedidoRepository.findById(id);
		if (produto.isPresent())
			return new ResponseEntity<Pedido>(produto.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Long> Post(@RequestBody PedidoDto pedidoDto) {
		Pedido novo = DtoUtils.pedidoNew(pedidoDto);
		novo.setStatus("PENDENTE");
		novo.setData(new Date());
		novo.setSessao(RequestContextHolder.currentRequestAttributes().getSessionId());
		pedidoRepository.save(novo);

		log.info(novo.toString());
		
		return new ResponseEntity<>(novo.getIdPedido(), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			pedido.get().setStatus("CANCELADO");
			pedidoRepository.save(pedido.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}