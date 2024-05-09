package com.example.stream.data

data class Videos(
    val id: Int,
    val title: String,
    val description: String,
    val likes: Int,
    val channelName: String,
    val url: String
){
    fun doesMatchSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            "$title$channelName",
            "$title $channelName"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
