package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Book


class Update : DialogFragment(R.layout.update) {
    companion object {
        fun newInstance(bookName: String): Update {
            val fragment = Update()
            val args = Bundle()
            args.putString("bookName", bookName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.backimg)
        imageView.setBackgroundResource(R.drawable.background_image)

        val bookName = arguments?.getString("bookName")
        val test: TextView = view.findViewById(R.id.test)
        test.text = bookName

        val cursor = requireContext().contentResolver.query(
            Book.CONTENT_URI,
            null,
            "${Book.NAME} = ?",
            arrayOf(bookName),
            null
        )





    }


}