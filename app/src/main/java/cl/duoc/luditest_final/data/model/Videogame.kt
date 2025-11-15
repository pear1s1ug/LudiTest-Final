package cl.duoc.luditest_final.data.model

data class Videogame(
    val id: Int,
    val name: String,
    val genres: List<GameGenre>,
    val platform: List<GamePlatform>,
    val description: String,
    val rating: Double,
    val trailerUrl: String,
    val imageUrl: String,
    val featured: Boolean,
    val releaseDate: String
)