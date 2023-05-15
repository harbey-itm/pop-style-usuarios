package co.com.popstyle.usuarios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioPerfilId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="usuario_id")
	private Long usuarioId;
	@Column(name="perfil_id")
	private Long perfilId;

}
