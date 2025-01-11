package com.tracking.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tracking.model.Habito;
@Repository
public interface IHabito extends JpaRepository<Habito, Long>{}