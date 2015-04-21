
package com.example.everardo.aplicacion;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class bienvenido extends Activity implements View.OnClickListener {

    EditText et_idusuario, et_nombres, et_apellidos, et_tipo, et_nivel, et_modulo;

    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        nombre = (TextView) findViewById(R.id.tvNombre);
        Bundle bolsa = getIntent().getExtras();
        nombre.setText(bolsa.getString("NOMBRE"));

        et_idusuario = (EditText) findViewById(R.id.et_idusuario);
        et_nombres = (EditText) findViewById(R.id.et_nombres);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_tipo = (EditText) findViewById(R.id.et_tipo);
        et_nivel = (EditText) findViewById(R.id.et_nivel);
        et_modulo = (EditText) findViewById(R.id.et_modulo);



    }
    public void alta (View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();



        String idusuario = et_idusuario.getText().toString();
        String nombres = et_nombres.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String tipo = et_tipo.getText().toString();
        String nivel = et_nivel.getText().toString();
        String modulo = et_modulo.getText().toString();
        ContentValues registro = new ContentValues();

        registro.put("id_usuario", idusuario);
        registro.put("user", nombres);
        registro.put("password", apellidos);
        registro.put("nombre", tipo);
        registro.put("apellido_p", nivel);
        registro.put("apellido_m", modulo);


        bd.insert("usuarios", null, registro);
        bd.close();
        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_tipo.setText("");
        et_nivel.setText("");
        et_modulo.setText("");


        Toast.makeText(this, "Se agrego un nuevo usuario", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        // muestra el registro guardado
        Cursor fila = bd.rawQuery("select user, password, nombre, apellido_p, apellido_m from usuarios where id_usuario=" + idusuario, null);
        if (fila.moveToFirst()) {
            et_nombres.setText(fila.getString(0));
            et_apellidos.setText(fila.getString(1));
            et_tipo.setText(fila.getString(2));
            et_nivel.setText(fila.getString(3));
            et_modulo.setText(fila.getString(4));


        } else {
            Toast.makeText(this,"No Existe El Registro",Toast.LENGTH_SHORT).show();
        } bd.close();
    }

    public void baja(View v) {
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
        et_modulo.setText("");


        if (cant == 1) {
            Toast.makeText(this, "Se borró el Regustro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el Registro", Toast.LENGTH_SHORT).show();

        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String nombres = et_nombres.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String tipo = et_tipo.getText().toString();
        String nivel = et_nivel.getText().toString();
        String modelo = et_modulo.getText().toString();



        ContentValues registro = new ContentValues();
// nombre de los campos y variables
        registro.put("id_usuario", idusuario);
        registro.put("user", nombres);
        registro.put("password", apellidos);
        registro.put("nombre", tipo);
        registro.put("apellido_p", nivel);
        registro.put("apellido_m", modelo);

// nombre de la tabla(usuarios) y la condicion(id_usuario)
        int cant = bd.update("usuarios", registro, "id_usuario=" + idusuario, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del Registro",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el Registro",Toast.LENGTH_SHORT).show();

        }
    }

    public void limpia (View v) {
        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_tipo.setText("");
        et_nivel.setText("");
        et_modulo.setText("");


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
