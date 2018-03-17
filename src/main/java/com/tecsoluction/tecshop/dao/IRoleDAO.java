package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.tecshop.entidade.Role;

import java.util.UUID;

@Repository
public interface IRoleDAO extends JpaRepository<Role, UUID> {
}
