//////Vista
package com.example.everardo.aplicacion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.everardo.aplicacion.adaptadores.usuarioAdaptador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class registros extends ActionBarActivity {
    // 1.- agregamos los recyvlerview (Listrecycler)
    EditText editsearch;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private int mYear, mMonth, mDay;
    EditText buscarlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        buscarlista = (EditText) findViewById(R.id.buscarlista);

        ///// agregamos el List usuario ()
        List<usuarios> items = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select id_usuario, nombre, apellidos, tipo, nivel, fecha, modulo from usuarios", null);

        for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
            items.add(new usuarios(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5), fila.getString(6)));
        }
        editsearch =(EditText) findViewById(R.id.buscarlista);

        recycler = (RecyclerView) findViewById(R.id.lista);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);


        adapter = new usuarioAdaptador(items);
        recycler.setAdapter(adapter);
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                populateListview(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//TEMRINA LO DE List
    }
// mETODO PAR AHACER CONSULTA POR FECHAS DEL REGISTRO EN LA BASE DE DATOS
    public void populateListview(String s) {
        final List<usuarios> items = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase AdminSQLiteOpenHelper = admin.getWritableDatabase();
        try {
            Cursor fila = AdminSQLiteOpenHelper.rawQuery("select id_usuario, nombre, apellidos, tipo, nivel, fecha, modulo from usuarios where id_usuario = '" + s
                    + "' or nombre like '%" + s + "%' or id_usuario like '%" + s + "%' or tipo like '%" + s + "%' or fecha like '%" + s + "%'", null);
            if (fila.moveToFirst()) {
                do {
                    items.add(new usuarios(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5), fila.getString(6)));
                } while (fila.moveToNext());
            }
        } catch (Exception e) {
        }
        admin.close();
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.lista);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        // Crear un nuevo adaptador
        adapter = new usuarioAdaptador(items);
        recycler.setAdapter(adapter);

    }
    //Metodo para mandar llamar la fecha desde el dispositivo y mostrarla en el EditTex indicado.
    public void Fecha2(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Setear valor en editText
                        buscarlista.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }

}
