package d.com.flucca.proyectofinalaltice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class MonedaSeleccionAdapter extends ArrayAdapter<Moneda> {
    public MonedaSeleccionAdapter(@NonNull Context context, @NonNull List<Moneda> objects) {
        super(context, 0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Moneda moneda = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.moneda_seleccion_item,parent,false);
        }
        TextView lblMoneda = convertView.findViewById(R.id.moneda_seleccion);

        lblMoneda.setText(new StringBuilder().append(moneda.getSimbolo()));
        return convertView;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Moneda moneda = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.moneda_seleccion_item,parent, false);
        }
        TextView lblMoneda = convertView.findViewById(R.id.moneda_seleccion);
        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(5);
        lblMoneda.setText(new StringBuilder().append(moneda.getSimbolo()).append(" (").append(decimalFormatter.format(moneda.getTasa())).append(")").toString());
        return convertView;
    }
}
