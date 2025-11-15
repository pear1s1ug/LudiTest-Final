package cl.duoc.luditest_final.data.repository

import cl.duoc.luditest_final.data.local.PersonalityData
import cl.duoc.luditest_final.data.local.VideogameData
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.Videogame
import cl.duoc.luditest_final.data.model.GameGenre
import cl.duoc.luditest_final.data.model.GamePlatform
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class VideogameRepository {

    // ... todas tus funciones existentes se mantienen igual ...

    fun getAllVideogames(): List<Videogame> {
        return VideogameData.videogames
    }

    fun getFeaturedVideogames(): List<Videogame> {
        return VideogameData.videogames.filter { it.featured }
    }

    fun getVideogameById(id: Int): Videogame? {
        return VideogameData.videogames.find { it.id == id }
    }

    fun getVideogamesByPlatform(platform: GamePlatform): List<Videogame> {
        return VideogameData.videogames.filter { it.platform.contains(platform) }
    }

    fun getVideogamesByGenre(genre: GameGenre): List<Videogame> {
        return VideogameData.videogames.filter { it.genres.contains(genre) }
    }

    // ✅ NUEVO: Búsqueda por múltiples géneros
    fun getVideogamesByGenres(genres: List<GameGenre>): List<Videogame> {
        return VideogameData.videogames.filter { videogame ->
            videogame.genres.any { genre -> genres.contains(genre) }
        }
    }

    fun searchVideogames(query: String): List<Videogame> {
        return VideogameData.videogames.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
    }

    fun getVideogamesByPersonalityType(personalityType: PersonalityType): List<Videogame> {
        val personality = PersonalityData.personalities.find { it.type == personalityType }
        return if (personality != null) {
            VideogameData.videogames.filter { videogame ->
                videogame.genres.any { genre ->
                    personality.recommendedGenres.contains(genre)
                }
            }
        } else {
            emptyList()
        }
    }

    fun getVideogamesByMinRating(minRating: Double): List<Videogame> {
        return VideogameData.videogames.filter { it.rating >= minRating }
    }

    fun getVideogamesByPlatformAndGenre(platform: GamePlatform, genre: GameGenre): List<Videogame> {
        return VideogameData.videogames.filter {
            it.platform.contains(platform) && it.genres.contains(genre)
        }
    }

    fun getVideogamesByPlatforms(platforms: List<GamePlatform>): List<Videogame> {
        return VideogameData.videogames.filter { videogame ->
            videogame.platform.any { platform -> platforms.contains(platform) }
        }
    }

    // ✅ NUEVO: Obtener juegos por fecha de lanzamiento
    fun getVideogamesByReleaseDate(date: String): List<Videogame> {
        return VideogameData.videogames.filter { it.releaseDate == date }
    }

    // ✅ NUEVO: Obtener juegos próximos a lanzarse
    fun getUpcomingVideogames(): List<Videogame> {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        return VideogameData.videogames.filter { it.releaseDate > currentDate }
    }

    // ✅ NUEVO: Obtener juegos recientemente lanzados
    fun getRecentlyReleasedVideogames(): List<Videogame> {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        return VideogameData.videogames.filter { it.releaseDate <= currentDate }
    }

    // ✅ NUEVO: Obtener juegos por mes y año
    fun getVideogamesByMonthYear(month: Int, year: Int): List<Videogame> {
        return VideogameData.videogames.filter { videogame ->
            val parts = videogame.releaseDate.split("-")
            if (parts.size == 3) {
                parts[0].toIntOrNull() == year && parts[1].toIntOrNull() == month
            } else {
                false
            }
        }
    }

    // ✅ NUEVO: Ordenar juegos por fecha de lanzamiento
    fun getVideogamesSortedByReleaseDate(ascending: Boolean = true): List<Videogame> {
        return if (ascending) {
            VideogameData.videogames.sortedBy { it.releaseDate }
        } else {
            VideogameData.videogames.sortedByDescending { it.releaseDate }
        }
    }

    // === IMPLEMENTACIÓN OPCIÓN 2 ===

    // Mapper para géneros principales (puede ir dentro del repositorio o como object separado)
    private fun getPrimaryGenre(personalityType: PersonalityType): GameGenre {
        return when (personalityType) {
            PersonalityType.DOMINANT -> GameGenre.BATTLE_ROYALE
            PersonalityType.INFLUENTIAL -> GameGenre.PARTY
            PersonalityType.STEADY -> GameGenre.LIFE_SIM
            PersonalityType.CONSCIENTIOUS -> GameGenre.TURN_BASED_STRATEGY
        }
    }

    // ✅ NUEVO: Obtener juegos destacados por personalidad (top 3 por rating)
    fun getFeaturedGamesByPersonality(personalityType: PersonalityType): List<Videogame> {
        val primaryGenre = getPrimaryGenre(personalityType)
        return VideogameData.videogames
            .filter { it.genres.contains(primaryGenre) }
            .sortedByDescending { it.rating }
            .take(3) // Top 3 por rating
    }

    // ✅ NUEVO: Obtener el juego principal destacado para una personalidad
    fun getMainFeaturedGame(personalityType: PersonalityType): Videogame? {
        val primaryGenre = getPrimaryGenre(personalityType)
        return VideogameData.videogames
            .filter { it.genres.contains(primaryGenre) }
            .maxByOrNull { it.rating }
    }

    // ✅ NUEVO: Obtener todos los juegos del género principal de una personalidad
    fun getGamesByPrimaryGenre(personalityType: PersonalityType): List<Videogame> {
        val primaryGenre = getPrimaryGenre(personalityType)
        return VideogameData.videogames
            .filter { it.genres.contains(primaryGenre) }
            .sortedByDescending { it.rating }
    }

    // ✅ NUEVO: Versión con límite personalizable
    fun getFeaturedGamesByPersonality(personalityType: PersonalityType, limit: Int = 3): List<Videogame> {
        val primaryGenre = getPrimaryGenre(personalityType)
        return VideogameData.videogames
            .filter { it.genres.contains(primaryGenre) }
            .sortedByDescending { it.rating }
            .take(limit)
    }
}