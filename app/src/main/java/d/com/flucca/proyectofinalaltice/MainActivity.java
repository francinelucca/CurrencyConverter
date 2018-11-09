package d.com.flucca.proyectofinalaltice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    MonedaManager monedaManager;
    Spinner monedaSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.monedaManager = MonedaManager.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monedaSpinner = (Spinner) findViewById(R.id.ddlMonedaConvert);
        monedaSpinner.setAdapter(new MonedaSeleccionAdapter(this,monedaManager.getMonedas()));
        selectSpinnerItemByValue(monedaManager.getPredeterminada());
        EditText monto = findViewById(R.id.txtMonto);
        monto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE){
                    Convertir();
                }
                return false;
            }
        });
    }
    public  void selectSpinnerItemByValue(Moneda m) {
        MonedaSeleccionAdapter adapter = (MonedaSeleccionAdapter) monedaSpinner.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(((Moneda)adapter.getItem(position)) == m) {
                monedaSpinner.setSelection(position);
                return;
            }
        }
    }
    public void onConvertClick(View view){
        Convertir();
    }
    public void Convertir(){
        Moneda convertFrom = (Moneda)monedaSpinner.getSelectedItem();
        EditText monto = findViewById(R.id.txtMonto);
        TextView valor = findViewById(R.id.txtValor);
        Double convertable = 0.0;
        try {
            convertable = Double.parseDouble(monto.getText().toString());
        }
        catch(Exception e){
            this.Toast("Debe ingresar un Monto Valido a Convertir");
            return;
        }
        ArrayList<Conversion> conversions = new ArrayList<Conversion>();
        for ( Moneda m : monedaManager.getMonedas()){
            Conversion c = new Conversion(m,monedaManager.Convertir(convertFrom,m,convertable),convertFrom);
            conversions.add(c);
        }
        ListView conversiones = findViewById(R.id.list_view_conversions);
        hideKeyboard();
        monto.setText("");
        valor.setText("Monto Seleccionado: " + convertFrom.getSimbolo() + " : " + convertable);
        View filler = findViewById(R.id.filler);
        filler.getBackground().setColorFilter(Color.parseColor("#000000"),PorterDuff.Mode.DARKEN);
        conversiones.setAdapter(new ConversionAdapter(this,conversions));
    }
    public void onMonedaListClick(View view){
        Intent intent = new Intent(this, MonedaListActivity.class);
        startActivity(intent);
    }

    public void Toast(String msg){
        new StyleableToast.Builder(this).text(msg).textColor(Color.WHITE).backgroundColor(Color.BLACK).show();
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showInfo(View view) {
        new AlertDialog.Builder(view.getContext()).setTitle("Tasa Converter").
                setMessage("Tasa Converter es una aplicación desarrollada por Francine Lucca que te permite convertir montos, manejar tasas y editar monedas de manera rápida, fácil y amigable, intentalo!")
                .setNeutralButton("De acuerdo",null).create().show();
    }
}
