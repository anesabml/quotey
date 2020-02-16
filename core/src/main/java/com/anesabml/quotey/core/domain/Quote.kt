package com.anesabml.quotey.core.domain

data class Quote(
    val id: Int,
    val quote: String,
    val author: String,
//    val background: String = "",
//    val category: String = "",
//    val title: String = "",
    val length: Int,
    var isFavorite: Boolean = false
) {
    companion object {
        val Empty = Quote(
            1,
            "It is impossible for a man to learn what he thinks he already knows.",
            "EPECTATUS",
//            "",
//            "",
//            "",
            67
        )
    }
}