package com.tecsoluction.tecshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.tecshop.entidade.Usuario;

import java.util.UUID;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, UUID> {


//	@Query("SELECT p FROM Usuario p where p.username=")
    Usuario findByUsername(String username);

}
