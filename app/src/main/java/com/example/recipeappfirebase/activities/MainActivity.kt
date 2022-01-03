package com.example.recipeappfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappfirebase.adapters.RecipeAdapter
import com.example.recipeappfirebase.services.RecipeViewModel
import com.example.recipeappfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private val recipeViewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchData()
        binding.btnAddRecipe.setOnClickListener {
            openNewRecipeActivity()

        }
    }

    private fun setupRecyclerView() {
        recipeRecyclerView = binding.rvRecipe
        recipeAdapter = RecipeAdapter()
        recipeRecyclerView.adapter = recipeAdapter
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        recipeViewModel.getRecipes().observe(this, {
                recipes -> recipeAdapter.updateRV(recipes)
        })

    }

    private fun openNewRecipeActivity() {
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivity(intent)
    }
}