package eu.epfc.pocketmovie.data

import java.io.Serializable
import java.util.*

class Movie(
    val title: String,
    val ReleaseDate: String,
    val vote: String,
    val backDropsPath: String,
    val posterpath: String,
    val overview: String,
    val checkBox: Boolean = false
    ) : Serializable {
}