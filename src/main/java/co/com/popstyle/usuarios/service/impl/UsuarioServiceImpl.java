package co.com.popstyle.usuarios.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.popstyle.usuarios.dto.request.UsuarioRequestDto;
import co.com.popstyle.usuarios.dto.response.PerfilResponseDto;
import co.com.popstyle.usuarios.dto.response.UsuarioResponseDto;
import co.com.popstyle.usuarios.entity.PerfilEntity;
import co.com.popstyle.usuarios.entity.UsuarioEntity;
import co.com.popstyle.usuarios.repository.IUsuarioRepository;
import co.com.popstyle.usuarios.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepositorio;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean existeUsuario(Long idUsuario) {

		UsuarioEntity usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
		if (usuario == null)
			return true;

		return false;

	}

	@Override
	public UsuarioResponseDto getUsuarioEmail(String email) {
		UsuarioEntity usuario = usuarioRepositorio.findByEmail(email);

		if (usuario != null) {
			
			UsuarioResponseDto usuRes = new UsuarioResponseDto();
			
			usuRes.setNombresApellidos(usuario.getNombresApellidos());
			usuRes.setCelular(usuario.getCelular());
			usuRes.setEmail(usuario.getEmail());
			usuRes.setEstado(usuario.getEstado());
			usuRes.setIdUsuario(usuario.getIdUsuario());
			
			Set<PerfilEntity> perfiles = usuario.getPerfiles();
			
			if(perfiles != null) {
				usuRes.setPerfiles(
						perfiles
						.stream()
						.map(a -> mapToPerDto(a))
						.collect(Collectors.toSet())
						);
			}else {
				usuRes.setPerfiles(null);
			}
			
			return usuRes;
		}
		return null;
	}

	@Override
	public UsuarioResponseDto getRecuperarPassword(String email) {

		String password = "";
		UsuarioEntity usuario = usuarioRepositorio.findByEmail(email);

		if (usuario != null) {

			password = generarPassword();
			System.out.println("password Generada: " + password);

			usuario.setPassword(password);

			if (usuarioRepositorio.save(usuario) != null)
				System.out.println("Se actualizó el password del usuario");

			// enviar correo al usuario
			System.out.println("enviar correo al usuario");

			return mapToUsuDto(usuario);

		}

		return null;
	}

	@Override
	public UsuarioRequestDto guardarUsuario(UsuarioRequestDto usuarioDto) {

		// Falta la validación si existe el correo

		UsuarioEntity existeEmail = usuarioRepositorio.findByEmail(usuarioDto.getEmail());

		UsuarioEntity usuEntity = new UsuarioEntity();
		if (existeEmail == null) {
			usuEntity.setFechaCreacion(LocalDateTime.now());
			usuEntity.setNombresApellidos(usuarioDto.getNombresApellidos().toUpperCase());
			usuEntity.setEstado((usuarioDto.getEstado().toUpperCase()).trim());
			usuEntity.setEmail((usuarioDto.getEmail().toLowerCase()).trim());
			usuEntity.setCelular((usuarioDto.getCelular()).trim());
			usuEntity.setPassword((usuarioDto.getPassword()).trim());

			if (usuarioRepositorio.save(usuEntity) == null)
				return null;

		} else {
			return null;
		}

		return usuarioDto;
	}

	@Override
	public UsuarioRequestDto actualizarUsuario(UsuarioRequestDto usuarioDto) {

		boolean actualizar = false;
		UsuarioEntity usuarioEntity = usuarioRepositorio.findById(usuarioDto.getIdUsuario()).orElse(null);

		if (usuarioEntity != null) {

			UsuarioEntity emailEntity = usuarioRepositorio.findByEmail(usuarioDto.getEmail());

			if (emailEntity != null) {

				if (emailEntity.getIdUsuario() == (usuarioEntity.getIdUsuario()))
					actualizar = true;

			} else
				actualizar = true;

		}

		if (actualizar) {
			usuarioEntity.setNombresApellidos(usuarioDto.getNombresApellidos().toUpperCase());
			usuarioEntity.setEstado(usuarioDto.getEstado().toUpperCase());
			usuarioEntity.setEmail(usuarioDto.getEmail().toLowerCase());
			usuarioEntity.setCelular(usuarioDto.getCelular());
			usuarioEntity.setPassword(usuarioDto.getPassword());

			if (usuarioRepositorio.save(usuarioEntity) != null)
				return usuarioDto;

		}
		return null;
	}

	private String generarPassword() {

		int longitudPassword = 10;

		String caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&/()=?¿*.,-+";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(longitudPassword);

		for (int i = 0; i < longitudPassword; i++) {
			int index = random.nextInt(caracteresPermitidos.length());
			sb.append(caracteresPermitidos.charAt(index));
		}

		return sb.toString();

	}

	private UsuarioResponseDto mapToUsuDto(UsuarioEntity usuarioEntity) {
		return modelMapper.map(usuarioEntity, UsuarioResponseDto.class);
	}
	
	private PerfilResponseDto mapToPerDto(PerfilEntity perfilEntity) {
		return modelMapper.map(perfilEntity, PerfilResponseDto.class);
	}

}
