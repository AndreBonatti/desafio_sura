package br.com.springbootapi.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.springbootapi.dto.CategoriaDto;
import br.com.springbootapi.dto.ClienteDto;
import br.com.springbootapi.dto.PedidoDto;
import br.com.springbootapi.dto.PedidoItemDto;
import br.com.springbootapi.dto.ProdutoDto;
import br.com.springbootapi.entity.Categoria;
import br.com.springbootapi.entity.Cliente;
import br.com.springbootapi.entity.Pedido;
import br.com.springbootapi.entity.PedidoItem;
import br.com.springbootapi.entity.Produto;

public class DtoUtils {

	public static Categoria categoriaNew(CategoriaDto categoriaDto) {
		Categoria categoria = new Categoria();
		categoria.setCategoria(categoriaDto.getCategoria());
		return categoria;
	}

	public static Categoria categoriaUpdate(Categoria old, CategoriaDto update) {
		old.setCategoria(update.getCategoria());
		return old;
	}

	public static Cliente clienteNew(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setBairro(clienteDto.getBairro());
		cliente.setCep(clienteDto.getCep());
		cliente.setCidade(clienteDto.getCidade());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEstado(clienteDto.getEstado());
		cliente.setNome(clienteDto.getNome());
		cliente.setRua(clienteDto.getRua());
		return cliente;
	}

	public static Cliente clienteUpdate(Cliente old, ClienteDto clienteDto) {
		old.setBairro(clienteDto.getBairro());
		old.setCep(clienteDto.getCep());
		old.setCidade(clienteDto.getCidade());
		old.setEmail(clienteDto.getEmail());
		old.setEstado(clienteDto.getEstado());
		old.setNome(clienteDto.getNome());
		old.setRua(clienteDto.getRua());
		return old;
	}

	public static Produto produtoNew(ProdutoDto produtoDto) {
		Produto produto = new Produto();
		produto.setIdCategoria(produtoDto.getIdCategoria());
		produto.setDescricao(produtoDto.getDescricao());
		produto.setFoto(produtoDto.getFoto());
		produto.setPreco(produtoDto.getPreco());
		produto.setProduto(produtoDto.getProduto());
		produto.setQuantidade(produtoDto.getQuantidade());
		return produto;
	}

	public static Produto produtoUpdate(Produto old, ProdutoDto produtoDto) {
		old.setIdCategoria(produtoDto.getIdCategoria());
		old.setDescricao(produtoDto.getDescricao());
		old.setFoto(produtoDto.getFoto());
		old.setPreco(produtoDto.getPreco());
		old.setProduto(produtoDto.getProduto());
		old.setQuantidade(produtoDto.getQuantidade());
		return old;
	}

	public static Pedido pedidoNew(PedidoDto pedidoDto) {
		Pedido pedido = new Pedido();
		pedido.setIdCliente(pedidoDto.getIdCliente());
		
		List<PedidoItem> items = new ArrayList<PedidoItem>();
		for (PedidoItemDto item : pedidoDto.getItems()) {
			
			PedidoItem pedidoItem = new PedidoItem();
			pedidoItem.setProdutoName(item.getProdutoName());
			pedidoItem.setQuantidade(item.getQuantidade());
			pedidoItem.setValor(item.getValor());
			pedidoItem.setSubtotal(item.getSubtotal());
			pedidoItem.setPedido(pedido);			
			pedidoItem.setIdProduto(item.getIdProduto());			
			
			items.add(pedidoItem);
		}
		pedido.setItems(items);

		return pedido;
	}

}
