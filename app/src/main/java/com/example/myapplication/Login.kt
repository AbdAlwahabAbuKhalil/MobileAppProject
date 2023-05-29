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
import androidx.appcompat.app.AppCompatActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.provider.AlarmClock

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
            Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_LONG).show()


            //to add an alarm
            val h: EditText = view.findViewById(R.id.hoursnum)
            val m: EditText = view.findViewById(R.id.minutesnum)
            var hour: Int = h.text.toString().toIntOrNull() ?: -1
            var minutes: Int = m.text.toString().toIntOrNull() ?: -1

            //this is to check if the alarm hour value is valid (0-23)
            if (hour < 0 || hour > 23) {
                h.backgroundTintList = ColorStateList.valueOf(Color.RED)
            } else {
                h.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            }
            //this is to chheck if the alarm minute value is valid (0-59)
            if (minutes < 0 || minutes > 59) {
                m.backgroundTintList = ColorStateList.valueOf(Color.RED)
            } else {
                m.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            }
            //both should be valid
            if (hour in 0..23 && minutes in 0..59) {
                val mess: String = "READ THE BOOK: " + name.text.toString() + " !!"
                createAlarm(mess, hour, minutes)
            }


            //going back to main page after adding the book
            dismiss() // Dismiss the dialog
            requireActivity().supportFragmentManager.popBackStack() // Pop the fragment from the back stack

        }
    }

    //function to create the alarm
    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }




}


