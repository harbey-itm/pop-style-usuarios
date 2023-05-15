package co.com.popstyle.usuarios.manejo.excepciones;

public class BadRequestException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPCION = "Bad Request Exception (400)";
	
	public BadRequestException(String detalle) {
		super(DESCRIPCION +" : "+detalle);
	}

}
