package d.com.flucca.proyectofinalaltice;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MonedaEditActivity extends AppCompatActivity {

    private int id;
    MonedaManager monedaManager;
    EditText nombre;
    EditText abreviatura;
    EditText tasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneda_edit);
        monedaManager = MonedaManager.getInstance();
        nombre = findViewById(R.id.txtNombre);
        abreviatura = findViewById(R.id.txtAbreviatura);
        tasa = findViewById(R.id.txtTasa);

        TextView TasaLbl = findViewById(R.id.lblTasa);

        TasaLbl.setText(new StringBuilder().append("Tasa Actual(").append(monedaManager.getPredeterminada().getSimbolo()).append("):"));
        Button save = findViewById(R.id.btnCrearMoneda);
        TextView id = findViewById(R.id.txtId);

        Intent intent = getIntent();
        this.id = intent.getIntExtra("ID",0);
        if(this.id != 0)
        {
            id.setText(Integer.toString(this.id));
            save.setText("Actualizar");
            this.fillForm();
        }
        else
        {
            id.setText("");
            save.setText("Crear");
        }
    }
    private void fillForm()
    {
        Moneda m = monedaManager.findById(this.id);
        nombre.setText(m.getNombre());
        tasa.setText(Double.toString(m.getTasa()));
        abreviatura.setText(m.getSimbolo());
        if(m.isPredeterminada()){
            tasa.setEnabled(false);
        }
    }
    public void Toast(String msg){
        new StyleableToast.Builder(this).text(msg).textColor(Color.WHITE).backgroundColor(Color.BLACK).show();
    }
    public void onSave(View view){
        TextView Id = findViewById(R.id.txtId);

        if(nombre.getText().toString().trim().equals("")){
            this.Toast("Campo Nombre no valido");
            return;
        }
        if(abreviatura.getText().toString().trim().equals("")){
            this.Toast("Campo Abreviatura no valido");
            return;
        }
        Double tasaMoneda = 0.0;
        try{
            tasaMoneda = Double.parseDouble(tasa.getText().toString());
        }
        catch (Exception e){
            this.Toast("Campo Tasa no valido");
            return;
        }

        if(this.id == 0){
            if(monedaManager.monedaConMismoNombre(nombre.getText().toString()) != null){

                this.Toast("Existe una Moneda con Este Nombre");
                return;
            }
            if(monedaManager.monedaConMismoSimbolo(abreviatura.getText().toString())!= null){

                this.Toast("Existe una Moneda con Esta Abreviatura");
                return;
            }
            int id = monedaManager.CrearMoneda(nombre.getText().toString(), tasaMoneda,abreviatura.getText().toString());
            Id.setText(Integer.toString(id));
            this.id = id;
            Button save = findViewById(R.id.btnCrearMoneda);
            save.setText("Actualizar");
            this.Toast("Moneda Creada Exitosamente");
        }
        else{
            Moneda mismo_nombre = monedaManager.monedaConMismoNombre(nombre.getText().toString());
            if(mismo_nombre != null &&  mismo_nombre.getId() != this.id){

                this.Toast(("Existe una Moneda con Este Nombre"));
                return;
            }
            Moneda mismo_simbolo = monedaManager.monedaConMismoSimbolo(abreviatura.getText().toString());
            if(mismo_simbolo != null && mismo_simbolo.getId() != this.id){
                this.Toast(("Existe una Moneda con Esta Abreviatura"));
                return;
            }
            monedaManager.ActualizarMoneda(this.id,nombre.getText().toString(), tasaMoneda,abreviatura.getText().toString());
            this.Toast("Moneda Actualizada Exitosamente");
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MonedaListActivity.class));
    }
}
