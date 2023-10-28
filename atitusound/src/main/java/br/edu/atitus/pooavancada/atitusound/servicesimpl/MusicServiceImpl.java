package br.edu.atitus.pooavancada.atitusound.servicesimpl;

import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancada.atitusound.entities.MusicEntity;
import br.edu.atitus.pooavancada.atitusound.repositories.GenericRepository;
import br.edu.atitus.pooavancada.atitusound.repositories.MusicRepository;
import br.edu.atitus.pooavancada.atitusound.services.MusicService;

@Service
public class MusicServiceImpl implements MusicService{

	private final MusicRepository repository;
	
	public MusicServiceImpl(MusicRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public GenericRepository<MusicEntity> getRepository() {
		return repository;
	}

}
