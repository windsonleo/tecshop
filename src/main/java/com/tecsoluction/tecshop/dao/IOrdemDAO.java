package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.tecshop.entidade.Ordem;

import java.util.UUID;

@Repository
public interface IOrdemDAO extends JpaRepository<Ordem, UUID> {
}
