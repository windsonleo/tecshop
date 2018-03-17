package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.tecshop.entidade.Fornecedor;

import java.util.UUID;

@Repository
public interface IFornecedorDAO extends JpaRepository<Fornecedor, UUID> {
}
