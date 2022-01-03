package com.example.recipeappfirebase


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    private var recipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    init {
        getData()
    }

    fun getRecipes(): LiveData<List<Recipe>> {
        return recipes
    }

    private fun getData() {
        val tempRecipes = arrayListOf<Recipe>()
        db.collection("recipes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val id = document.id
                    val data = Gson().toJson(document.data)
                    val docsData = Gson().fromJson(data, Recipe::class.java)!!
                    tempRecipes.add(Recipe(id, docsData.title, docsData.author, docsData.ingredients, docsData.instructions))
                }
                recipes.postValue(tempRecipes)
            }
            .addOnFailureListener { exception ->
                Log.w("Main", "Error getting documents.", exception)
            }
    }

    fun addRecipe(recipe: Recipe) {
        val newRecipe = hashMapOf(
            "author" to recipe.author,
            "title" to recipe.title,
            "ingredients" to recipe.ingredients,
            "instructions" to recipe.instructions
        )
        db.collection("recipes")
            .add(newRecipe)
            .addOnSuccessListener { documentReference ->
                Log.d("Main", "DocumentSnapshot added with ID: ${documentReference.id}")
                getData()
            }
            .addOnFailureListener { e ->
                Log.w("Main", "Error adding document", e)
            }
    }

}