package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class Show: DialogFragment(R.layout.showpage), AdapterView.OnItemClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.backimg)
        imageView.setBackgroundResource(R.drawable.background_image)

        // Assuming you have a ListView with the ID "listView" in your layout
        val listView: ListView = view.findViewById(R.id.listBooks)
        val URL="content://com.example.MyApplication.Book"
        val books= Uri.parse(URL)
        listView.onItemClickListener = this



        val cursor=requireContext().contentResolver.query(books,null,null,null,null)


        val columns = arrayOf(Book.NAME)

// Define an array to store the data
        val data: ArrayList<String> = ArrayList()

// Iterate over the cursor and extract the data
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val rowData = cursor.getString(cursor.getColumnIndexOrThrow(Book.NAME))
                    data.add(rowData)
                } while (cursor.moveToNext())
            }
        }

// Create a custom adapter to bind the data to the ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)

// Set the adapter to the ListView
        listView.adapter = adapter


        //Search view(how to search the book name)
        val searchView: SearchView = view.findViewById(R.id.searchBooks)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                val q = newText?.trim() ?: ""
                val selection = "${Book.NAME} LIKE ?"
                val selectionArgs = arrayOf("%$q%")
                val sortOrder = Book.NAME

                val cursor = requireContext().contentResolver.query(
                    Book.CONTENT_URI,
                    columns,
                    selection,
                    selectionArgs,
                    sortOrder
                )

                val data: ArrayList<String> = ArrayList()
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val nameIndex = cursor.getColumnIndexOrThrow(Book.NAME)
                        val name = cursor.getString(nameIndex)
                        data.add(name)
                    }
                }

                // Update the adapter data
                adapter.clear()
                adapter.addAll(data)
                adapter.notifyDataSetChanged()

                return true
            }

            override fun onQueryTextSubmit(q: String): Boolean {
                return  false
            }
        })


    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedItem = p0?.getItemAtPosition(p2) as String

        // Perform any necessary actions with the selected item
        // ...

        // Create an instance of your update dialog fragment and pass the selected item (book name)
        val updateDialogFragment = Update.newInstance(selectedItem)

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, updateDialogFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}