package tests;

public class Personas {
    private String nombre;
    private String apellidos;
    public Personas (String nombre, String apellidos){
        nombre = this.nombre;
        apellidos = this.apellidos;
    }
    public void saludar(){
        System.out.println("Hola "+this.nombre+" "+this.apellidos);
    }
    
}
