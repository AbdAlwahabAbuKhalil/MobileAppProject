package com.example.project

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Book
import com.example.myapplication.R

class Login : DialogFragment(R.layout.login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.backimg)
        imageView.setBackgroundResource(R.drawable.background_image)



        val backbtn: Button = view.findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            dismiss() // Dismiss the dialog
            requireActivity().supportFragmentManager.popBackStack() // Pop the fragment from the back stack
        }

        val add:Button=view.findViewById(R.id.insertbtn)
        add.setOnClickListener(){
            onClickAddName()
        }



    }

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