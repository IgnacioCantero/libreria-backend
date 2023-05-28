package com.libreria.backend.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.libreria.backend.models.entity.Libro;
import com.libreria.backend.models.services.ILibroService;
import com.libreria.backend.models.services.IUploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class LibroRestController {
	
	@Autowired
	private ILibroService libroService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	//Mostrar libros:
	@GetMapping("/libros")
	@ResponseStatus(HttpStatus.OK)
	public List<Libro> index(){
		return libroService.findAll();
	}
	
	//Mostrar libros paginados [3 registros por página] y ordenados [alfabéticamente por 'título']:
	@GetMapping("/libros/page/{page}")
	@ResponseStatus(HttpStatus.OK)
	public Page<Libro> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3, Sort.by("titulo").ascending());
		return libroService.findAll(pageable);
	}
	
	//Mostrar libro por id:
	@Secured("ROLE_ADMIN")
	@GetMapping("/libros/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Libro libro = null;
		Map<String, Object> response = new HashMap<>();
		try {
			libro = libroService.findById(id);
		} catch (DataAccessException dataAccessException) {
			response.put("mensaje", "¡ERROR al realizar la consulta en la base de datos!");
			response.put("error", dataAccessException.getMessage().concat(": ").concat(dataAccessException.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (libro == null) {
			response.put("mensaje", "¡ERROR: El libro ID: " .concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}
	
	//Crear libro:
	@Secured("ROLE_ADMIN")
	@PostMapping("/libros")
	public ResponseEntity<?> create(@Valid @RequestBody Libro libro, BindingResult result) {
		Libro libroNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->  "ERROR: El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			libroNew = libroService.save(libro);
		} catch (DataAccessException dataAccessException) {
			response.put("mensaje", "¡ERROR al realizar el INSERT en la base de datos!");
			response.put("error", dataAccessException.getMessage().concat(": ").concat(dataAccessException.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡El libro ha sido creado con éxito!");
		response.put("libro", libroNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	//Actualizar libro:
	@Secured("ROLE_ADMIN")
	@PutMapping("/libros/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Libro libro, BindingResult result, @PathVariable Long id) {
		Libro libroActual = libroService.findById(id);
		Libro libroUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {
						return "ERROR: El campo '" + err.getField() + "' " + err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (libroActual == null) {
			response.put("mensaje", "¡ERROR: El libro ID: " .concat(id.toString().concat(" no se puede editar ya que no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			libroActual.setTitulo(libro.getTitulo());
			libroActual.setAutor(libro.getAutor());
			libroActual.setIsbn(libro.getIsbn());
			libroActual.setCreateAt(libro.getCreateAt());
			libroActual.setGenero(libro.getGenero());
			libroActual.setSinopsis(libro.getSinopsis());
			libroActual.setDisponibilidad(libro.getDisponibilidad());
			if(libro.getDisponibilidad().equalsIgnoreCase("No")) {
				libroActual.setUbicacion("");
			} else {
				libroActual.setUbicacion(libro.getUbicacion());
			}
			libroUpdated = libroService.save(libroActual);
		} catch (DataAccessException dataAccessException) {
			response.put("mensaje", "¡ERROR al realizar el UPDATE en la base de datos!");
			response.put("error", dataAccessException.getMessage().concat(": ").concat(dataAccessException.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡El libro ha sido actualizado con éxito!");
		response.put("libro", libroUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar libro:
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		if (libroService.findById(id) == null) {
			response.put("mensaje", "¡ERROR: El libro ID: " .concat(id.toString().concat(" no se puede eliminar ya que no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			Libro libro = libroService.findById(id);
			String nombreFotoAnterior = libro.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			libroService.delete(id);
		} catch (DataAccessException dataAccessException) {
			response.put("mensaje", "¡ERROR al realizar el DELETE en la base de datos!");
			response.put("error", dataAccessException.getMessage().concat(": ").concat(dataAccessException.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡El libro ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//Subir imágen libro:
	@Secured("ROLE_ADMIN")
	@PostMapping("/libros/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Libro libro = libroService.findById(id);
		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "¡ERROR al subir la imágen!");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior = libro.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			libro.setFoto(nombreArchivo);
			libroService.save(libro);
			response.put("libro", libro);
			response.put("mensaje", "Imágen " + nombreArchivo + " subida correctamente");
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Cargar/Descargar imágen libro:
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
