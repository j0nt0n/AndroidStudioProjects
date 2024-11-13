package com.example.cafeorder

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class OrderFragment : Fragment() {

    // Получаем аргументы с помощью navArgs()
    private val args: OrderFragmentArgs by navArgs()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Используем правильный макет для этого фрагмента
        val view = inflater.inflate(R.layout.fragment_order_detail, container, false)

        // Получение и отображение строки fullOrder в TextView
        val textViewOrder = view.findViewById<TextView>(R.id.textViewOrder)
        textViewOrder.text = args.fullOrder // Теперь аргумент доступен

        return view
    }
}