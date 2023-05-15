package co.com.popstyle.usuarios.manejo.excepciones;

public class NoFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPCION = "Recurso No Encontrado";
	
	public NoFoundException(String detalle) {
		super(DESCRIPCION +" : "+detalle);
	}

}
