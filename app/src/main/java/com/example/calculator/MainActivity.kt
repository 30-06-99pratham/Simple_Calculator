package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tv_input:TextView? = null
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_input = findViewById(R.id.tv_input)

    }

    fun OnDigit(view: View){
        tv_input?.append((view as Button).text)
        lastNumeric = true

    }
    fun OnClear(view: View){
        tv_input?.text = ""
    }

    fun OnDecimal(view: View){
        if(lastNumeric && !lastDot){
            tv_input?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tv_input?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix= "-"
                    tvValue = tvValue.substring(1)
                }
                else if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tv_input?.text = removeZerosAfterDecimal((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tv_input?.text = removeZerosAfterDecimal((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tv_input?.text = removeZerosAfterDecimal((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    tv_input?.text = removeZerosAfterDecimal((one.toDouble() * two.toDouble()).toString())
                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    fun removeZerosAfterDecimal(res:String):String{
        var value = res
        if(res.contains(".0")){
            value = res.substring(0,res.length-2)

        }
        return value
    }

    fun onOperator(view:View){
        tv_input?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tv_input?.append((view as Button).text)
                lastNumeric = false
                lastDot = false

            }
        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+")
                    || value.contains("*")
                    || value.contains("/")
                    || value.contains("-")
        }
    }

}