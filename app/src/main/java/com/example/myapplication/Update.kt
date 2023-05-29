package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment


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
        val test: TextView = view.findViewById(R.id.nametxtu)
        test.text = bookName

        val cursor = requireContext().contentResolver.query(
            Book.CONTENT_URI,
            null,
            "${Book.NAME} = ?",
            arrayOf(bookName),
            null
        )

            val pagesTextView: EditText = view.findViewById(R.id.pagesnumu)
            val authorTextView: EditText = view.findViewById(R.id.authortxtu)
            val priceTextView: EditText = view.findViewById(R.id.pricenumu)
            val genreTextView: EditText = view.findViewById(R.id.genretxtu)
        if (cursor != null && cursor.moveToFirst()) {
            val pages = cursor.getInt(cursor.getColumnIndexOrThrow(Book.PAGES))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(Book.AUTHOR))
            val price = cursor.getFloat(cursor.getColumnIndexOrThrow(Book.PRICE))
            val genre = cursor.getString(cursor.getColumnIndexOrThrow(Book.GENRE))

            // Use the retrieved attributes as needed
            // Example:

            pagesTextView.setText("$pages")
            authorTextView.setText(author)
            priceTextView.setText("$price")
            genreTextView.setText(genre)
        }
        cursor?.close()

        val updateButton: Button = view.findViewById(R.id.updatebtn)
        updateButton.setOnClickListener {
            // Retrieve the new values from the EditText views
            val newPages = pagesTextView.text.toString().toInt()
            val newAuthor = authorTextView.text.toString()
            val newPrice = priceTextView.text.toString().toDouble()
            val newGenre = genreTextView.text.toString()

            // Update the corresponding values in the database
            val contentValues = ContentValues()
            contentValues.put(Book.PAGES, newPages)
            contentValues.put(Book.AUTHOR, newAuthor)
            contentValues.put(Book.PRICE, newPrice)
            contentValues.put(Book.GENRE, newGenre)

            requireContext().contentResolver.update(
                Book.CONTENT_URI,
                contentValues,
                "${Book.NAME} = ?",
                arrayOf(bookName)
            )

            // Close the dialog fragment
            dismiss()
        }

    }


}