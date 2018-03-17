package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecsoluction.tecshop.entidade.Endereco;

import java.util.UUID;


public interface IEnderecoDAO  extends JpaRepository<Endereco, UUID> {
}
