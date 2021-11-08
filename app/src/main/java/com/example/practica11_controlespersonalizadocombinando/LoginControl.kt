package com.example .practica11_controlespersonalizadocombinando

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.properties.Delegates

class LoginControl: LinearLayout {
    //Constructores
    constructor(ctx: Context): super(ctx){
        inicializar()
    }
    constructor(ctx: Context, attrs: AttributeSet): super(ctx, attrs){
        inicializar()
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ControlLogin,0,0).apply {
                try {
                    btnAceptar.text=getString(R.styleable.ControlLogin_login_text)
                }finally {
                    recycle()
                }
             }
    }
    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int): super(ctx, attrs, defStyleAttr){
        inicializar()
    }
    //Variables de controladores view
    private var lblMensaje: TextView by Delegates.notNull()
    private var btnAceptar: Button by Delegates.notNull()
    private var txtUsuario: EditText by Delegates.notNull()
    private var txtPassword: EditText by Delegates.notNull()
    //Interfaz para Listener
    interface OnLoginListener{
        fun onLogin(usuario: String, password: String)
    }
    fun inicializar(){
        //Utilizamos el layout 'login_control.xml' como interfaz del control
        val li= LayoutInflater.from(context)
        li.inflate(R.layout.login_control, this, true)

        //Obtenemos las referencias a los distintos controles
        txtUsuario=findViewById(R.id.txtUsuario) as EditText
        txtPassword=findViewById(R.id.txtPassword) as EditText
        btnAceptar=findViewById(R.id.btnAceptar) as Button
        lblMensaje=findViewById(R.id.lblMensaje) as TextView

        asignarEvento()
    }

    fun setMensaje(msg: String){
        lblMensaje.text=msg
    }
    var listener:OnLoginListener?=null
    fun setOnLoginListener(login:(String, String)->Unit){
        listener=object:OnLoginListener{
            override fun onLogin(usuario: String, password: String) {
                login(usuario,password)
            }
        }
    }
    fun asignarEvento(){
        btnAceptar.setOnClickListener{
            listener?.onLogin(txtUsuario.text.toString(), txtPassword.text.toString())
        }
    }

}