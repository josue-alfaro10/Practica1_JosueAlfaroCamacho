/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica1_JosueAlfaroCamacho.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="arbol")
public class Arbol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_arbol")
    private Integer idArbol;

    @Column(name="nombre_comun", nullable=false, length=100)
    private String nombreComun;

    @Column(name="tipo_flor", length=100)
    private String tipoFlor;

    @Column(name="dureza_madera")
    private String durezaMadera;

    @Column(name="altura_promedio")
    private Double alturaPromedio;

    @Column(name="ruta_imagen", length=1024)
    private String rutaImagen;


}
