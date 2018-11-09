package d.com.flucca.proyectofinalaltice;

public class Conversion {
   private  Moneda monedaConvertida;
   private  double monto;
   private Moneda monedaOriginal;


    public double getMonto() {
        return monto;
    }

    public Moneda getMonedaConvertida() {
        return monedaConvertida;
    }

    public void setMonedaConvertida(Moneda monedaConvertida) {
        this.monedaConvertida = monedaConvertida;
    }

    public Moneda getMonedaOriginal() {
        return monedaOriginal;
    }

    public void setMonedaOriginal(Moneda monedaOriginal) {
        this.monedaOriginal = monedaOriginal;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Conversion(Moneda monedaConvertida, double monto, Moneda monedaOriginal){
        this.monedaConvertida = monedaConvertida;
        this.monto = monto;
        this.monedaOriginal = monedaOriginal;
    }
}
