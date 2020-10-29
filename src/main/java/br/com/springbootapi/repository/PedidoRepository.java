package br.com.springbootapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springbootapi.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "FROM Pedido WHERE status = ?1")
	List<Pedido> findAllStatus(String status);
	
	Pedido findTopByOrderByIdPedidoDesc();

}