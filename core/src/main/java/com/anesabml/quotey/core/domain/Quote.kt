package com.anesabml.quotey.core.domain

data class Quote(
    val id: String,
    val quote: String,
    val author: String,
    val background: String = "",
    val category: String = "",
    val title: String = "",
    val length: Int = 0
) {
    companion object {
        val Empty = Quote(
            "",
            "It is impossible for a man to learn what he thinks he already knows.",
            "EPECTATUS",
            "",
            "",
            "",
            67
        )
    }
}