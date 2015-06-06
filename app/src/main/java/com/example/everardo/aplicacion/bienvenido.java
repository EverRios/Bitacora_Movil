
package com.example.everardo.aplicacion;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class bienvenido extends Activity implements View.OnClickListener {
    // Se declaran las variables
    EditText et_idusuario, et_nombres, et_apellidos, et_tipo, et_nivel, et_fecha, et_mdulo;
    private int mYear, mMonth, mDay;
    ///AGREGAS LOS CHECKBOX Y LAS VARIABLES
    CheckBox escrito, linea;
    String tipoE, nivelE;
    CheckBox inicial, intermedio, avanzado;

    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        nombre = (TextView) findViewById(R.id.tvNombre);
        Bundle bolsa = getIntent().getExtras();
        nombre.setText(bolsa.getString("NOMBRE"));
        // Se enlazan los EditText y CheckBox
        et_idusuario = (EditText) findViewById(R.id.et_idusuario);
        et_nombres = (EditText) findViewById(R.id.et_nombres);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_tipo = (EditText) findViewById(R.id.et_tipo);
        et_nivel = (EditText) findViewById(R.id.et_nivel);
        et_fecha = (EditText) findViewById(R.id.et_fecha);
        et_mdulo = (EditText) findViewById(R.id.et_modulo);

        escrito = (CheckBox) findViewById(R.id.rdescrito);
        linea = (CheckBox) findViewById(R.id.rdlinea);
        inicial = (CheckBox) findViewById(R.id.rdinicial);
        intermedio = (CheckBox) findViewById(R.id.rdintermedio);
        avanzado = (CheckBox) findViewById(R.id.rdavanzado);
    }

    // Metodo para guardar los datos en la base de datos
    public void alta(View v) {
        boolean funciona = true;
        if (et_idusuario.getText().toString().equals("") || et_nombres.getText().toString().equals("") || et_fecha.getText().toString().equals("")) {
            Dialog d = new Dialog(this);
            d.setTitle("¡Error!");
            TextView tv = new TextView(this);
            tv.setText("Hay Campos Vacios");
            d.setContentView(tv);
            d.show();

        } else {
            try {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String idusuario = et_idusuario.getText().toString();
                String nombres = et_nombres.getText().toString();
                String apellidos = et_apellidos.getText().toString();
                String tipoE = et_tipo.getText().toString();
                String nivelE = et_nivel.getText().toString();
                String fecha = et_fecha.getText().toString();
                String modulo = et_mdulo.getText().toString();

                if (escrito.isChecked() == true) {
                    tipoE = "Escrito";
                } else if (linea.isChecked() == true) {
                    tipoE = "Linea";
                }
                if (inicial.isChecked() == true) {
                    nivelE = "Inicial";
                } else if (intermedio.isChecked() == true) {
                    nivelE = "Intermedio";
                } else if (avanzado.isChecked() == true) {
                    nivelE = "Avanzado";
                }
                Cursor fila = bd.rawQuery("select nombre, apellidos, tipo, nivel, fecha from usuarios where id_usuario=" + idusuario, null);
                if (fila.getCount() >= 1) {
                    Toast.makeText(this, "El ID Persona Ya Se Encuentra Registrado", Toast.LENGTH_SHORT).show();


                } else {
                    ContentValues registro = new ContentValues();

                    registro.put("id_usuario", idusuario);
                    registro.put("nombre", nombres);
                    registro.put("apellidos", apellidos);
                    registro.put("tipo", tipoE);
                    registro.put("nivel", nivelE);
                    registro.put("fecha", fecha);
                    registro.put("modulo", modulo);


                    bd.insert("usuarios", null, registro);
                    bd.close();
                    et_idusuario.setText("");
                    et_nombres.setText("");
                    et_apellidos.setText("");
                    et_tipo.setText("");
                    et_nivel.setText("");
                    et_fecha.setText("");
                    et_mdulo.setText("");
                    escrito.setChecked(false);
                    linea.setChecked(false);
                    inicial.setChecked(false);
                    intermedio.setChecked(false);
                    avanzado.setChecked(false);

                    Toast.makeText(this, "Se Agrego Un Nuevo Registro", Toast.LENGTH_SHORT).show();

                }//fin else
            } catch (Exception e) {

                Dialog d = new Dialog(this);
                d.setTitle("Se ha Producido Un Error");
                TextView tv = new TextView(this);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }
        }
    }

    // Metodo para consultar los datos en la base de datos
    public void consulta(View v) {
        boolean funciona = true;
        if (et_idusuario.getText().toString().equals("")) {
            Dialog d = new Dialog(this);
            d.setTitle("¡Error!");
            TextView tv = new TextView(this);
            tv.setText("Debe colocar un ID persona Paa Consultar");
            d.setContentView(tv);
            d.show();

        } else {
            try {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String idusuario = et_idusuario.getText().toString();
                // muestra el registro guardado

                Cursor fila = bd.rawQuery("select nombre, apellidos, tipo, nivel, fecha, modulo from usuarios where id_usuario=" + idusuario, null);
                if (fila.moveToFirst()) {
                    et_nombres.setText(fila.getString(0));
                    et_apellidos.setText(fila.getString(1));
                    et_tipo.setText(fila.getString(2));
                    et_nivel.setText(fila.getString(3));
                    et_fecha.setText(fila.getString(4));
                    et_mdulo.setText(fila.getString(5));


                    if (et_tipo.getText().toString().equals("Escrito")) {
                        escrito.setChecked(true);

                    } else if (et_tipo.getText().toString().equals("Linea")) {
                        linea.setChecked(true);
                    }

                    if (et_nivel.getText().toString().equals("Inicial")) {
                        inicial.setChecked(true);

                    } else if (et_nivel.getText().toString().equals("Intermedio")) {
                        intermedio.setChecked(true);
                    } else if (et_nivel.getText().toString().equals("Avanzado")) {
                        avanzado.setChecked(true);
                    } else {
                        Toast.makeText(this, "No Existe El Registro", Toast.LENGTH_SHORT).show();
                    }
                    bd.close();
                }//fin else
            } catch (Exception e) {

                Dialog d = new Dialog(this);
                d.setTitle("Se ha Producido Un Error");
                TextView tv = new TextView(this);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }

        }
    }

    // Metodo para borrar los datos en la base de datos
    public void baja(View v) {
        boolean funciona = true;
        if (et_idusuario.getText().toString().equals("")) {
            Dialog d = new Dialog(this);
            d.setTitle("¡Error!");
            TextView tv = new TextView(this);
            tv.setText("Debe Consultar Primero un registro para dar de Baja");
            d.setContentView(tv);
            d.show();

        } else {
            try {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String idusuario = et_idusuario.getText().toString();
                // nombre de la tabla(usuarios) y la condicion(id_usuario)
                int cant = bd.delete("usuarios", "id_usuario=" + idusuario, null);
                bd.close();

                et_idusuario.setText("");
                et_nombres.setText("");
                et_apellidos.setText("");
                et_tipo.setText("");
                et_nivel.setText("");
                et_fecha.setText("");
                et_mdulo.setText("");


                if (cant == 1) {
                    Toast.makeText(this, "Se Borró El Registro", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No Existe el Registro", Toast.LENGTH_SHORT).show();
                }//fin else
            } catch (Exception e) {

                Dialog d = new Dialog(this);
                d.setTitle("Se ha Producido Un Error");
                TextView tv = new TextView(this);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }


        }
    }

    // Metodo para modificarr los datos en la base de datos
    public void modificacion(View v) {
        boolean funciona = true;
        if (et_idusuario.getText().toString().equals("")) {
            Dialog d = new Dialog(this);
            d.setTitle("¡Error!");
            TextView tv = new TextView(this);
            tv.setText("Debe Consultar Primero un registro para Modificar");
            d.setContentView(tv);
            d.show();

        } else {
            try {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String idusuario = et_idusuario.getText().toString();
                String nombres = et_nombres.getText().toString();
                String apellidos = et_apellidos.getText().toString();
                String tipoE = et_tipo.getText().toString();
                String nivelE = et_nivel.getText().toString();
                String fecha = et_fecha.getText().toString();
                String modulo = et_mdulo.getText().toString();


                if (escrito.isChecked() == true) {
                    tipoE = "Escrito";
                } else if (linea.isChecked() == true) {
                    tipoE = "Linea";
                }
                if (inicial.isChecked() == true) {
                    nivelE = "Inicial";
                } else if (intermedio.isChecked() == true) {
                    nivelE = "Intermedio";
                } else if (avanzado.isChecked() == true) {
                    nivelE = "Avanzado";
                }
                ContentValues registro = new ContentValues();
// nombre de los campos y variables
                registro.put("id_usuario", idusuario);
                registro.put("nombre", nombres);
                registro.put("apellidos", apellidos);
                registro.put("tipo", tipoE);
                registro.put("nivel", nivelE);
                registro.put("fecha", fecha);
                registro.put("modulo", modulo);


// nombre de la tabla(usuarios) y la condicion(id_usuario)
                int cant = bd.update("usuarios", registro, "id_usuario=" + idusuario, null);
                bd.close();

                if (cant == 1) {
                    Toast.makeText(this, "Se modificaron los datos del Registro", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No existe el Registro", Toast.LENGTH_SHORT).show();
                }//fin else
            } catch (Exception e) {

                Dialog d = new Dialog(this);
                d.setTitle("Se ha Producido Un Error");
                TextView tv = new TextView(this);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }


        }
    }

    // Metodo para limpiar todos los campos
    public void limpia(View v) {
        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_tipo.setText("");
        et_nivel.setText("");
        et_fecha.setText("");
        et_mdulo.setText("");
        escrito.setChecked(false);
        linea.setChecked(false);
        inicial.setChecked(false);
        intermedio.setChecked(false);
        avanzado.setChecked(false);


    }

    //Metodo para mandar llamar la fecha desde el dispositivo y mostrarla en el EditTex indicado.
    public void Fecha_1(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Setear valor en editText
                        et_fecha.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    // metodo para ver todos los registros guardados en la base de datos
    public void ver(View v) {
        Intent intent = new Intent(this, registros.class);
        startActivity(intent);


    }

    //Metodo para indicar que cuando este CheckBox  este seleccionado el otro debe estar vacio.
    public void escrito(View v) {
        linea.setChecked(false);
    }

    //Metodo para indicar que cuando este CheckBox  este seleccionado el otro debe estar vacio.
    public void linea(View v) {
        escrito.setChecked(false);
    }

    //Metodo para indicar que cuando este CheckBox  este seleccionado el otro debe estar vacio.
    public void inicial(View v) {
        inicial.setChecked(true);
        intermedio.setChecked(false);
        avanzado.setChecked(false);

    }

    //Metodo para indicar que cuando este CheckBox  este seleccionado el otro debe estar vacio.
    public void intermedio(View v) {

        inicial.setChecked(false);
        intermedio.setChecked(true);
        avanzado.setChecked(false);

    }

    //Metodo para indicar que cuando este CheckBox  este seleccionado el otro debe estar vacio.
    public void avanzado(View v) {

        inicial.setChecked(false);
        intermedio.setChecked(false);
        avanzado.setChecked(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bienvenido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.title_activity_acerca) {
       // return true;
        switch (item.getItemId()) {
            case R.id.title_activity_acerca:
                Intent intent = new Intent(bienvenido.this, acerca.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
        @Override
        public void onClick (View v){

        }
    }

