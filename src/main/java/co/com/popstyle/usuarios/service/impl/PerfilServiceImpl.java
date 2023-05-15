package co.com.popstyle.usuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.popstyle.usuarios.dto.response.PerfilResponseDto;
import co.com.popstyle.usuarios.entity.PerfilEntity;
import co.com.popstyle.usuarios.repository.IPerfilRepository;
import co.com.popstyle.usuarios.service.IPerfilService;

@Service
public class PerfilServiceImpl implements IPerfilService {

	@Autowired
	private IPerfilRepository perfilRepositorio;

	@Override
	public List<PerfilResponseDto> obtenerRoles() {

		List<PerfilEntity> perfiles = perfilRepositorio.findAll();

		if (perfiles != null) {
			
			List<PerfilResponseDto> misPerfilesDto = new ArrayList<PerfilResponseDto>();

			for (PerfilEntity per : perfiles) {
				PerfilResponseDto perfilDto = new PerfilResponseDto();
				perfilDto.setIdPerfil(per.getIdPerfil());
				perfilDto.setDescripcion(per.getDescripcion());
				perfilDto.setEstado(per.getEstado());
				perfilDto.setNombrePerfil(per.getNombrePerfil());
				//perfilDto.setUsuarios(null);

				misPerfilesDto.add(perfilDto);
			}
			return misPerfilesDto;
		}

		return null;
	}

}
