package com.example.myyy


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CheckboxFragment : Fragment() {



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_checkbox, container, false)
        val sublast : Button = view.findViewById(R.id.submitlast)

        sublast.setOnClickListener {
            navigateToNewActivity()

        }


        return view
    }

    fun navigateToNewActivity() {
        val intent = Intent(requireContext(), MapnewActivity::class.java)
        startActivity(intent)
    }


}