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
            "IT IS IMPOSSIBLE FOR A MAN TO LEARN WHAT HE THINKS HE ALREADY KNOWS",
            "EPECTATUS",
            "",
            "",
            "",
            67
        )
    }
}