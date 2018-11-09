package d.com.flucca.proyectofinalaltice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MonedaListActivity extends AppCompatActivity{


    Spinner predeterminada;
    ListView monedas;
    MonedaManager monedaManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneda_list);
        monedas = findViewById(R.id.list_view_monedas);
        monedaManager = MonedaManager.getInstance();

        final MonedaAdapter adapter = new MonedaAdapter(this, monedaManager.getMonedas());
        monedas.setAdapter(adapter);

       monedas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Moneda m = monedaManager.getMonedas().get(position);
               Intent intent = new Intent(parent.getContext(),MonedaEditActivity.class);
               if(m != null) {
                   intent.putExtra("ID", m.getId());
               }
               startActivity(intent);
           }
       });



        predeterminada = (Spinner) findViewById(R.id.ddlMonedaPred);
        predeterminada.setAdapter(new MonedaSeleccionAdapter(this,monedaManager.getMonedas()));
        selectSpinnerItemByValue(monedaManager.getPredeterminada());
        predeterminada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Moneda m = (Moneda)predeterminada.getSelectedItem();
                if(m.getId() != monedaManager.Predeterminada.getId()) {

                    new AlertDialog.Builder(view.getContext()).setTitle("Cambio Predeterminada").setMessage("Esta seguro que desea cambiar la moneda predeterminada a: " + m.getNombre())
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Moneda cambio = (Moneda) predeterminada.getSelectedItem();
                            if(cambio.getId() != monedaManager.Predeterminada.getId())
                            {
                                for (Moneda cambiaria : monedaManager.getMonedas()) {
                                    if(cambiaria!= cambio) {
                                        cambiaria.setTasa(monedaManager.Convertir(cambiaria, cambio, 1.0));
                                    }
                                }
                                cambio.setTasa(1.0);
                                monedaManager.setPredeterminada(cambio);
                                Refresh();
                            }
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selectSpinnerItemByValue(monedaManager.getPredeterminada());
                        }
                    }).create().show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public  void selectSpinnerItemByValue(Moneda m) {
        MonedaSeleccionAdapter adapter = (MonedaSeleccionAdapter) predeterminada.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(((Moneda)adapter.getItem(position)) == m) {
                predeterminada.setSelection(position);
                return;
            }
        }
    }

    public void onMonedaEdit(View view){
        goEdit(null);
    }
    private void goEdit(Integer id)
    {
        Intent intent = new Intent(this, MonedaEditActivity.class);
        if(id != null){
            intent.putExtra("ID",id);
        }
        startActivity(intent);
    }
    private void Refresh(){
        Intent intent = new Intent(this,MonedaListActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
       startActivity(new Intent(this,MainActivity.class));
    }
}
