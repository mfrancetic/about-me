package com.mfrancetic.aboutme

import android.content.Context
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.mfrancetic.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // glue between the layout and its views
    // name derived from the layout file + Binding
    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Maja Francetic")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // standard setContentView, without data binding
//        setContentView(R.layout.activity_main)

        // setContentView, with data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        // it = doneButton
        binding.doneButton.setOnClickListener { addNickname(it) }
    }

    private fun addNickname(view: View) {
        binding.apply {
//            nicknameText.text = binding.nicknameEdit.text;
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll() // invalidating all binding expressions, so they get
            // recreated, with the correct data
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE;
        }

        // Hide the keyboard
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}