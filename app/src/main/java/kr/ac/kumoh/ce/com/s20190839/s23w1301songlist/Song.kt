package kr.ac.kumoh.ce.com.s20190839.s23w1301songlist

data class Song(
    val id: Int,
    val title: String,
    val singer: String,
    val rating: Int,
    val lyrics: String?
)
