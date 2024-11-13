package com.example.cafeorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class CreateOrderFragment : Fragment() {

    private val args: CreateOrderFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_order, container, false)

        // Приветствие
        val hello = getString(R.string.hello_user, args.name)
        val textViewHello = view.findViewById<TextView>(R.id.textViewHello)
        textViewHello.text = hello

        // Выбор типа напитка и управление видимостью элементов
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val spinnerTea = view.findViewById<Spinner>(R.id.spinnerTea)
        val spinnerCoffee = view.findViewById<Spinner>(R.id.spinnerCoffee)
        val checkBoxMilk = view.findViewById<CheckBox>(R.id.checkBoxMilk)
        val checkBoxSugar = view.findViewById<CheckBox>(R.id.checkBoxSugar)
        val checkBoxLemon = view.findViewById<CheckBox>(R.id.checkBoxLemon)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val drink = when (checkedId) {
                R.id.radioButtonTea -> {
                    spinnerTea.visibility = View.VISIBLE
                    spinnerCoffee.visibility = View.INVISIBLE
                    checkBoxLemon.visibility = View.VISIBLE
                    getString(R.string.tea)
                }
                else -> {
                    spinnerTea.visibility = View.INVISIBLE
                    spinnerCoffee.visibility = View.VISIBLE
                    checkBoxLemon.visibility = View.INVISIBLE
                    getString(R.string.coffee)
                }
            }
            val textViewAdditions = view.findViewById<TextView>(R.id.textViewAdditions)
            textViewAdditions.text = getString(R.string.additions, drink)
        }

        // Обработка нажатия кнопки "Сделать заказ"
        val buttonSendOrder = view.findViewById<AppCompatImageButton>(R.id.buttonSendOrder)
        buttonSendOrder.setOnClickListener {
            // Определение выбранного напитка
            val drink = when (radioGroup.checkedRadioButtonId) {
                R.id.radioButtonTea -> getString(R.string.tea)
                else -> getString(R.string.coffee)
            }

            // Определение выбранных добавок
            var additions = ""
            if (checkBoxMilk.isChecked) {
                additions += getString(R.string.milk) + " "
            }
            if (checkBoxSugar.isChecked) {
                additions += getString(R.string.sugar) + " "
            }
            if (checkBoxLemon.isChecked && drink == getString(R.string.tea)) {
                additions += getString(R.string.lemon) + " "
            }

            // Формирование строки с добавками
            val additionsList = if (additions.isNotEmpty()) {
                getString(R.string.need_additions) + additions
            } else {
                ""
            }

            // Определение выбранного вида чая/кофе
            val optionOfDrink = if (drink == getString(R.string.tea)) {
                spinnerTea.selectedItem.toString()
            } else {
                spinnerCoffee.selectedItem.toString()
            }

            // Формирование общей строки с параметрами заказа
            val order = String.format(getString(R.string.order), args.name, args.password, drink, optionOfDrink)

            val fullOrder = order + additionsList
            val action = CreateOrderFragmentDirections.actionCreateOrderFragmentToOrderFragment(fullOrder)
            findNavController().navigate(action)
        }

        return view
    }
}