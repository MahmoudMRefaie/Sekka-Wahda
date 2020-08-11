package com.mahmoudrefaie.sekkawahda.ui.Registeration;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.mahmoudrefaie.sekkawahda.R;
import com.mahmoudrefaie.sekkawahda.ui.Login.Login;

import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.appcompat.widget.Toolbar;

public class Registeration extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sign_up)
    TextView signUp;

    private FragmentRegisterA firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);

        //BackArraw at ToolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_blue_24dp);
        getSupportActionBar().setTitle("");

        //Open first Fragment that has username, email, password
        firstFragment = new FragmentRegisterA();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_register_layout , firstFragment).commit();

        login.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login) {
            Intent intent = new Intent(Registeration.this, Login.class);
            startActivity(intent);
            this.finish();
        }
    }
}