package d.com.flucca.proyectofinalaltice;

public class Moneda {

    private boolean Predeterminada;
    private int Id;
    private double Tasa;
    private String Nombre;
    private String Simbolo;

    public boolean isPredeterminada() {
        return Predeterminada;
    }
    public void setPredeterminada(){
        this.Predeterminada = true;
    }
    public void unsetPredetrminada(){
        this.Predeterminada = false;
    }
    public int getId(){
        return this.Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getTasa() {
        return Tasa;
    }

    public void setTasa(double tasa) {
        Tasa = tasa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSimbolo() {
        return Simbolo;
    }

    public void setSimbolo(String simbolo) {
        Simbolo = simbolo;
    }

    public Moneda(String Nombre, double Tasa,String Simbolo, int Id){

        this.Nombre = Nombre;
        this.Tasa = Tasa;
        this.Simbolo = Simbolo;
        this.Predeterminada = false;
        this.Id = Id;
    }

}
