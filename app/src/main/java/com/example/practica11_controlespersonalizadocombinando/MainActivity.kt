package com.example.practica11_controlespersonalizadocombinando

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var login: LoginControl
    private lateinit var lblMensaje: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login=findViewById(R.id.ctlLogin)
        lblMensaje=findViewById(R.id.lblMensaje)

        login.setOnLoginListener { usuario, password ->
        if(usuario=="usr" && password == "pass")
            lblMensaje.text="Login correcto"
        else
            lblMensaje.text="Vuelve a intentar"
        }

    }
}