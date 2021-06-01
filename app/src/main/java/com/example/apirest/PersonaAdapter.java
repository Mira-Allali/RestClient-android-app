package com.example.apirest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Model.Person;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Person> {

    private Context context;
    private  List<Person>persons;

    public PersonaAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        this.context=context;
        this.persons=objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.IdPersona);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);;
        TextView txtApellidos=(TextView)rowView.findViewById(R.id.Apellidos);;

        txtidPersona.setText(String.format("ID:%d",persons.get(position).getId()));
        txtNombre.setText(String.format("NOMBRE:%s",persons.get(position).getNombres()));
        txtApellidos.setText(String.format("APELLIDOS: %s",persons.get(position).getApellidos()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, PersonActivity.class);
               intent.putExtra("ID",String.valueOf(persons.get(position).getId()));
               intent.putExtra("NOMBRE",persons.get(position).getNombres());
                intent.putExtra("APELLIDOS",persons.get(position).getApellidos());
               context.startActivity(intent);
            }
        });
        return rowView;

    }

}
