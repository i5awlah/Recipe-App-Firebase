package com.example.recipeappfirebase.models

import java.io.Serializable

data class Recipe(
    val pk: String,
    val title: String,
    val author: String,
    val ingredients: String,
    val instructions: String
) {
    fun toMap(): HashMap<String, String> {
        return hashMapOf(
            "author" to author,
            "title" to title,
            "ingredients" to ingredients,
            "instructions" to instructions
        )
    }
}