package br.com.springbootapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private long idPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", insertable = false, updatable = false)
	private Cliente cliente;
	
	@Column(name = "id_cliente", nullable = false)
	private long idCliente;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PedidoItem> items = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private String sessao;

	public Pedido() {
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getData() {
		return data;
	}
	

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	public void setItems(List<PedidoItem> items) {
		this.items = items;
	}

	public List<PedidoItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return String.format("Pedido [idPedido=%s, cliente=%s, idCliente=%s, data=%s, status=%s, sessao=%s]", idPedido,
				cliente, idCliente, data, status, sessao);
	}

}
