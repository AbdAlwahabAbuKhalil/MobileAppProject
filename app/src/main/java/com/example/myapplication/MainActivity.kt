package com.example.myapplication

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add:Button=findViewById(R.id.addBtn)
        add.setOnClickListener(){
            onClickAddName()
        }
    }



//    val _ID="_id"
//    val NAME="name"
//    val PAGES="pages"
//    val AUTHOR="author"
//    val PRICE="price"
//    val GENRE="genre"
    fun onClickAddName() {

        val values = ContentValues()
        values.put(
            Book.NAME,
            "Book1"

        )
        values.put(
            Book.PAGES,
           255
        )
        values.put(
            Book.AUTHOR,
            "AUTHOR1"
        )

    values.put(
        Book.PRICE,
       25
    )
    values.put(
        Book.GENRE,
        "ACTION"
    )
        val uri = contentResolver.insert(
            Book.CONTENT_URI, values
        )
        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()


    }

}