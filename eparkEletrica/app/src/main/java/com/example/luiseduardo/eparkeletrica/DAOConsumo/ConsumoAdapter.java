package com.example.luiseduardo.eparkeletrica.DAOConsumo;
 
import java.util.List;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.luiseduardo.eparkeletrica.R;

public class ConsumoAdapter extends BaseAdapter  {
    private Context context;
 
    private List<ConsumoVO> lstConsumo;
    private LayoutInflater inflater;
 
    public ConsumoAdapter(Context context, List<ConsumoVO> listConsumo) {
        this.context = context;
        this.lstConsumo = listConsumo;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
     
    @Override
    public void notifyDataSetChanged() {   
            super.notifyDataSetChanged();
    }
         
    public int getCount() {
        return lstConsumo.size();
    }

    public void remove(final ConsumoVO item) {
        this.lstConsumo.remove(item);
    } 

    public void add(final ConsumoVO item) {
        this.lstConsumo.add(item);
    }    
     
    public Object getItem(int position) {
        return lstConsumo.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup viewGroup) {
            ConsumoVO consumo = lstConsumo.get(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.consumo_row, null);
                 
                holder = new ViewHolder();
                holder.tvRegistro_inicial = (TextView) convertView.findViewById(R.id.edtRegistroInicial);
                holder.TvRegistro_final = (TextView) convertView.findViewById(R.id.edtRegistroFinal);
                holder.tvMes = (TextView) convertView.findViewById(R.id.editMes);
                 
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
 
            holder.tvRegistro_inicial.setText(consumo.getRegistro_inicial());
            holder.TvRegistro_final.setText(consumo.getRegistro_final());
            holder.tvMes.setText(consumo.getMes());
            return convertView;
    }

}