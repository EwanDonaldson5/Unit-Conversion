package com.example.practical5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.Fragment


//Page Fragment 1 Class
class Page1Fragment : Fragment() {

    lateinit var comm: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.page1_fragment, container, false)

        comm = activity as Communicator

        //initialise the button and checkboxes that make up the distance category
        val distanceButton = view.findViewById<Button>(R.id.distanceButton)//create value for the distance button
        val centimetreCheckBox = view.findViewById<CheckBox>(R.id.centimetreCheckBox)//create the checkbox for the centimetre in distance
        val metreCheckBox = view.findViewById<CheckBox>(R.id.metreCheckBox)//create the checkbox for the metre in distance
        val inchCheckBox = view.findViewById<CheckBox>(R.id.inchCheckBox)//create the checkbox for the inch in distance

        //initialise the button and checkboxes that make up the speed category
        val speedButton = view.findViewById<Button>(R.id.speedButton)//create value for the distance button
        val kphCheckBox = view.findViewById<CheckBox>(R.id.kphCheckBox)//create the checkbox for the centimetre in distance
        val mphCheckBox = view.findViewById<CheckBox>(R.id.mphCheckBox)//create the checkbox for the metre in distance
        val mpsCheckBox = view.findViewById<CheckBox>(R.id.mpsCheckBox)//create the checkbox for the inch in distance

        //initialise the button and checkboxes that make up the weight category
        val weightButton = view.findViewById<Button>(R.id.weightButton)//create value for the weight button
        val ozCheckBox = view.findViewById<CheckBox>(R.id.ozCheckBox)//create the checkbox for the centimetre in distance
        val kgCheckBox = view.findViewById<CheckBox>(R.id.kgCheckBox)//create the checkbox for the metre in distance
        val gCheckBox = view.findViewById<CheckBox>(R.id.gramCheckBox)//create the checkbox for the inch in distance

        //initialise the button and checkboxes that make up the time category
        val timeButton = view.findViewById<Button>(R.id.timeButton)//create value for the distance button
        val secCheckBox = view.findViewById<CheckBox>(R.id.secCheckBox)//create the checkbox for the centimetre in distance
        val minCheckBox = view.findViewById<CheckBox>(R.id.minCheckBox)//create the checkbox for the metre in distance
        val hrCheckBox = view.findViewById<CheckBox>(R.id.hrCheckBox)//create the checkbox for the inch in distance

        //initialise the button and checkboxes that make up the time category
        val temperatureButton = view.findViewById<Button>(R.id.temperatureButton)//create value for the distance button
        val celsiusCheckBox = view.findViewById<CheckBox>(R.id.celsiusCheckBox)//create the checkbox for the centimetre in distance
        val fahrenheitCheckBox = view.findViewById<CheckBox>(R.id.fahrenheitCheckBox)//create the checkbox for the metre in distance
        val kelvinCheckBox = view.findViewById<CheckBox>(R.id.kelvinCheckBox)//create the checkbox for the inch in distance

        //initialise the textViews used to display the users current selection
        val categoryTextView = view.findViewById<TextView>(R.id.categoryTextView)//is a textView that updates to display the chosen category
        val unitsTextView = view.findViewById<TextView>(R.id.unitsTextView)//is a textView that updates to display the first chosen units from within the category
        val unitsTextView2 = view.findViewById<TextView>(R.id.unitsTextView2)//is a textView that updates to display the second chosen units from within the category

        val categories = arrayOf("Distance", "Speed", "Weight", "Time", "Temperature")//array holds all the choices available in terms of categories
        var chosenCategory: String//variable holds the currently chosen category
        chosenCategory = "Distance"//sets a default value in the variable
        var chosenUnits = arrayOf("","")//array that holds the chosen units



        //listener for the distance button
        distanceButton?.setOnClickListener {//if the distance button is clicked
            chosenCategory = categories[0]//set chosen category to distance from the categories array
            categoryTextView.text = chosenCategory//set the textView to the chosen category

            chosenUnits[0] = ""//resets the chosen units
            chosenUnits[1] = ""//resets the value


            if(centimetreCheckBox.isChecked){//if centimetre has been checked
                chosenUnits[0] = "Centimetre"//set the first chosen unit to centimetre
                if(metreCheckBox.isChecked){//if metre has also been checked
                    chosenUnits[1] = "Metre"//set the second chosen unit to metre
                    if(inchCheckBox.isChecked){//if inch was also checked then set it to false (only two boxes can be checked at one time)
                        inchCheckBox.isChecked = false//set checkbox to false
                    }
                }else if(inchCheckBox.isChecked){//if inch is the second selected checkbox
                    chosenUnits[1] = "Inch"//set second chosen unit to inch
                }
            }else if(metreCheckBox.isChecked){//if metre box is checked
                chosenUnits[0] = "Metre"//set first chosen unit to metre
                if(inchCheckBox.isChecked){//if inch check box is checked
                    chosenUnits[1] = "Inch"//set second chosen unit to inch
                }
            }else if(inchCheckBox.isChecked){//if inch check box is checked
                chosenUnits[0] = "Inch"//set first chosen unit to inch
            }
            unitsTextView.text = chosenUnits[0]//set the text view to the first chosen unit
            unitsTextView2.text = chosenUnits[1]//set the second text view to the second chosen unit

            comm.passDataCom(chosenUnits[0],chosenUnits[1],chosenCategory)//submit the chosen/verified units to the main activity
        }
        //listener for the second check box
        centimetreCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Centimetre"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Centimetre"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(metreCheckBox.isChecked && inchCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                centimetreCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Centimetre"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Centimetre"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Centimetre"//set the second textView to Hours
                }
            }
            distanceButton.callOnClick()
        }
        //listener for the minute check box
        metreCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Metre"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Metre"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(centimetreCheckBox.isChecked && inchCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                metreCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Metre"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Metre"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Metre"//set the second textView to Hours
                }
            }
            distanceButton.callOnClick()
        }
        //listener for the hour check box
        inchCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Inch"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Inch"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(metreCheckBox.isChecked && centimetreCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                inchCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Inch"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Inch"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Inch"//set the second textView to Hours
                }
            }
            distanceButton.callOnClick()
        }

        //listener for the speed button
        speedButton?.setOnClickListener {
            chosenCategory = categories[1]//set chosen category to speed from the categories array
            categoryTextView.text = chosenCategory//set the textView to the chosen category

            chosenUnits[0] = ""//resets the value
            chosenUnits[1] = ""//resets the value

            if(kphCheckBox.isChecked){//if centimetre has been checked
                chosenUnits[0] = "KPH"//set the first chosen unit to centimetre
                if(mphCheckBox.isChecked){//if metre has also been checked
                    chosenUnits[1] = "MPH"//set the second chosen unit to metre
                    if(mpsCheckBox.isChecked){//if inch was also checked then set it to false (only two boxes can be checked at one time)
                        mpsCheckBox.isChecked = false//set checkbox to false
                    }
                }else if(mpsCheckBox.isChecked){//if inch is the second selected checkbox
                    chosenUnits[1] = "MPS"//set second chosen unit to inch
                }
            }else if(mphCheckBox.isChecked){//if metre box is checked
                chosenUnits[0] = "MPH"//set first chosen unit to metre
                if(mpsCheckBox.isChecked){//if inch check box is checked
                    chosenUnits[1] = "MPS"//set second chosen unit to inch
                }
            }else if(mpsCheckBox.isChecked){//if inch check box is checked
                chosenUnits[0] = "MPS"//set first chosen unit to inch
            }
            unitsTextView.text = chosenUnits[0]//set the text view to the first chosen unit
            unitsTextView2.text = chosenUnits[1]//set the second text view to the second chosen unit

            comm.passDataCom(chosenUnits[0],chosenUnits[1],chosenCategory)//submit the chosen/verified units to the main activity
        }
        //listener for the second check box
        kphCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "KPH"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "KPH"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(mphCheckBox.isChecked && mpsCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                kphCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "KPH"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "KPH"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "KPH"//set the second textView to Hours
                }
            }
            speedButton.callOnClick()
        }
        //listener for the minute check box
        mphCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "MPH"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "MPH"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(kphCheckBox.isChecked && mpsCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                mphCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "MPH"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "MPH"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "MPH"//set the second textView to Hours
                }
            }
            speedButton.callOnClick()
        }
        //listener for the hour check box
        mpsCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "MPS"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "MPS"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(mphCheckBox.isChecked && kphCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                mpsCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "MPS"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "MPS"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "MPS"//set the second textView to Hours
                }
            }
            speedButton.callOnClick()
        }

        //listener for the weight button
        weightButton?.setOnClickListener {
            chosenCategory = categories[2]//set chosen category to weight from the categories array
            categoryTextView.text = chosenCategory//set the textView to the chosen category

            chosenUnits[0] = ""//resets the value
            chosenUnits[1] = ""//resets the value

            if(ozCheckBox.isChecked){//if centimetre has been checked
                chosenUnits[0] = "Ounces"//set the first chosen unit to centimetre
                if(kgCheckBox.isChecked){//if metre has also been checked
                    chosenUnits[1] = "Kilograms"//set the second chosen unit to metre
                    if(gCheckBox.isChecked){//if inch was also checked then set it to false (only two boxes can be checked at one time)
                        gCheckBox.isChecked = false//set checkbox to false
                    }
                }else if(gCheckBox.isChecked){//if inch is the second selected checkbox
                    chosenUnits[1] = "Grams"//set second chosen unit to inch
                }
            }else if(kgCheckBox.isChecked){//if metre box is checked
                chosenUnits[0] = "Kilograms"//set first chosen unit to metre
                if(gCheckBox.isChecked){//if inch check box is checked
                    chosenUnits[1] = "Grams"//set second chosen unit to inch
                }
            }else if(gCheckBox.isChecked){//if inch check box is checked
                chosenUnits[0] = "Grams"//set first chosen unit to inch
            }
            unitsTextView.text = chosenUnits[0]//set the text view to the first chosen unit
            unitsTextView2.text = chosenUnits[1]//set the second text view to the second chosen unit

            comm.passDataCom(chosenUnits[0],chosenUnits[1],chosenCategory)//submit the chosen/verified units to the main activity
        }
        //listener for the second check box
        ozCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Ounce"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Ounce"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(kgCheckBox.isChecked && gCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                ozCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Ounce"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Ounce"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Ounce"//set the second textView to Hours
                }
            }
            weightButton.callOnClick()
        }
        //listener for the minute check box
        kgCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Kilograms"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Kilograms"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(ozCheckBox.isChecked && gCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                kgCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Kilograms"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Kilograms"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Kilograms"//set the second textView to Hours
                }
            }
            weightButton.callOnClick()
        }
        //listener for the hour check box
        gCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Grams"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Grams"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(kgCheckBox.isChecked && ozCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                gCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Grams"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Grams"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Grams"//set the second textView to Hours
                }
            }
            weightButton.callOnClick()
        }

        //listener for the time button
        timeButton?.setOnClickListener {
            chosenCategory = categories[3]//set chosen category to time from the categories array
            categoryTextView.text = chosenCategory//set the textView to the chosen category

            chosenUnits[0] = ""//resets the value
            chosenUnits[1] = ""//resets the value

            if(secCheckBox.isChecked){//if centimetre has been checked
                chosenUnits[0] = "Seconds"//set the first chosen unit to centimetre
                if(minCheckBox.isChecked){//if metre has also been checked
                    chosenUnits[1] = "Minutes"//set the second chosen unit to metre
                    //if(hrCheckBox.isChecked){//if inch was also checked then set it to false (only two boxes can be checked at one time)
                    //    hrCheckBox.isChecked = false//set checkbox to false
                    //}
                }else if(hrCheckBox.isChecked){//if inch is the second selected checkbox
                    chosenUnits[1] = "Hours"//set second chosen unit to inch
                }
            }else if(minCheckBox.isChecked){//if metre box is checked
                chosenUnits[0] = "Minutes"//set first chosen unit to metre
                if(hrCheckBox.isChecked){//if inch check box is checked
                    chosenUnits[1] = "Hours"//set second chosen unit to inch
                }
            }else if(hrCheckBox.isChecked){//if inch check box is checked
                chosenUnits[0] = "Hours"//set first chosen unit to inch
            }
            unitsTextView.text = chosenUnits[0]//set the text view to the first chosen unit
            unitsTextView2.text = chosenUnits[1]//set the second text view to the second chosen unit

            comm.passDataCom(chosenUnits[0],chosenUnits[1],chosenCategory)//submit the chosen/verified units to the main activity
        }
        //listener for the second check box
        secCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Seconds"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Seconds"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(minCheckBox.isChecked && hrCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                secCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Seconds"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Seconds"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Seconds"//set the second textView to Hours
                }
            }
            timeButton.callOnClick()
        }
        //listener for the minute check box
        minCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Minutes"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Minutes"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(secCheckBox.isChecked && hrCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                minCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Minutes"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Minutes"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Minutes"//set the second textView to Hours
                }
            }
            timeButton.callOnClick()
        }
        //listener for the hour check box
        hrCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Hours"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Hours"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(minCheckBox.isChecked && secCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                hrCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Hours"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Hours"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Hours"//set the second textView to Hours
                }
            }
            timeButton.callOnClick()
        }

        //listener for the time button
        temperatureButton?.setOnClickListener {
            chosenCategory = categories[4]//set chosen category to temp from the categories array
            categoryTextView.text = chosenCategory//set the textView to the chosen category

            chosenUnits[0] = ""//resets the value
            chosenUnits[1] = ""//resets the value

            if(celsiusCheckBox.isChecked){//if centimetre has been checked
                chosenUnits[0] = "Celsius"//set the first chosen unit to centimetre
                if(fahrenheitCheckBox.isChecked){//if metre has also been checked
                    chosenUnits[1] = "Fahrenheit"//set the second chosen unit to metre
                    //if(hrCheckBox.isChecked){//if inch was also checked then set it to false (only two boxes can be checked at one time)
                    //    hrCheckBox.isChecked = false//set checkbox to false
                    //}
                }else if(kelvinCheckBox.isChecked){//if inch is the second selected checkbox
                    chosenUnits[1] = "Kelvin"//set second chosen unit to inch
                }
            }else if(fahrenheitCheckBox.isChecked){//if metre box is checked
                chosenUnits[0] = "Fahrenheit"//set first chosen unit to metre
                if(kelvinCheckBox.isChecked){//if inch check box is checked
                    chosenUnits[1] = "Kelvin"//set second chosen unit to inch
                }
            }else if(kelvinCheckBox.isChecked){//if inch check box is checked
                chosenUnits[0] = "Kelvin"//set first chosen unit to inch
            }
            unitsTextView.text = chosenUnits[0]//set the text view to the first chosen unit
            unitsTextView2.text = chosenUnits[1]//set the second text view to the second chosen unit

            comm.passDataCom(chosenUnits[0],chosenUnits[1],chosenCategory)//submit the chosen/verified units to the main activity
        }
        //listener for the second check box
        celsiusCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Celsius"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Celsius"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(fahrenheitCheckBox.isChecked && kelvinCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                celsiusCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Celsius"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Celsius"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Celsius"//set the second textView to Hours
                }
            }
            temperatureButton.callOnClick()
        }
        //listener for the minute check box
        fahrenheitCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Fahrenheit"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Fahrenheit"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(celsiusCheckBox.isChecked && kelvinCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                fahrenheitCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Fahrenheit"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Fahrenheit"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Fahrenheit"//set the second textView to Hours
                }
            }
            temperatureButton.callOnClick()
        }
        //listener for the hour check box
        kelvinCheckBox?.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(!b){
                if(chosenUnits[0] == "Kelvin"){//if the first chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView.text = ""
                }
                if(chosenUnits[1] == "Kelvin"){//if the second chosenUnit contains Hours
                    chosenUnits[1] == ""
                    unitsTextView2.text = ""
                }
            }else if(celsiusCheckBox.isChecked && fahrenheitCheckBox.isChecked){//if the other checkboxes are already checked the make this one uncheck
                kelvinCheckBox.isChecked = false//set the checkbox to false
            }else if(chosenCategory == categories[3]){//if the chosen category is time
                if(chosenUnits[0] == "" || chosenUnits[0] == "Kelvin"){//if the first string in chosenUnits is empty
                    unitsTextView.text = "Kelvin"//set the first textView to Hours
                }else {
                    unitsTextView2.text = "Kelvin"//set the second textView to Hours
                }
            }
            temperatureButton.callOnClick()
        }

        return view
    }

}