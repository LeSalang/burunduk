package com.lesa.burunduk.data.expenses.models

import com.lesa.burunduk.R

enum class Category {
    ALL_FOOD,
    VEGAN_FOOD,
    HOUSEWARES,
    HYGIENE,
    HEALTH,
    PREPARED_FOOD,
    ENTERTAINMENT,
    BIG_VACATION,
    RENT,
    COMMUNICATIONS,
    CLOTHES,
    ELECTRONICS,
    TRANSPORT,
    OTHER
}

val Category.nameId: Int
    get() = when(this) {
        Category.ALL_FOOD -> R.string.category_all_food
        Category.VEGAN_FOOD -> R.string.category_vegan_food
        Category.HOUSEWARES -> R.string.category_housewares
        Category.HYGIENE -> R.string.category_hygiene
        Category.HEALTH -> R.string.category_health
        Category.PREPARED_FOOD -> R.string.category_prepared_food
        Category.ENTERTAINMENT -> R.string.category_entertainment
        Category.BIG_VACATION -> R.string.category_big_vacation
        Category.RENT -> R.string.category_rent
        Category.COMMUNICATIONS -> R.string.category_communications
        Category.CLOTHES -> R.string.category_clothes
        Category.ELECTRONICS -> R.string.category_electronics
        Category.TRANSPORT -> R.string.category_transport
        Category.OTHER -> R.string.category_other
    }
