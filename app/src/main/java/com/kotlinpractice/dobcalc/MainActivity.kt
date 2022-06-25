package com.kotlinpractice.dobcalc
import android.app.DatePickerDialog
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView? =null
    private var tvSelectedDateInMinutes:TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker :Button= findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate);
        tvSelectedDateInMinutes=findViewById(R.id.tvDateInMinutes);



       private fun clickDatePicker(){
            print("messgage")
            val myCalender = Calendar.getInstance()
            val year= myCalender.get(Calendar.YEAR)
            val monthValue= myCalender.get(Calendar.MONTH)
            val day= myCalender.get(Calendar.DAY_OF_MONTH);
            val dbd= DatePickerDialog(this,{view, year, month, dayOfMonth ->
                Toast.makeText(this,"Date Picker click listener year is $year and $month", Toast.LENGTH_LONG).show()
                val selectedDate= "$dayOfMonth/${month+1}/$year"
                tvSelectedDate?.setText(selectedDate)
                val sdf =SimpleDateFormat("dd/MM/yyy",Locale.ENGLISH)
                val theDate =sdf.parse(selectedDate)
                theDate?.let{
                    val selectedDateInMinutes =theDate.time/ 60000
                    val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes= currentDate.time/60000
                        val differenceInMinutes= currentDateInMinutes-selectedDateInMinutes
                        tvSelectedDateInMinutes?.text=(differenceInMinutes).toString()
                    }

                }



            },year, monthValue,day)
         dbd.datePicker.maxDate = System.currentTimeMillis() -86400000
            dbd.show()
        }
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
}