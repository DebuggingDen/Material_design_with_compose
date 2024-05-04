package com.example.superherolist.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superherolist.R

data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    val age: Int,
    @DrawableRes val imageRes: Int

)

    val heroes = listOf(
        Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            age = 24,
            imageRes = R.drawable.android_superhero1
        ),
        Hero(
            nameRes = R.string.hero2,
            descriptionRes = R.string.description2,
            age = 35,
            imageRes = R.drawable.android_superhero2
        ),
        Hero(
            nameRes = R.string.hero3,
            descriptionRes = R.string.description3,
            age = 40,
            imageRes = R.drawable.android_superhero3
        ),
        Hero(
            nameRes = R.string.hero4,
            descriptionRes = R.string.description4,
            age = 21,
            imageRes = R.drawable.android_superhero4
        ),
        Hero(
            nameRes = R.string.hero5,
            descriptionRes = R.string.description5,
            age = 60,
            imageRes = R.drawable.android_superhero5
        ),
        Hero(
            nameRes = R.string.hero6,
            descriptionRes = R.string.description6,
            age = 23,
            imageRes = R.drawable.android_superhero6
        )
    )
