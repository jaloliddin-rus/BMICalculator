package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Gravity
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener(){
            //todo: invoke a function to calculate bmi
            //todo: display image and bmi result

            try {
                val height = editTextHeight.text.toString().toDouble()
                val weight = editTextWeight.text.toString().toDouble()


            val bmi = calcBmi(height, weight)

            hideSoftKeyboard()

            if(bmi < 18.5){
                imgBody.setImageResource(R.drawable.under)
                txtResult.setText("Your BMI is %.2f".format(bmi) + " (Underweight)")
                val toast = Toast.makeText(applicationContext, "UNDERWEIGHT", Toast.LENGTH_LONG).show()
            }
            else if(bmi < 24.9 ){
                imgBody.setImageResource(R.drawable.normal)
                txtResult.setText("Your BMI is %.2f".format(bmi) + " (Normal)")
                val toast = Toast.makeText(applicationContext, "NORMAL", Toast.LENGTH_LONG).show()
            }
            else{
                imgBody.setImageResource(R.drawable.over)
                txtResult.setText("Your BMI is %.2f".format(bmi) + " (Overweight)")
                val toast = Toast.makeText(applicationContext, "OVERWEIGHT", Toast.LENGTH_LONG).show()
            }


            }catch (ex: NumberFormatException){
                val toast : Toast = Toast.makeText(applicationContext,"Please enter values!", Toast.LENGTH_LONG)

                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

        }

        btnReset.setOnClickListener(){
            //todo: clear inputs and result
            //todo: clear image

            editTextHeight.setText(null)
            editTextWeight.setText(null)
            txtResult.setText(null)
            imgBody.setImageResource(0)

            editTextHeight.setFocusable(true)
        }
    }

    fun calcBmi(height : Double, weight : Double):Double{
        return weight/(height*height)
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

}
