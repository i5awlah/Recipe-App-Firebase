package com.example.recipeappfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.recipeappfirebase.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddRecipeBinding
    private val recipeViewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addNewRecipe()
        }
    }

    private fun addNewRecipe() {
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val ingredients = binding.etIngredients.text.toString()
        val instructions = binding.etInstructions.text.toString()
        val newRecipe = Recipe("0", title, author, ingredients, instructions)

        recipeViewModel.addRecipe(newRecipe)


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}