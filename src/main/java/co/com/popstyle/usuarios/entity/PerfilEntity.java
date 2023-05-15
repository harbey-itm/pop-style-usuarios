package co.com.popstyle.usuarios.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="perfiles")
public class PerfilEntity implements Serializable{

	private static final long serialVersionUID = -3533623870322078821L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfil;
	
	private String nombrePerfil;
	
	private String descripcion;
	
	private String estado;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "perfiles", fetch = FetchType.LAZY)
	private Set<UsuarioEntity> usuarios = new HashSet<>();
	
}
