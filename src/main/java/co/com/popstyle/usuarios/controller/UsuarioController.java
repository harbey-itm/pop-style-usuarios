package co.com.popstyle.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.popstyle.usuarios.dto.request.UsuarioRequestDto;
import co.com.popstyle.usuarios.dto.response.RespuestaDto;
import co.com.popstyle.usuarios.dto.response.RespuestaObjetosJson;
import co.com.popstyle.usuarios.dto.response.UsuarioResponseDto;
import co.com.popstyle.usuarios.service.IUsuarioService;

@RestController
public class UsuarioController<T> {

	@Autowired
	public IUsuarioService usuarioService;

	@GetMapping(value = "/getUsuarios")
	public String getUsuarios() {
		System.out.println("Ok Usuarios");
//		public List<UsuarioResponseDto> getUsuarios() {
		return "Ok Usuarios";
		// return usuarioServicio.getUsuarios();
	}

	@GetMapping(value = "/getUsuarioEmail/{email}/")
	public UsuarioResponseDto getUsuarioEmail(@PathVariable String email) {

		return usuarioService.getUsuarioEmail(email);

	}

	@GetMapping(value = "/recuperarPassword/{email}/")
	public RespuestaObjetosJson<T>  recuperarPassword(@PathVariable String email) {
		
		usuarioService.getUsuarioEmailDto(email);
		
		if (usuarioService.getUsuarioEmailDto(email) != null)
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true,
					"Se reestabeció la contraseña del usuario con el correo: " +email+ "correctamente", ""), null));

		return (new RespuestaObjetosJson<T>(new RespuestaDto(false, "No se pudo reestablecer la contraseña del usuario con el correo: "+email, ""), null));
		
		
	}

	@PostMapping(value = "/registrarUsuario")
	public RespuestaObjetosJson<T> registrarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto) {

		UsuarioResponseDto buscarUsuario = usuarioService.getUsuarioEmail(usuarioDto.getEmail());

		if (buscarUsuario != null) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
					"No se puede completar el registro !!! El usuario con el correo electronico "
							+ usuarioDto.getEmail() + " ya existen en la BBDD",
					""), null));

		}

		usuarioDto = usuarioService.guardarUsuario(usuarioDto);
		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(true, "El usuario se registró correctamente en la base de datos.", ""), null));

	}

	@PutMapping(value = "/actualizarUsuario")
	public RespuestaObjetosJson<T> actualizarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto) {

		if (usuarioService.existeUsuario(usuarioDto.getIdUsuario())) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
					"El usuario " + usuarioDto.getIdUsuario() + " no existe en la base de datos.", ""), null));
		}

		if (usuarioService.actualizarUsuario(usuarioDto) != null)
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true,
					"El usuario " + usuarioDto.getIdUsuario() + " se actualizó correctamente", ""), null));

		return (new RespuestaObjetosJson<T>(new RespuestaDto(false, "El usuario: " + usuarioDto.getIdUsuario()
				+ " con email " + usuarioDto.getEmail() + " no se pudo actualizar porque el correo: "+usuarioDto.getEmail()+" ya esta asignado a otro usuario !!!", ""), null));
	}

}
