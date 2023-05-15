package co.com.popstyle.usuarios.manejo.excepciones;

public class InternalFatalError extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPCION = "Error Interno del Servidor";
	
	public InternalFatalError(String detalle) {
		super(DESCRIPCION +" : "+detalle);
	}

}
