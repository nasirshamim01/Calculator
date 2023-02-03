package com.nasirshamim.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder



class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //For Numbers
        val tvOne = findViewById<TextView>(R.id.tvOne)
        val tvTwo = findViewById<TextView>(R.id.tvTwo)
        val tvThree = findViewById<TextView>(R.id.tvThree)
        val tvFour = findViewById<TextView>(R.id.tvFour)
        val tvFive = findViewById<TextView>(R.id.tvFive)
        val tvSix = findViewById<TextView>(R.id.tvSix)
        val tvSeven = findViewById<TextView>(R.id.tvSeven)
        val tvEight = findViewById<TextView>(R.id.tvEight)
        val tvNine = findViewById<TextView>(R.id.tvNine)
        val tvZero = findViewById<TextView>(R.id.tvZero)
        val tvDot = findViewById<TextView>(R.id.tvDot)

        tvOne.setOnClickListener { appendOnExpresstion("1", true) }
        tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
        tvThree.setOnClickListener { appendOnExpresstion("3", true) }
        tvFour.setOnClickListener { appendOnExpresstion("4", true) }
        tvFive.setOnClickListener { appendOnExpresstion("5", true) }
        tvSix.setOnClickListener { appendOnExpresstion("6", true) }
        tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
        tvEight.setOnClickListener { appendOnExpresstion("8", true) }
        tvNine.setOnClickListener { appendOnExpresstion("9", true) }
        tvZero.setOnClickListener { appendOnExpresstion("0", true) }
        tvDot.setOnClickListener { appendOnExpresstion(".", true) }

        //For Operators
        val tvPlus = findViewById<TextView>(R.id.tvPlus)
        val tvMinus = findViewById<TextView>(R.id.tvMinus)
        val tvMul = findViewById<TextView>(R.id.tvMul)
        val tvDivide = findViewById<TextView>(R.id.tvDivide)
        val tvOpen = findViewById<TextView>(R.id.tvOpen)
        val tvClose = findViewById<TextView>(R.id.tvClose)

        tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
        tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
        tvMul.setOnClickListener { appendOnExpresstion("*", false) }
        tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
        tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        tvClose.setOnClickListener { appendOnExpresstion(")", false) }

        val tvClear = findViewById<TextView>(R.id.tvClear)
        val tvBack = findViewById<TextView>(R.id.tvBack)
        val tvEquals = findViewById<TextView>(R.id.tvEquals)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvExpression = findViewById<TextView>(R.id.tvExpression)
        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evalute()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text=longResult.toString()
                else
                    tvResult.text=result.toString()
            }catch (e:Exception){
            Log.d("Exception","message : " + e.message)
            }
        }

    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvExpression = findViewById<TextView>(R.id.tvExpression)
        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}