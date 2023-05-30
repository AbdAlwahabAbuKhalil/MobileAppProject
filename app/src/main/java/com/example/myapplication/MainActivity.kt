package com.example.myapplication

import android.content.*
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.project.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView: ImageView = findViewById(R.id.backimg);
        imageView.setBackgroundResource(R.drawable.background_image)
        val nunitoFont: Typeface = Typeface.createFromAsset(assets, "fonts/nunito_semibold_italic.ttf")
        val address1TextView: TextView = findViewById(R.id.desctxt)
        address1TextView.typeface = nunitoFont
        val insertbtn:Button = findViewById(R.id.insertbtn)
        val showBtn:Button=findViewById(R.id.registerbtn)
        val login = Login()
        val show=Show()

        insertbtn.setOnClickListener{
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, login)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            startService()
            //stopService()
        }

        showBtn.setOnClickListener{
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, show)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        serviceIntent.putExtra("inputExtra", "Welcome!, You can add a book to the library now!")
        ContextCompat.startForegroundService(this, serviceIntent)
    }
    fun stopService() {
           val serviceIntent = Intent(this, MyService::class.java)
           stopService(serviceIntent)
    }


}