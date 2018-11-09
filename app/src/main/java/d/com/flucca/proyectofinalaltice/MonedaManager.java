package d.com.flucca.proyectofinalaltice;

import java.util.ArrayList;
import java.util.List;

public class MonedaManager {

    private ArrayList<Moneda> monedas;
    Moneda Predeterminada;
    int Size;
    private static MonedaManager instance;

    public Moneda getPredeterminada() {
        return Predeterminada;
    }


    private MonedaManager(){
        this.monedas = new ArrayList<Moneda>(){
        };
        Size = 0;
        CrearMonedaPredeterminada("Peso Dominicano" , "DOP");
        CrearMoneda("Dolar",50.02,"USD");
        CrearMoneda("Euro",56.84,"EUR");
        CrearMoneda("Bitcon",322422.417,"BTC");
        CrearMoneda("Yen",0.44,"JPY");
        CrearMoneda("Peso Mexicano",2.48,"MXN");
    }
    public static MonedaManager getInstance(){
        if(instance == null){
            instance = new MonedaManager();
        }
        return instance;
    }
    public int getSize(){
        return  this.Size;
    }

    public ArrayList<Moneda> getMonedas() {
        return monedas;
    }

    public int CrearMoneda(String Nombre, double tasa, String Simbolo){
        Moneda m = new Moneda(Nombre, tasa,Simbolo,++Size);
        monedas.add(m);
        return m.getId();
    }
    public void CrearMonedaPredeterminada(String Nombre, String Simbolo){
        Moneda m = new Moneda(Nombre,1,Simbolo, ++Size);
        Predeterminada = m;
        m.setPredeterminada();
        monedas.add(m);
    }
    public Boolean ActualizarMoneda(int id, String Nombre, double tasa, String Simbolo){
        Moneda m = findById(id);
        if(m != null){
            m.setNombre(Nombre);
            m.setTasa(tasa);
            m.setSimbolo(Simbolo);
        }
        else{
            return false;
        }

        return true;
    }

    public Moneda monedaConMismoNombre(String nombre) {
         for( Moneda m :monedas){
             if(m.getNombre().toLowerCase().equals(nombre.toLowerCase())){
                 return m;
             }
         }
         return null;
    }
    public Moneda monedaConMismoSimbolo(String Simbolo) {
        for( Moneda m :monedas){
            if(m.getSimbolo().toLowerCase().equals(Simbolo.toLowerCase())){
                return m;
            }
        }
        return null;
    }

    public double GetEquivalentePredeterminada(Moneda m, double monto){
           double returnable = 0.0;
           returnable = ((monto)*(m.getTasa()));
           return returnable;
    }

    public double Convertir(Moneda convertible, Moneda convertir, double monto){

        double valorPredeterminada = GetEquivalentePredeterminada(convertible, monto);
        return (valorPredeterminada/convertir.getTasa());
    }
    public Moneda findById(int id){
        Moneda m = null;
        for ( Moneda moneda : monedas  ){
            if(moneda.getId() == id){
                return moneda;
            }
        }
        return  m;
    }

    public void setPredeterminada(Moneda m) {
        getPredeterminada().unsetPredetrminada();
        m.setPredeterminada();
        Predeterminada = m;
    }
}
