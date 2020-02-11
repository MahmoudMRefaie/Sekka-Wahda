package com.example.sekkawa7da;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Home extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView profileName;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profileName = findViewById(R.id.username);

        Spinner from_spinner = findViewById(R.id.from_Spinner);
        ArrayAdapter<CharSequence> adapter_from_spinner = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        adapter_from_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_spinner.setAdapter(adapter_from_spinner);
        //spinner.setOnItemSelectedListener(this);

        Spinner to_spinner = findViewById(R.id.to_Spinner);
        ArrayAdapter<CharSequence> adapter_to_spinner = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        adapter_to_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_spinner.setAdapter(adapter_to_spinner);


        LoginResponse loginResponse = SharedPrefManager.getInstance(this).getResponse();    //To store token
        String token = loginResponse.getAccess_token();

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}