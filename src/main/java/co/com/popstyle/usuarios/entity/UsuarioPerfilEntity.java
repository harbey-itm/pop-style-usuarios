package co.com.popstyle.usuarios.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios_perfiles")
@IdClass(value = UsuarioPerfilId.class)
public class UsuarioPerfilEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long usuarioId;
	@Id
	private Long perfilId;
	
	private String estado;
	
}
