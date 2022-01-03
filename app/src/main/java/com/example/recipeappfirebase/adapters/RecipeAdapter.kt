package com.example.recipeappfirebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeappfirebase.databinding.RecipeRowBinding
import com.example.recipeappfirebase.models.Recipe

class RecipeAdapter(): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private var recipes = listOf<Recipe>()
    class RecipeViewHolder(val binding: RecipeRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.apply {
            // Each Card View should display the Title of the recipe and its Author
            tvTitle.text = recipe.title
            tvAuthor.text = recipe.author

            tvIngredients.text = recipe.ingredients
            tvInstructions.text = recipe.instructions

            // When users click on the Card View, they should be presented with the full Recipe (including Ingredients and Instructions)
            cvRecipe.setOnClickListener {
                llIngredientsAndInstructions.visibility = when (llIngredientsAndInstructions.visibility) {
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount() = recipes.size

    fun updateRV(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

}