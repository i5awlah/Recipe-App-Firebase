package com.example.recipeappfirebase

data class Recipe(
    val pk: String,
    val title: String,
    val author: String,
    val ingredients: String,
    val instructions: String
)