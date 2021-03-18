package com.mahmoudrefaie.sekkawahda.ui.Registeration

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login

class Registeration : AppCompatActivity(), View.OnClickListener {
    @JvmField
    @BindView(R.id.login)
    var login: TextView? = null

    @JvmField
    @BindView(R.id.toolbar)
    var toolbar: Toolbar? = null

    @JvmField
    @BindView(R.id.sign_up)
    var signUp: TextView? = null
    private var firstFragment: FragmentRegisterA? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)
        ButterKnife.bind(this)

        //BackArraw at ToolBar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_blue_24dp)
        supportActionBar!!.title = ""

        //Open first Fragment that has username, email, password
        firstFragment = FragmentRegisterA()
        val fm = supportFragmentManager
        fm.beginTransaction()
                .add(R.id.fragment_register_layout, firstFragment!!)
                .addToBackStack("first_fragment")
                .commit()

        login!!.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.login) {
            val intent = Intent(this@Registeration, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}