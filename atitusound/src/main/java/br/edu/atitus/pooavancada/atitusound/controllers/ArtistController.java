package br.edu.atitus.pooavancada.atitusound.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancada.atitusound.entities.ArtistEntity;
import br.edu.atitus.pooavancada.atitusound.entities.dtos.ArtistDTO;
import br.edu.atitus.pooavancada.atitusound.services.ArtistService;
import br.edu.atitus.pooavancada.atitusound.services.GenericService;

@RestController
@RequestMapping("/artists")
public class ArtistController extends GenericController<ArtistEntity, ArtistDTO>{
	
	//Spring, Injeção de Dependências
	//Aqui o Spring Framework é responsável pela implementação,
	//Instanciação e injeção dentro do objeto ArtistController
	
	//@Autowired >> Spring recomenda utilizar métodos construtores
	
	private ArtistService artistService;
	
	public ArtistController(ArtistService artistService) {
		super();
		this.artistService = artistService;
	}

	protected ArtistEntity convertDTO2Entity(ArtistDTO dto) {
		ArtistEntity entidade = new ArtistEntity();
		entidade.setName(dto.getName());
		entidade.setImage(dto.getImage());
		return entidade;
	}

	@Override
	GenericService<ArtistEntity> getService() {
		return artistService;
	}

	
}
