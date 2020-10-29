package br.com.test.springbootapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import br.com.springbootapi.DemoApplication;
import br.com.springbootapi.dto.CategoriaDto;
import br.com.springbootapi.dto.ClienteDto;
import br.com.springbootapi.dto.PedidoDto;
import br.com.springbootapi.dto.PedidoItemDto;
import br.com.springbootapi.dto.ProdutoDto;
import br.com.springbootapi.repository.CategoriaRepository;
import br.com.springbootapi.repository.ClienteRepository;
import br.com.springbootapi.repository.PedidoRepository;
import br.com.springbootapi.repository.ProdutoRepository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class DemoApplicationTest {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTest.class);

	public static final String BASE_PATH = "http://localhost:8080";
	public static final String USER = "admin";
	public static final String PASS = "123";

	public static final HttpHeaders headers = new HttpHeaders();

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private TestRestTemplate template;

	@Test
	@Order(1)
	public void categoria_create_201() throws Exception {

		final String baseUrl = BASE_PATH + "/categoria/";

		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setCategoria("MATERIAS DE LIMPEZA");

		HttpEntity<CategoriaDto> request = new HttpEntity<>(categoriaDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.POST, request,
				String.class);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void categoria_getall_200() throws Exception {
		final String baseUrl = BASE_PATH + "/categoria/";

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("categoria_getall_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void categoria_getbyid_200() throws Exception {

		Long id = categoriaRepository.findTopByOrderByIdCategoriaDesc().getIdCategoria();

		final String baseUrl = BASE_PATH + "/categoria/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("categoria_getbyid_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void categoria_getbyid_404() throws Exception {
		final String baseUrl = BASE_PATH + "/categoria/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("categoria_getbyid_404 : " + result.getBody());
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void categoria_put_200() throws Exception {

		Long id = categoriaRepository.findTopByOrderByIdCategoriaDesc().getIdCategoria();

		final String baseUrl = BASE_PATH + "/categoria/" + id;

		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setCategoria("MATERIAS");

		HttpEntity<CategoriaDto> request = new HttpEntity<>(categoriaDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void categoria_put_404() throws Exception {

		final String baseUrl = BASE_PATH + "/categoria/" + Integer.MAX_VALUE;

		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setCategoria("MATERIAS");

		HttpEntity<CategoriaDto> request = new HttpEntity<>(categoriaDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void categoria_delete_200() throws Exception {

		Long id = categoriaRepository.findTopByOrderByIdCategoriaDesc().getIdCategoria();

		final String baseUrl = BASE_PATH + "/categoria/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void categoria_delete_404() throws Exception {

		final String baseUrl = BASE_PATH + "/categoria/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	// =================== Cliente =========================== \\

	@Test
	@Order(2)
	public void cliente_create_201() throws Exception {

		final String baseUrl = BASE_PATH + "/cliente/";

		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setSenha("654");
		clienteDto.setNome("TESTE" + Math.random());
		clienteDto.setEmail("teste@gmail.com");

		log.info(clienteDto.toString());

		HttpEntity<ClienteDto> request = new HttpEntity<>(clienteDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.POST, request,
				String.class);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void cliente_getall_200() throws Exception {
		final String baseUrl = BASE_PATH + "/cliente/";

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("getCategoria_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void cliente_getbyid_200() throws Exception {
		Long id = clienteRepository.findTopByOrderByIdClienteDesc().getIdCliente();

		final String baseUrl = BASE_PATH + "/cliente/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("cliente_getbyid_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void cliente_getbyid_404() throws Exception {
		final String baseUrl = BASE_PATH + "/cliente/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("cliente_getbyid_404 : " + result.getBody());
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void cliente_put_200() throws Exception {

		Long id = clienteRepository.findTopByOrderByIdClienteDesc().getIdCliente();

		final String baseUrl = BASE_PATH + "/cliente/" + id;

		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setSenha("987");
		clienteDto.setNome("TESTE" + Math.random());
		clienteDto.setEmail("teste@gmail.com");

		HttpEntity<ClienteDto> request = new HttpEntity<>(clienteDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void cliente_put_404() throws Exception {

		final String baseUrl = BASE_PATH + "/cliente/" + Integer.MAX_VALUE;

		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setSenha("987");
		clienteDto.setNome("TESTE" + Math.random());
		clienteDto.setEmail("teste@gmail.com");

		HttpEntity<ClienteDto> request = new HttpEntity<>(clienteDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void cliente_delete_200() throws Exception {

		Long id = clienteRepository.findTopByOrderByIdClienteDesc().getIdCliente();

		final String baseUrl = BASE_PATH + "/cliente/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void cliente_delete_404() throws Exception {

		final String baseUrl = BASE_PATH + "/cliente/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	// =================== Produto =========================== \\

	@Test
	@Order(3)
	public void produto_create_201() throws Exception {

		final String baseUrl = BASE_PATH + "/produto/";

		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setDescricao("");
		produtoDto.setIdCategoria(1);
		produtoDto.setPreco(2.0);
		produtoDto.setProduto("SABONETE");
		produtoDto.setQuantidade(10);

		log.info(produtoDto.toString());

		HttpEntity<ProdutoDto> request = new HttpEntity<>(produtoDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.POST, request,
				String.class);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void produto_getall_200() throws Exception {
		final String baseUrl = BASE_PATH + "/produto/";

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("getCategoria_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void produto_getbyid_200() throws Exception {

		Long id = produtoRepository.findTopByOrderByIdProdutoDesc().getIdProduto();

		final String baseUrl = BASE_PATH + "/produto/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("cliente_getbyid_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void produto_getbyid_404() throws Exception {
		final String baseUrl = BASE_PATH + "/produto/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("cliente_getbyid_404 : " + result.getBody());
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void produto_put_200() throws Exception {

		Long id = produtoRepository.findTopByOrderByIdProdutoDesc().getIdProduto();

		final String baseUrl = BASE_PATH + "/produto/" + id;

		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setDescricao("");
		produtoDto.setIdCategoria(1);
		produtoDto.setPreco(2.0);
		produtoDto.setProduto("SABONETE 222");
		produtoDto.setQuantidade(10);

		log.info(produtoDto.toString());

		HttpEntity<ProdutoDto> request = new HttpEntity<>(produtoDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void produto_put_404() throws Exception {

		final String baseUrl = BASE_PATH + "/produto/" + Integer.MAX_VALUE;

		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setDescricao("");
		produtoDto.setIdCategoria(1);
		produtoDto.setPreco(2.0);
		produtoDto.setProduto("SABONETE");
		produtoDto.setQuantidade(10);

		log.info(produtoDto.toString());

		HttpEntity<ProdutoDto> request = new HttpEntity<>(produtoDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.PUT, request,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void produto_delete_200() throws Exception {

		Long id = produtoRepository.findTopByOrderByIdProdutoDesc().getIdProduto();

		final String baseUrl = BASE_PATH + "/produto/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void produto_delete_404() throws Exception {

		final String baseUrl = BASE_PATH + "/produto/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	// =================== Pedido =========================== \\

	@Test
	@Order(4)
	public void pedido_create_201() throws Exception {

		final String baseUrl = BASE_PATH + "/pedido/";

		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setIdCliente(2);
		List<PedidoItemDto> items = new ArrayList<PedidoItemDto>();

		PedidoItemDto pedidoItem = new PedidoItemDto();
		pedidoItem.setProdutoName("TESTE");
		pedidoItem.setQuantidade(5);
		pedidoItem.setValor(1.0f);
		pedidoItem.setSubtotal(5.0f);
		pedidoItem.setIdProduto(1);
		items.add(pedidoItem);
		pedidoDto.setItems(items);

		log.info(pedidoDto.toString());

		HttpEntity<PedidoDto> request = new HttpEntity<>(pedidoDto, headers);
		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.POST, request,
				String.class);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void pedido_getall_200() throws Exception {
		final String baseUrl = BASE_PATH + "/pedido/";

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("pedido_getall_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void pedido_getbyid_200() throws Exception {

		Long id = pedidoRepository.findTopByOrderByIdPedidoDesc().getIdPedido();
		final String baseUrl = BASE_PATH + "/pedido/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("pedido_getbyid_200 : " + result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void pedido_getbyid_404() throws Exception {
		final String baseUrl = BASE_PATH + "/pedido/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.GET, null,
				String.class);
		log.info("pedido_getbyid_404 : " + result.getBody());
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void pedido_delete_200() throws Exception {

		Long id = pedidoRepository.findTopByOrderByIdPedidoDesc().getIdPedido();

		final String baseUrl = BASE_PATH + "/pedido/" + id;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void pedido_delete_404() throws Exception {

		final String baseUrl = BASE_PATH + "/pedido/" + Integer.MAX_VALUE;

		ResponseEntity<String> result = template.withBasicAuth(USER, PASS).exchange(baseUrl, HttpMethod.DELETE, null,
				String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

}
