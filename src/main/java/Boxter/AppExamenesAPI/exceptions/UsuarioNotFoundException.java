package Boxter.AppExamenesAPI.exceptions;

public class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException(){
        super("El usuario con ese Username ya existe!!");
    }

    public UsuarioNotFoundException(String msg){
        super(msg);
    }

}
