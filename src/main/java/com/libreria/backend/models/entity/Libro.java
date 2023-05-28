package com.libreria.backend.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {
	
	//ATRIBUTOS:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 1, max = 80, message = "tiene que tener entre 1 y 80 caracteres")
	@Column(nullable = false)
	private String titulo;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2, max = 30, message = "tiene que tener entre 2 y 30 caracteres")
	private String autor;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 10, max = 14, message = "tiene que tener entre 10 y 14 caracteres")
	private String isbn;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2, max = 2, message = "tiene que tener 2 caracteres (si/no)")
	private String disponibilidad;
	
	private String foto;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2, max = 30, message = "tiene que tener entre 2 y 30 caracteres")
	private String genero;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2, max = 2000, message = "tiene que tener entre 2 y 2000 caracteres")
	private String sinopsis;

	@Size(max = 10, message = "tiene que tener 10 caracteres como máximo")
	private String ubicacion;
	
	//GETTERS&SETTERS:
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	//MÉTODOS:
	private static final long serialVersionUID = 1L;

}
