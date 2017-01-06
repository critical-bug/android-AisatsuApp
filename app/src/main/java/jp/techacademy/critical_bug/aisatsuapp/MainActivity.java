package jp.techacademy.critical_bug.aisatsuapp;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView aisatsuTextView;
    Button buttonPickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aisatsuTextView = (TextView) findViewById(R.id.aisatsuTextView);
        buttonPickTime = (Button)findViewById(R.id.aisatsuShowButton);

        buttonPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d("UI-PARTS", String.format("%d:%02d", hourOfDay, minute));
        aisatsuTextView.setText(aisatsuByTime(hourOfDay, minute));
        aisatsuTextView.setBackgroundColor(colorByTime(hourOfDay));
    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        TimePickerDialog picker = new TimePickerDialog(this, this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        picker.show();
    }

    private String aisatsuByTime(int hour, int minute) {
        if (hour >= 18 || hour <= 1) {
            return "こんばんは";
        }
        if (hour <= 9) {
            return "おはよう";
        }
        return "こんにちは";
    }

    private int colorByTime(int hour) {
        if (hour >= 18 || hour < 6) {
            // light blue
            return 0xff8080ff;
        }
        // light yellow
        return 0xffffff80;
    }
}
