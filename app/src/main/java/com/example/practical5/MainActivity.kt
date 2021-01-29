package com.example.practical5
//package android.cs.stir.ac.uk.yhproject

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


//Main Activity Class
class MainActivity : AppCompatActivity(), Communicator {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)
        //include code below

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page1))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_page2))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val adapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun passDataCom(edited_input: String, edited_input2: String, edited_input3: String) {
        val bundle = Bundle()//create a bundle to parse
        bundle.putString("input_txt", edited_input)//add the first unit to the bundle
        bundle.putString("input2_txt", edited_input2)//add the second unit to the bundle
        bundle.putString("input3_txt", edited_input3)//add the category to the bundle

        val fragment2 = Page2Fragment()//reference to the fragment
        fragment2.arguments = bundle//add the bundle containing the string values
        supportFragmentManager.beginTransaction().replace(R.id.page3_fragment, fragment2).commit()//add the second fragment to the temporary one

    }
}

interface Communicator {

    fun passDataCom(edited_input: String, edited_input2: String, edited_input3: String)

}


