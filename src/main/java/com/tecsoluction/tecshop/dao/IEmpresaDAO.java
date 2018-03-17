package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecsoluction.tecshop.entidade.Empresa;

import java.util.UUID;


public interface IEmpresaDAO extends JpaRepository<Empresa, UUID> {
}
