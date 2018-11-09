package d.com.flucca.proyectofinalaltice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class MonedaAdapter extends ArrayAdapter<Moneda>  {

    public MonedaAdapter(Context context, List<Moneda> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Moneda moneda = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.moneda_item,parent,false);
        }
        TextView id = convertView.findViewById(R.id.id);
        TextView monedaDesc = convertView.findViewById(R.id.moneda);
        TextView tasa = convertView.findViewById(R.id.tasa);
        id.setText( Integer.toString(moneda.getId()));
        monedaDesc.setText(new StringBuilder().append(moneda.getSimbolo()).append(" - ").append(moneda.getNombre()));
        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(5);
        tasa.setText(new StringBuilder().append("Tasa Actual: ").append(decimalFormatter.format(moneda.getTasa())));
        return convertView;
    }
}
