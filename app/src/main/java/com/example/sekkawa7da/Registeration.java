package com.example.sekkawa7da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registeration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private EditText username,email,password,ssn,phone_no;
    private Spinner city;
    private CheckBox ifAgree;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        username = (EditText)findViewById(R.id.user);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        ssn = (EditText)findViewById(R.id.ssn);
        phone_no = (EditText)findViewById(R.id.phone);
        city = (Spinner)findViewById(R.id.city);
        ifAgree = (CheckBox)findViewById(R.id.agreeCheck);
        button = (Button)findViewById(R.id.regBtn);

        /*if(ifAgree.isChecked())
            button.setEnabled(true);
        else
            button.setEnabled(true);*/

        preferenceHelper = new PreferenceHelper(this);
        if(preferenceHelper.getIsLogin()) {
            Intent intent = new Intent(Registeration.this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName = username.getText().toString();
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getSsn = ssn.getText().toString();
                String getPhone = phone_no.getText().toString();
                String getCity = city.getSelectedItem().toString();

                User user = new User(getUserName,getEmail,getPassword,getSsn,getPhone,getCity);

                Call<String> call = RetrofitClient.getInstance().getApi().createUser(user);

                //Toast.makeText(Registeration.this, getUserName, Toast.LENGTH_LONG).show();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            if (response.isSuccessful()) {
                                String res = response.body();
                                Toast.makeText(Registeration.this, "Registered Successfully" , Toast.LENGTH_LONG).show();
                                openActivityLogin();
                                //text.setText(res);
                            } else {
                                String res = response.errorBody().string();
                                Toast.makeText(Registeration.this, res, Toast.LENGTH_LONG).show();
                                Log.e("Error Code", String.valueOf(response.code()));
                                Log.e("Error Body", response.errorBody().toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            /*public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText(Registeration.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        try {
                            parseRegData(jsonresponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(Registeration.this,"Else",Toast.LENGTH_LONG).show();
                    }
                }
            }*/

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Registeration.this,"On Failure",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void registerMe() {

//        User user = new User(getUserName,getEmail,getPassword,getSsn,getPhone,getCity);
//
//        RegisterInterface api = RetrofitClient.getInstance().getApi()
//                .
//
//        Call<String> call = api.getUserRegi(getUserName,getEmail,getPassword,getSsn,getPhone,getCity);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response.isSuccessful()) {
//                    Toast.makeText(Registeration.this, response.body().toString() ,Toast.LENGTH_LONG).show();
//                    openActivityLogin();
//                }
//                else {
//                    Log.e("Error Code", String.valueOf(response.code()));
//                    Log.e("Error Body", response.errorBody().toString());
//
//                    Toast.makeText(Registeration.this,"Response ERROR",Toast.LENGTH_LONG).show();
//                    //display the appropriate message...
//                }
//            }
//            /*public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
//                //Toast.makeText(Registeration.this,"Registered Successfully",Toast.LENGTH_LONG).show();
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
//
//                        String jsonresponse = response.body().toString();
//                        try {
//                            parseRegData(jsonresponse);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    } else {
//                        //Log.i("onEmptyResponse", "Returned empty response");
//                        Toast.makeText(Registeration.this,"Else",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }*/
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(Registeration.this,t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void parseRegData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")){

            saveInfo(response);

            Toast.makeText(Registeration.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Registeration.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }else {
            Toast.makeText(Registeration.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }


    private void saveInfo(String response){

        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putUserName(dataobj.getString("UserName"));
                    preferenceHelper.putEmail(dataobj.getString("UserEmailID"));
                    preferenceHelper.putSSN(dataobj.getString("SSN"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    User user = new User(getUserName,getEmail,getPassword,getSsn,getPhone,getCity);
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://sekkawahda20200201024113.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    Call<User> call = apiInterface.storeUser(user);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(Registeration.this , getUserName , Toast.LENGTH_SHORT).show();
                    openActivityLogin();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(Registeration.this , t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    });
    */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openActivityLogin(){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

}