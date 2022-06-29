package com.ceiba.biblioteca.modelo;

public enum TipoUsuario {
	AFILIADO("1"),
	EMPLEADO("2"),
	INVITADO("3"),
	;
	

    public final String label;

    private TipoUsuario(String label) {
        this.label = label;
    }
}
