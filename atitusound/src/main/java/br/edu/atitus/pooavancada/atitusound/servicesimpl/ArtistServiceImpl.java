package br.edu.atitus.pooavancada.atitusound.servicesimpl;

import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancada.atitusound.entities.ArtistEntity;
import br.edu.atitus.pooavancada.atitusound.repositories.ArtistRepository;
import br.edu.atitus.pooavancada.atitusound.repositories.GenericRepository;
import br.edu.atitus.pooavancada.atitusound.services.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService {

	private final ArtistRepository artistRepository;

	public ArtistServiceImpl(ArtistRepository artistRepository) {
		super();
		this.artistRepository = artistRepository;
	}

	@Override
	public GenericRepository<ArtistEntity> getRepository() {
		return artistRepository;
	}

	

}