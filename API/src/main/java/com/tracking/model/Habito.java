package com.tracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "habito")
public class Habito implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String descripcion;
    private int idUsuario; 
    private String fechaInicio;
    private int plazo;
    private int diasDescanso;
}
