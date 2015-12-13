package com.example.luiseduardo.eparkeletrica.DAOCadastro;
 
import java.util.List;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luiseduardo.eparkeletrica.DAOConsumo.ViewHolder;
import com.example.luiseduardo.eparkeletrica.R;

public class CadastroAdapter extends BaseAdapter  {

    private Context context;

    private List<CadastroVO> lstCadastro;
    private LayoutInflater inflater;

    public CadastroAdapter(Context context, List<CadastroVO> listCadastro) {
        this.context = context;
        this.lstCadastro = listCadastro;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Atualizar ListView de acordo com o lstContato
    @Override
    public void notifyDataSetChanged() {
        try{
            super.notifyDataSetChanged();
        }catch (Exception e) {

        }
    }

    public int getCount() {
        return lstCadastro.size();
    }

    //Remover item da lista
    public void remove(final CadastroVO item) {
        this.lstCadastro.remove(item);
    }

    //Adicionar item na lista
    public void add(final CadastroVO item) {
        this.lstCadastro.add(item);
    }

    public Object getItem(int position) {
        return lstCadastro.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        try
        {

            CadastroVO cadastro = lstCadastro.get(position);

            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.contato_row, null);


                holder = new ViewHolder();
                holder.tvNome = (TextView) convertView.findViewById(R.id.txtNome);
                holder.tvEndereco = (TextView) convertView.findViewById(R.id.txtEndereco);
                holder.tvTelefone = (TextView) convertView.findViewById(R.id.txtTelefone);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvNome.setText(cadastro.getNome());
            holder.tvEndereco.setText(cadastro.getEndereco());
            holder.tvTelefone.setText(cadastro.getTelefone());

            return convertView;

        }catch (Exception e) {

        }
        return convertView;
    }


    //Criada esta classe estática para guardar a referência dos objetos abaixo
    static class ViewHolder {
        public TextView tvNome;
        public TextView tvEndereco;
        public TextView tvTelefone;
    }

}