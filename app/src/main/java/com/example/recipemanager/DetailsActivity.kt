
//Name: Kumran Gopal Maistry
//Student Number: ST10480333
//Module Name: Introduction to mobile application development
//Module Code: IMAD5112

package com.example.recipemanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {
//private lateinit var is used so i dont have to keep bringing up/initialising the variable names
    private lateinit var txtOutput: TextView
    private lateinit var btnDisplay: Button
    private lateinit var btnAverage: Button
    private lateinit var btnBack: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
//declaring variables for buttons and textview
        txtOutput = findViewById(R.id.txtOutput)
        btnDisplay = findViewById(R.id.btnDisplay)
        btnAverage = findViewById(R.id.btnAverage)
        btnBack = findViewById(R.id.btnBack)
//setonclicklistener for what the button does when pressed
        btnDisplay.setOnClickListener { displayRecipes() }
        btnAverage.setOnClickListener { showAverageRating() }
        btnBack.setOnClickListener { finish() }
    }
//private function to connect the variables from MainActivity to DetailsActivity
    private fun displayRecipes() {
        val recipes = MainActivity.recipeNameList
        val categories = MainActivity.categoryList
        val ratings = MainActivity.ratingList
        val ingredients = MainActivity.ingredientsList
//if else statement for what to do if nothing is inputed, this is error handling aswell
        if (recipes.isEmpty()) {
            txtOutput.text = "No recipe in book."
            return
        }
//loops is used along with builder append to sort the inputed information from the user and place them into the textview
        val builder = StringBuilder()
        for (i in recipes.indices) {
            builder.append(" recipe: ${recipes[i]}\n")
            builder.append(" category: ${categories[i]}\n")
            builder.append(" Rating: ${ratings[i]}\n")
            builder.append(" ingredients: ${ingredients[i]}\n\n")
        }
//connects the textview to the user inputs
        txtOutput.text = builder.toString()
    }
// private fun for the calculation loop for calculating the average
    private fun showAverageRating() {
        val ratings = MainActivity.ratingList
//if else for average calculation
        if (ratings.isEmpty()) {
            txtOutput.text = "No ratings available."
            return //ends the loop
        }
//loop for average calculation
        var sum = 0
        for (r in ratings) {
            sum += r
        }

        val avg = sum.toDouble() / ratings.size
        txtOutput.text = "🎯 Average Rating: %.2f".format(avg)
    }
}