package br.com.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springbootapi.entity.Authoritie;

@Repository
public interface AuthoritieRepository extends JpaRepository<Authoritie, String> { }