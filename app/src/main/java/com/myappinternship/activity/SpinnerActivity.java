package com.myappinternship.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myappinternship.R;

public class SpinnerActivity extends AppCompatActivity {

    Spinner spinner;
    TextView tvSpinner;
    String strData[] = {"Select City", "Surat", "Ahmedabad", "Vadodara"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner = findViewById(R.id.spinner);
        tvSpinner = findViewById(R.id.tv_spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strData){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                TextView tvData = (TextView) super.getView(position, convertView, parent);

                if (position != 0){

                    tvData.setTextColor(Color.WHITE);

                }
                return tvData;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                TextView tvData = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == 0){

                    tvData.setTextColor(Color.GRAY);
                }else {
                    tvData.setTextColor(Color.MAGENTA);
                }
                return  tvData;
            }
        };

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strOutPut = parent.getItemAtPosition(position).toString();

                if (position != 0) {
                    tvSpinner.setText(strOutPut);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}