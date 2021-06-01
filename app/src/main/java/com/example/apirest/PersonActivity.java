package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.Model.Person;
import com.example.apirest.Utils.Apis;
import com.example.apirest.Utils.PersonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonActivity extends AppCompatActivity {
    PersonService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_layout);

        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);
        TextView f_name=(TextView)findViewById(R.id.nombres);
        final EditText txtNombres=(EditText)findViewById(R.id.txtNombres);
        TextView l_name=(TextView)findViewById(R.id.apellidos);
        final EditText txtApellidos=(EditText)findViewById(R.id.txtApellidos);

        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnReturn=(Button)findViewById(R.id.btnVolver);
        Button btnRemove=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOMBRE");
        String ape=bundle.getString("APELLIDOS");

        txtId.setText(id);
        txtNombres.setText(nom);
        txtApellidos.setText(ape);
        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p=new Person();
                p.setNombres(txtNombres.getText().toString());
                p.setApellidos(txtApellidos.getText().toString());
                if(id.trim().length()==0||id.equals("")){
                    addPerson(p);
                    Intent intent =new Intent(PersonActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updatePerson(p,Integer.valueOf(id));
                    Intent intent =new Intent(PersonActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson(Integer.valueOf(id));
                Intent intent =new Intent(PersonActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PersonActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addPerson(Person p){
        service= Apis.getPersonaService();
        Call<Person>call=service.addPersona(p);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updatePerson(Person p, int id){
        service= Apis.getPersonaService();
        Call<Person>call=service.updatePersona(p,id);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void deletePerson(int id){
        service=Apis.getPersonaService();
        Call<Person>call=service.deletePersona(id);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
