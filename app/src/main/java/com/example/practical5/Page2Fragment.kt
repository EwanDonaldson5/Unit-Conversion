package com.example.practical5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.String.format
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

//Page Fragment 2 Class
class Page2Fragment : Fragment() {

    var unitA: String = ""
    var unitB: String = ""
    var category: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.page2_fragment, container, false)

        //create the buttons that work as a calculator
        val button1 = view.findViewById<Button>(R.id.val1Button)
        val button2 = view.findViewById<Button>(R.id.val2Button)
        val button3 = view.findViewById<Button>(R.id.val3Button)
        val button4 = view.findViewById<Button>(R.id.val4Button)
        val button5 = view.findViewById<Button>(R.id.val5Button)
        val button6 = view.findViewById<Button>(R.id.val6Button)
        val button7 = view.findViewById<Button>(R.id.val7Button)
        val button8 = view.findViewById<Button>(R.id.val8Button)
        val button9 = view.findViewById<Button>(R.id.val9Button)
        val button0 = view.findViewById<Button>(R.id.val0Button)
        val buttonEnter = view.findViewById<Button>(R.id.enterButton)
        val buttonClear = view.findViewById<Button>(R.id.clearButton)
        val buttonReverse = view.findViewById<Button>(R.id.reverseButton)

        var sum = 0.00//the variable that calculation
        var pressed: Int

        val output = view.findViewById<TextView>(R.id.outputTextView)//text view for displaying what unit conversion will take place
        val result = view.findViewById<TextView>(R.id.resultTextView)//text view for outputting the results from the conversion
        var input = ""

        unitA = arguments?.getString("input_txt").toString()//set the first unit to the given unit
        unitB = arguments?.getString("input2_txt").toString()//set the second unit to the given unit
        category = arguments?.getString("input3_txt").toString()
        if(output != null){//checks the output isn't null
            if(unitA == "" || unitB == ""){
                output.text = "You must choose units to make a conversion"//output that no units were selected
            }else {
                output.text = "Enter the " + unitA + " and it will return in " + unitB//output the conversion that will be executed
            }
        }

        //create the listeners for the calculator buttons
        button1?.setOnClickListener{
            pressed = 1//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button2?.setOnClickListener{
            pressed = 2//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button3?.setOnClickListener{
            pressed = 3//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button4?.setOnClickListener{
            pressed = 4//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button5?.setOnClickListener{
            pressed = 5//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button6?.setOnClickListener{
            pressed = 6//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button7?.setOnClickListener{
            pressed = 7//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button8?.setOnClickListener{
            pressed = 8//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button9?.setOnClickListener{
            pressed = 9//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        button0?.setOnClickListener{
            pressed = 0//set the pressed variable to the value of the button
            input = input + pressed.toString()//concatenate the previous pressed button with the newly pressed one
            result.text = input//update the textView to show the user what they have typed
        }
        buttonClear?.setOnClickListener{
            sum = 0.0
            input = ""
            result.text = ""
        }//resets the sum variable when clear button is pressed
        buttonReverse?.setOnClickListener{//listener reverses the units orientation to allow for calculations to go the other way
            var temp = unitA
            unitA = unitB
            unitB = temp
            //if statement updates the output textView with the swapped units
            if(output != null){//checks the output isn't null
                output.text = "Enter the " + unitA + " and it will return in " + unitB//output the conversion that will be executed
            }
            result.text = ""
        }
        buttonEnter?.setOnClickListener{//when the enter button is clicked
            if(input != ""){//if an input has been typed
                sum = input.toDouble()//set the sum to the users input

                //conversions between the different units from each category
                if(category == "Distance"){//if the distance category was selected
                    if(unitA == "Centimetre"){if(unitB == "Metre")     {sum = sum/100}}//if unitA is cm and unitB is m
                    if(unitA == "Centimetre"){if(unitB == "Inch")      {sum = sum/2.54}}//if unitA is cm and unitB is inch
                    if(unitA == "Metre")     {if(unitB == "Inch")      {sum = sum*39.37}}//if unitA is m and unitB is inch
                    if(unitA == "Metre")     {if(unitB == "Centimetre"){sum = sum*100}}//if unitA is m and unitB is cm
                    if(unitA == "Inch")      {if(unitB == "Centimetre"){sum = sum*2.54}}//if unitA is inch and unitB is cm
                    if(unitA == "Inch")      {if(unitB == "Metre")     {sum = sum/39.37}}//if unitA is inch and unitB is m
                }
                if(category == "Speed"){//if the speed category was selected
                    if(unitA == "KPH"){if(unitB == "MPH"){sum = sum/1.609}}//if unitA is kph and unitB is mph
                    if(unitA == "KPH"){if(unitB == "MPS"){sum = sum/3.6}}//if unitA is kph and unitB is mps
                    if(unitA == "MPH"){if(unitB == "MPS"){sum = sum/2.237}}//if unitA is mph and unitB is mps
                    if(unitA == "MPH"){if(unitB == "KPH"){sum = sum*1.609}}//if unitA is mph and unitB is kph
                    if(unitA == "MPS"){if(unitB == "KPH"){sum = sum*3.6}}//if unitA is mps and unitB is kph
                    if(unitA == "MPS"){if(unitB == "MPH"){sum = sum*2.237}}//if unitA is mps and unitB is mph
                }
                if(category == "Weight"){//if the weight category was selected
                    if(unitA == "Ounce")    {if(unitB == "Kilograms"){sum = sum/35.274}}//if unitA is Ounce and unitB is Kilograms
                    if(unitA == "Ounce")    {if(unitB == "Grams")    {sum = sum*28.35}}//if unitA is Ounce and unitB is Grams
                    if(unitA == "Kilograms"){if(unitB == "Grams")    {sum = sum*1000}}//if unitA is Kilograms and unitB is Grams
                    if(unitA == "Kilograms"){if(unitB == "Ounce")    {sum = sum*35.274}}//if unitA is Kilograms and unitB is Ounce
                    if(unitA == "Grams")    {if(unitB == "Ounce")    {sum = sum/28.35}}//if unitA is Grams and unitB is Ounce
                    if(unitA == "Grams")    {if(unitB == "Kilograms"){sum = sum/1000}}//if unitA is Grams and unitB is Kilograms
                }
                if(category == "Time"){//if the time category was selected
                    if(unitA == "Seconds"){if(unitB == "Minutes"){sum = sum/60}}//if unitA is Seconds and unitB is Minutes
                    if(unitA == "Seconds"){if(unitB == "Hours")  {sum = sum/3600}}//if unitA is Seconds and unitB is Hours
                    if(unitA == "Minutes"){if(unitB == "Hours")  {sum = sum/60}}//if unitA is Minutes and unitB is Hours
                    if(unitA == "Minutes"){if(unitB == "Seconds"){sum = sum*60}}//if unitA is Minutes and unitB is Seconds
                    if(unitA == "Hours")  {if(unitB == "Seconds"){sum = sum*3600}}//if unitA is Hours and unitB is Seconds
                    if(unitA == "Hours")  {if(unitB == "Minutes"){sum = sum*60}}//if unitA is Hours and unitB is Minutes
                }
                if(category == "Temperature"){//if the time category was selected
                    if(unitA == "Celsius")   {if(unitB == "Fahrenheit"){sum = ((sum*9/5)+32)}}//if unitA is Celsius and unitB is Fahrenheit
                    if(unitA == "Celsius")   {if(unitB == "Kelvin")    {sum = sum + 273.15}}//if unitA is Celsius and unitB is Kelvin
                    if(unitA == "Fahrenheit"){if(unitB == "Kelvin")    {sum = ((sum-32)*(5/9))+273.15}}//if unitA is Fahrenheit and unitB is Kelvin
                    if(unitA == "Fahrenheit"){if(unitB == "Celsius")   {sum = ((sum-32)*(5/9))}}//if unitA is Fahrenheit and unitB is Celsius
                    if(unitA == "Kelvin")    {if(unitB == "Celsius")   {sum = sum - 273.15}}//if unitA is Kelvin and unitB is Celsius
                    if(unitA == "Kelvin")    {if(unitB == "Fahrenheit"){sum = (((sum-273.15)*9/5)+32)}}//if unitA is Kelvin and unitB is Fahrenheit
                }

                val df = DecimalFormat("#.###")//create decimal format for rounding values
                df.roundingMode = RoundingMode.CEILING//sets the deciaml format to round upwards
                println(df.format(sum))
                result.text = sum.toString() + unitB//output the answer
                sum = 0.0//reset the sum variable
                input = ""//reset the input variable
            }else{result.text = "No values have been typed"}//if there was no input then output error message
        }

        return view
    }
}