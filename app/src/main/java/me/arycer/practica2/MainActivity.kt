package me.arycer.practica2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamar por teléfono
        findViewById<Button>(R.id.btnCall).setOnClickListener {
            makePhoneCall("1234567890")
        }

        // Crear alarma
        findViewById<Button>(R.id.btnAlarm).setOnClickListener {
            createAlarm("Despierta", 7, 30)
        }

        // Abrir una URL
        findViewById<Button>(R.id.btnUrl).setOnClickListener {
            openUrl("https://arycer.me")
        }

        // Enviar SMS
        findViewById<Button>(R.id.btnSms).setOnClickListener {
            sendSms("1234567890", "Hola, este es un mensaje de prueba.")
        }
    }

    private fun sendSms(phoneNumber: String, message: String) {
        Intent(Intent.ACTION_SENDTO).apply {
            this.data = Uri.parse("smsto:$phoneNumber")
            this.putExtra(Intent.EXTRA_TEXT, message)
        }.also {
            startActivity(it)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun createAlarm(description: String, hour: Int, minute: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, description)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
        }

        startActivity(intent)
    }

    private fun makePhoneCall(phoneNumber: String) {
        Intent(Intent.ACTION_DIAL).apply {
            this.setData(Uri.parse("tel:$phoneNumber"))
        }.also {
            startActivity(it)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}