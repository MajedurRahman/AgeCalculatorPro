package com.design.capstone.agecalculatorpro;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String DATEPICKER_TAG = "datepicker";

    private TextView birthDay;
    private TextView ageTextview;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });


        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

        }
    }


    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

        birthDay.setText(year + "/" + month + "/" + day);


        displayBirthdayResult(new DateTime(year, month, day, 0, 0), toDay());
    }


    private DateTime toDay() {

        return new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH, 0, 0);
    }

    private Period displayBirthdayResult(DateTime dateToday, DateTime birthdayDate) {
        Period dateDifferencePeriod = new Period(dateToday, birthdayDate , PeriodType.yearMonthDayTime());

        ageTextview.setText(dateDifferencePeriod.getYears() + " Year " + dateDifferencePeriod.getMonths() +" Month " + dateDifferencePeriod.getDays() +" Days ");
        return dateDifferencePeriod;
    }

    void initComponent() {
        birthDay = (TextView) findViewById(R.id.birthdayfield);
        ageTextview = (TextView) findViewById(R.id.ageTextField);

        calendar = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), false);

    }

}