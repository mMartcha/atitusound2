package br.edu.atitus.pooavancada.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.pooavancada.atitusound.entities.GenericEntity;
import br.edu.atitus.pooavancada.atitusound.repositories.GenericRepository;

public interface GenericService <TEntidade extends GenericEntity>{
	
	GenericRepository<TEntidade> getRepository();
	
	default void validate(TEntidade entidade) throws Exception {
		if (entidade.getName() == null || entidade.getName().isEmpty())
			throw new Exception("É necessário informar um nome válido!");
		if (entidade.getUuid() == null) {
			if (getRepository().existsByName(entidade.getName()))
				throw new Exception("Já existe um registro com este nome!");
		} else {
			if (!getRepository().existsById(entidade.getUuid()))
				throw new Exception("Não existe registro com este UUID");
			if (getRepository().existsByNameAndUuidNot(entidade.getName(), entidade.getUuid()))
				throw new Exception("Já existe um registro com este nome!");
		}
	}

	
	default TEntidade save(TEntidade entidade) throws Exception {
		validate(entidade);
		return getRepository().save(entidade);
	}

	
	default List<TEntidade> findAll() throws Exception {
		return getRepository().findAll();
	}

	
	default Optional<TEntidade> findById(UUID uuid) throws Exception {
		return getRepository().findById(uuid);
	}

	
	default void deleteById(UUID uuid) throws Exception {
		if (!getRepository().existsById(uuid))
			throw new Exception("Não existe registro com este UUID");
		getRepository().deleteById(uuid);
	}

	
	default Page<List<TEntidade>> findByNameContainingIgnoreCase(Pageable pageable, String name) throws Exception {
		return getRepository().findByNameContainingIgnoreCase(pageable, name);
	}
	
}
