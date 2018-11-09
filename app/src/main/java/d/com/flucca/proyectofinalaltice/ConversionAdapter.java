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

public class ConversionAdapter extends ArrayAdapter<Conversion> {


    public ConversionAdapter(Context context, List<Conversion> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Conversion conversion = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conversion_item,parent,false);
        }
        TextView moneda = convertView.findViewById(R.id.moneda);
        TextView equivalente = convertView.findViewById(R.id.conversion);
        moneda.setText(new StringBuilder().append(conversion.getMonedaConvertida().getSimbolo()).append(" - ").append(conversion.getMonedaConvertida().getNombre()));
        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(5);
        equivalente.setText(new StringBuilder().append("Equivalente ").append(conversion.getMonedaOriginal().getSimbolo()).append(": ").append(decimalFormatter.format(conversion.getMonto())));
        return convertView;
    }
}
