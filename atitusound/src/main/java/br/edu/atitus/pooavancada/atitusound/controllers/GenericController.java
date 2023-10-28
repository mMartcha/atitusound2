package br.edu.atitus.pooavancada.atitusound.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.atitus.pooavancada.atitusound.entities.GenericEntity;
import br.edu.atitus.pooavancada.atitusound.services.GenericService;

public abstract class GenericController <TEntidade extends GenericEntity , TDTO>{

	abstract GenericService<TEntidade> getService();
	
	abstract TEntidade convertDTO2Entity(TDTO dto);
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable UUID uuid){
		try {
			getService().deleteById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<TEntidade> put(@PathVariable UUID uuid, @RequestBody TDTO dto){
		TEntidade entidade = convertDTO2Entity(dto);
		entidade.setUuid(uuid);
		try {
			getService().save(entidade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.ok(entidade);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<TEntidade> getByUuid(@PathVariable UUID uuid){
		Optional<TEntidade> entidade;
		try {
			entidade = getService().findById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		if (entidade.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(entidade.get());
	}

	@GetMapping
	public ResponseEntity<Page<List<TEntidade>>> getAll(@PageableDefault(page = 0, size = 10, sort = "name", direction = Direction.ASC) Pageable pageable, 
			                @RequestParam String name){
		Page<List<TEntidade>> entidades;
		try {
			entidades = getService().findByNameContainingIgnoreCase(pageable,name);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.ok(entidades);
	}
	
	@PostMapping
	public ResponseEntity<TEntidade> save(@RequestBody TDTO dto){
		TEntidade entidade = convertDTO2Entity(dto);
		try {
			getService().save(entidade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
	}
		
}
