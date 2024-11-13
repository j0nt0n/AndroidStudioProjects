package com.example.cafeorder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
// Заполнение макета этого фрагмента
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val startButton = view.findViewById<Button>(R.id.buttonCreateOrder)
        val nameView = view.findViewById<EditText>(R.id.editTextName)
        val passwordView = view.findViewById<EditText>(R.id.editTextPassword)
        startButton.setOnClickListener {
            val name = nameView.text.toString()
            val password = passwordView.text.toString()
            val action =
                LoginFragmentDirections.actionLoginFragmentToCreateOrderFragment(name, password)
            view.findNavController()
                .navigate(action)
        }

        return view
    }
}