package Boxter.AppExamenesAPI.exceptions;

public class UsuarioFoundException extends Exception {

    public UsuarioFoundException(){
        super("El usuario con ese Username ya existe!!");
    }

    public UsuarioFoundException(String msg){
        super(msg);
    }

}
