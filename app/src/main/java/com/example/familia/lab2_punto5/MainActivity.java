package com.example.familia.lab2_punto5;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.Calendar;


public class MainActivity extends Activity {
    private TextView nombre;
    private TextView correo;
    private TextView tel;
    private TextView sexo;
    private TextView ciudad;
    private TextView hobbies;
    private TextView birth;
    private EditText enombre;
    private EditText ecorreo;
    private EditText etel;
    private TextView text_date;
    private DatePicker date_picker;
    private Button button;
    private Spinner spinner1;
    private int year;
    private int month;
    private int day;
    private int sex=0;
    private int f_depor=0;
    private int f_ent=0;
    private int f_lect=0;
    private int f_dormir=0;

    static final int DATE_DIALOG_ID = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentDate();
        addButtonListener();
    }
    public void select_h(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.chdep:
                if(checked)
                    f_depor=1;
                else
                    f_depor=0;
                break;
            case R.id.chent:
                if(checked)
                    f_ent=1;
                else
                    f_ent=0;
                break;
            case R.id.chlect:
                if(checked)
                    f_lect=1;
                else
                    f_lect=0;
                break;
            case R.id.chdorm:
                if(checked)
                    f_dormir=1;
                else
                    f_dormir=0;
                break;
        }
    }
    public void select_s(View view){
        if(view.getId()==R.id.rmasc){
            sex=1;
        }else if (view.getId()==R.id.rfem){
            sex=2;
        }
    }
    public void submit(View view){
        nombre = (TextView)findViewById(R.id.tname);
        correo =(TextView)findViewById(R.id.tcorreo);
        tel = (TextView)findViewById(R.id.ttel);
        sexo = (TextView)findViewById(R.id.tsex);
        ciudad = (TextView)findViewById(R.id.tciudad);
        hobbies = (TextView)findViewById(R.id.thobbies);
        birth = (TextView)findViewById(R.id.tdate);
        enombre = (EditText)findViewById(R.id.ename);
        etel = (EditText)findViewById(R.id.etel);
        ecorreo = (EditText)findViewById(R.id.ecorreo);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        String mostrar = "hobbies: ";
        String depor="Deportes";
        String enter="Entretenimiento";
        String lect="Lectura";
        String dorm="Dormir";

        nombre.setText(enombre.getText());
        correo.setText(ecorreo.getText());
        tel.setText(etel.getText());
        if(sex==1){
            sexo.setText("Masculino");
        }
        else if(sex==2){
            sexo.setText("Femenino");
        }
        if(f_depor==1)
            mostrar = mostrar+" "+depor;
        if (f_ent==1)
            mostrar=mostrar+" "+enter;
        if(f_lect==1)
            mostrar=mostrar+" "+lect;
        if(f_dormir==1)
            mostrar=mostrar+" "+dorm;
        hobbies.setText(mostrar);
        birth.setText(text_date.getText());
        ciudad.setText(String.valueOf(spinner1.getSelectedItem()));
    }


    // display current date both on the text view and the Date Picker when the application starts.
    public void setCurrentDate() {
        text_date = (TextView) findViewById(R.id.text_date);
        date_picker = (DatePicker) findViewById(R.id.date_picker);
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        // set current date into textview
        text_date.setText(new StringBuilder()
                // Month is 0 based, so you have to add 1
                .append(month + 1).append("-")
                .append(day).append("-")
                .append(year).append(" "));
        // set current date into Date Picker
        date_picker.init(year, month, day, null);
    }

    public void addButtonListener() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            // set selected date into Text View
            text_date.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year).append(" "));
            // set selected date into Date Picker
            date_picker.init(year, month, day, null);
        }
    };

}
