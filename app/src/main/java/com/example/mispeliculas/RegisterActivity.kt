package com.example.mispeliculas

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mispeliculas.databinding.ActivityRegisterBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.ArrayAdapter
import android.widget.Spinner

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private var fechaNacimiento: String = ""
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        /* Data Picker - Date  */
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fechaNacimiento = sdf.format(cal.time).toString()
                registerBinding.dateButton.setText(fechaNacimiento)
            }

        registerBinding.dateButton.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        /* Spinner - City */

        /* */

        registerBinding.registerButton.setOnClickListener{
            Log.d("button","clicked")
            val email = registerBinding.emailEditText.text.toString()
            val password = registerBinding.passwordEditText.text.toString()
            val repPassword = registerBinding.repPasswordEditText.text.toString()
            var genre = "Masculino"
            var favoritesGenre = ""
            val cities = registerBinding.citySpinner.selectedItem.toString()


            /* Genre */

            if (registerBinding.femaleRadioButton.isChecked)
                genre = "Femenino"

            /* Favorite Genre */

            if (registerBinding.actionCheckBox.isChecked)
                favoritesGenre = "Accion"
            if (registerBinding.loveCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Amor"
            if (registerBinding.adventureCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Aventura"
            if (registerBinding.comicCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Comedia"
            if (registerBinding.dramaCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Drama"
            if (registerBinding.fantasyCheckBox.isChecked)
                favoritesGenre = favoritesGenre + " " + "Fantasia"



            if (email == "" || password == "" || genre == "" || cities == "" || fechaNacimiento == "") {
                Toast.makeText(this, "Falta ingresar algún elemento", Toast.LENGTH_LONG).show()
            }else {
                if (password == repPassword) {
                    val info = email + "\n" + password + "\n" + genre + "\n" + cities + "\n" + fechaNacimiento
                    registerBinding.infoTextView.text = info
                }else{
                    Toast.makeText(this,"Las contraseñas no son iguales", Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}



