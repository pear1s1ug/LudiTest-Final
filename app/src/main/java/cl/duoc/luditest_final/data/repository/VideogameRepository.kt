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
}