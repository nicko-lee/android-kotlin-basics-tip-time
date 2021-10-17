package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // Get the user entered cost
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        // Get the tip percentage
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // Calculate the tip and round it up if user toggled Round Up option
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }

        // Format the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Display the tip to user
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}