package com.tracking.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tracking.model.Cumplimiento;
@Repository
public interface ICumplimiento extends JpaRepository<Cumplimiento, Long>{}