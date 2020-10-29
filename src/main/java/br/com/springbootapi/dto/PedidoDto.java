package br.com.springbootapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCliente;
	private List<PedidoItemDto> items = new ArrayList<>();

	public PedidoDto() {
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public List<PedidoItemDto> getItems() {
		return items;
	}

	public void setItems(List<PedidoItemDto> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return String.format("PedidoDto [idCliente=%s, items=%s]", idCliente,items);
	}

}
