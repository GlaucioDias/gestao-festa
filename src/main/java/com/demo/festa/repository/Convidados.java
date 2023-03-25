package com.demo.festa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.festa.model.Convidado;

public interface Convidados extends JpaRepository<Convidado, Long>{

}
