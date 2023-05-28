package com.example.project

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.net.Uri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Book
import android.content.*
import android.widget.*
import com.example.myapplication.R

class Login : DialogFragment(R.layout.login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.backimg)
        imageView.setBackgroundResource(R.drawable.background_image)


        val name: EditText = view.findViewById(R.id.nametxt)
        val pages:EditText = view.findViewById(R.id.pagesnum)
        val author:EditText = view.findViewById(R.id.authortxt)
        val price:EditText = view.findViewById(R.id.pricenum)
        val genre:EditText = view.findViewById(R.id.genretxt)


        val backbtn: Button = view.findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            dismiss() // Dismiss the dialog
            requireActivity().supportFragmentManager.popBackStack() // Pop the fragment from the back stack
        }

        val add:Button=view.findViewById(R.id.insertbtn)
        add.setOnClickListener(){
            //onClickAddName()
            val contentResolver = requireContext().contentResolver


            val values = ContentValues()
            values.put(Book.NAME,name.text.toString() )
            values.put(Book.PAGES, pages.text.toString().toInt())
            values.put(Book.AUTHOR, author.text.toString())
            values.put(Book.PRICE, price.text.toString().toInt())
            values.put(Book.GENRE, genre.text.toString())

            val uri = contentResolver.insert(Book.CONTENT_URI, values)
            Toast.makeText(requireContext(), uri.toString(), Toast.LENGTH_LONG).show()

        }
        }



    }

    fun onClickAddName() {

        val contentResolver = requireContext().contentResolver


        val values = ContentValues()
        values.put(Book.NAME, )
        values.put(Book.PAGES, 255)
        values.put(Book.AUTHOR, "AUTHOR1")
        values.put(Book.PRICE, 25)
        values.put(Book.GENRE, "ACTION")

        val uri = contentResolver.insert(Book.CONTENT_URI, values)
        Toast.makeText(requireContext(), uri.toString(), Toast.LENGTH_LONG).show()

    }

}