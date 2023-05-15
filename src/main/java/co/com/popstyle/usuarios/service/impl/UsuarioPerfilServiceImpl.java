package co.com.popstyle.usuarios.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.popstyle.usuarios.dto.request.UsuarioPerfilRequestDto;
import co.com.popstyle.usuarios.dto.response.PerfilResponseDto;
import co.com.popstyle.usuarios.dto.response.UsuarioPerfilesResponseDto;
import co.com.popstyle.usuarios.dto.response.UsuarioResponseDto;
import co.com.popstyle.usuarios.entity.PerfilEntity;
import co.com.popstyle.usuarios.entity.UsuarioEntity;
import co.com.popstyle.usuarios.entity.UsuarioPerfilEntity;
import co.com.popstyle.usuarios.entity.UsuarioPerfilId;
import co.com.popstyle.usuarios.repository.IPerfilRepository;
import co.com.popstyle.usuarios.repository.IUsuarioPerfilRepository;
import co.com.popstyle.usuarios.repository.IUsuarioRepository;
import co.com.popstyle.usuarios.service.IUsuarioPerfilService;

@Service
public class UsuarioPerfilServiceImpl implements IUsuarioPerfilService {

	@Autowired
	private IUsuarioRepository usuarioRepositorio;

	@Autowired
	private IPerfilRepository perfilRepositorio;
	
	@Autowired
	private IUsuarioPerfilRepository usuarioPerfilRepositorio;

	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public boolean guardarPerfilUsuario(UsuarioPerfilRequestDto usuarioDto) {

		UsuarioEntity usuario = usuarioRepositorio.findByIdUsuario(usuarioDto.getIdUsuario());
		PerfilEntity perfil = perfilRepositorio.findById(usuarioDto.getIdPerfil()).orElse(null);
		
		if(usuarioDto.getEstado() == null || usuarioDto.getEstado().isEmpty())
			usuarioDto.setEstado("A");
		
		if(usuario != null && perfil != null) {
			
			UsuarioPerfilEntity usuPerfilEntity = new UsuarioPerfilEntity();
			usuPerfilEntity.setUsuarioId(usuarioDto.getIdUsuario());
			usuPerfilEntity.setPerfilId(usuarioDto.getIdPerfil());
			usuPerfilEntity.setEstado(usuarioDto.getEstado());
			
			usuarioPerfilRepositorio.save(usuPerfilEntity);
			return true;
		}		
		return false;
	}

	@Override
	public boolean actualizarPerfilUsuario(UsuarioPerfilRequestDto usuarioDto) {
		
		UsuarioEntity usuario = usuarioRepositorio.findByIdUsuario(usuarioDto.getIdUsuario());
		PerfilEntity perfil = perfilRepositorio.findById(usuarioDto.getIdPerfil()).orElse(null);
		
		if(usuarioDto.getEstado() == null || usuarioDto.getEstado().isEmpty())
			usuarioDto.setEstado("A");
		
		if(usuario != null && perfil != null) {
			
			UsuarioPerfilId usuarioPerfilId = new UsuarioPerfilId(usuario.getIdUsuario(), perfil.getIdPerfil());
			
			UsuarioPerfilEntity usuPerfilEntity = usuarioPerfilRepositorio.findById(usuarioPerfilId).orElse(null);
			
			if(usuPerfilEntity != null) {
				usuPerfilEntity.setEstado(usuarioDto.getEstado());
				usuarioPerfilRepositorio.save(usuPerfilEntity);
				return true;
			}

		}		
		return false;
	}
	
	private UsuarioPerfilesResponseDto mapToUsuarioDto(UsuarioEntity usuarioEntity) {
		return modelMapper.map(usuarioEntity, UsuarioPerfilesResponseDto.class);
	}
	
	private UsuarioResponseDto mapToPerfilUsuariosDto(UsuarioEntity usuarioEntity) {
		return modelMapper.map(usuarioEntity, UsuarioResponseDto.class);
	}

	// Convertir Entity a Dto
	private PerfilResponseDto mapToPerfilDto(PerfilEntity perfilEntity) {
		return modelMapper.map(perfilEntity, PerfilResponseDto.class);
	}
	
}
