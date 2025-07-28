
//Name: Kumran Gopal Maistry
//Student Number: ST10480333
//Module Name: Introduction to mobile application development
//Module Code: IMAD5112

package com.example.recipemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
//companion object allows me to define the variables
    companion object {
        val recipeNameList = ArrayList<String>()
        val categoryList = ArrayList<String>()
        val ratingList = ArrayList<Int>()
        val ingredientsList = ArrayList<String>()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//declaring my button variables
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDetails = findViewById<Button>(R.id.btnDetails)
        val btnExit = findViewById<Button>(R.id.btnExit)
//button.setonclicklistener used to connect what the button does in the app
        btnAdd.setOnClickListener { showAddDialog() }
//connects the to activities, when button is clicked, it wil show the next activity
        btnDetails.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
//when button is clicked you will exit the app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
//when u click add to recipe book, a box filled with edit texts will prompt you to fill in information regarding the recipe
    private fun showAddDialog() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(30, 30, 30, 30)
//Edit text for name of recipe, "The Name Of The Recipe" will be the hint
        val recipeInput = EditText(this)
        recipeInput.hint = "The Name Of The Recipe"
        layout.addView(recipeInput)
//Edit text for the category, "The Category" will be the hint
        val categoryInput = EditText(this)
        categoryInput.hint = "The Category"
        layout.addView(categoryInput)
//Edit text for the rating, "Rating (1-5)" will be the hint. It will only allow number 1-5.
        val ratingInput = EditText(this)
        ratingInput.hint = "Rating (1-5)"
        ratingInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(ratingInput)
//Edit text for ingredients, "The Ingredients" will be the hint
        val ingredientsInput = EditText(this)
        ingredientsInput.hint = "The Ingredients"
        layout.addView(ingredientsInput)
//initialises and connects the variables to its appropriate string also adds the "Add Recipe To Book"
        AlertDialog.Builder(this)
            .setTitle("Add Recipe To Book")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val recipe = recipeInput.text.toString().trim()
                val category = categoryInput.text.toString().trim()
                val ratingStr = ratingInput.text.toString().trim()
                val ingredients = ingredientsInput.text.toString().trim()
//try closes the code and makes sure that no exceptions are valid
                try {
                    val rating = ratingStr.toInt()
//if else statements for if recipe or any other variable is empty, this is also error handling, it will give feedback if something is wrong
                    if (recipe.isEmpty() || category.isEmpty() || ingredients.isEmpty() || rating !in 1..5) {
                        showToast("Invalid input. Fill all fields correctly.")
                    } else {
                        recipeNameList.add(recipe)
                        categoryList.add(category)
                        ratingList.add(rating)
                        ingredientsList.add(ingredients)
                        showToast("Recipe added to book.")
                    }
//catch handles exceptions
                } catch (e: NumberFormatException) {
                    showToast("Rating must be a number between 1 and 5.")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
//TOAST for the messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}