package com.tracking.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tracking.model.Usuario;
@Repository
public interface IUsuario extends JpaRepository<Usuario, Long>{}