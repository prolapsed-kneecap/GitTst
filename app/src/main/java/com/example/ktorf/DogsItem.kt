package com.example.ktorf

data class DogsItem(
    val bred_for: String = "",
    val breed_group: String = "",
    val country_code: String = "",
    val description: String = "",
    val height: Height = Height(),
    val history: String = "",
    val id: Int = 0,
    val image: Image = Image(),
    val life_span: String = "",
    val name: String = "",
    val origin: String = "",
    val reference_image_id: String = "",
    val temperament: String = "",
    val weight: Weight = Weight()
)