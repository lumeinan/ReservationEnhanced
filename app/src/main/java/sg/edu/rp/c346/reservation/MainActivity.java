package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvMobileNo, tvGroupSize,tvDay, tvTime;
    EditText etName, etMobileNo, etGroupSize, etDay,etTime;
    CheckBox cbSmoke;

    Button btnReset;
    Button btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.Name);
        tvGroupSize = (TextView) findViewById(R.id.GroupSize);
        tvMobileNo = (TextView) findViewById(R.id.MobileNo);
        tvDay = (TextView) findViewById(R.id.textViewDay);
        tvTime = (TextView) findViewById(R.id.textViewTime);

        etName = (EditText) findViewById(R.id.Name1);
        etGroupSize = (EditText) findViewById(R.id.GroupSize1);
        etMobileNo = (EditText) findViewById(R.id.MobileNo1);
        etDay=(EditText) findViewById(R.id.editTextDay);
        etTime=(EditText) findViewById(R.id.editTextTime);
        cbSmoke = (CheckBox) findViewById(R.id.checkBoxSmoke);

        btnReserve = (Button) findViewById(R.id.buttonReserve);
        btnReset = (Button) findViewById(R.id.buttonReset);

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        etDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };

                // Create the Date Picker Dialog
                Calendar now=Calendar.getInstance();
                int year=now.get(Calendar.YEAR);
                int month=now.get(Calendar.MONTH);
                int day=now.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener, year, month, day);

                myDateDialog.show();
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        etTime.setText("Time: " + hourOfDay + ":" + minute);
                    }
                };

                // Create the Time Picker Dialog
               /*
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, 20, 00, true);
                */
                Calendar now=Calendar.getInstance();
                int hour=now.get(Calendar.HOUR_OF_DAY);
                int minute=now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, hour, minute, true);
                myTimeDialog.show();
            }
        });

        // tp.setIs24HourView(true);


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder=new AlertDialog.Builder(MainActivity.this);
                if (etName.getText().toString().length()!=0 && etGroupSize.getText().toString().length()!=0 &&etMobileNo.getText().toString().length()!=0) {
                    String day = etDay.getText().toString();
                    String time=etTime.getText().toString();
                    String strname = etName.getText().toString();
                    String strsize = etGroupSize.getText().toString();
                    String strmobile = etMobileNo.getText().toString();
                    if (cbSmoke.isChecked()) {
                        myBuilder.setTitle("Reservation message");
                        myBuilder.setMessage("Name: "+strname+"\nGroup Size: "+ strsize + "\nMobile No: " + strmobile+ "\nTime: " + time + "\nDate: " + day+ "\nSmoke: Smoking area" );
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("DISMISS",null);
                        myBuilder.setNeutralButton("Cancel", null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else {
                        myBuilder.setTitle("Reservation message");
                        myBuilder.setMessage("Name: "+strname+"\nGroup Size: "+ strsize + "\nMobile No: " + strmobile+ "\nTime: " + time + "\nDate: " + day+ "\nSmoke: Non-Smoking area" );
                        myBuilder.setNeutralButton("Cancel", null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }

                }
                else {
                    //tvDisplay.setText("Please fill in everything");
                    Toast.makeText(MainActivity.this, "PLEASE FILL IN EVERYTHING", Toast.LENGTH_SHORT).show();
                }
            }
        });





       /*tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
           @Override
            public void onTimeChanged(TimePicker timePicker v, int hourOfday, int i1) {

            }
        });*/



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDay.setText("");
                etTime.setText("");
                etName.setText("");
                etGroupSize.setText("");
                etMobileNo.setText("");
                cbSmoke.setChecked(false);
            }
        });

    }
}