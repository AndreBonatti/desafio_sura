package br.com.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springbootapi.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { 
	
	Produto findTopByOrderByIdProdutoDesc();
}