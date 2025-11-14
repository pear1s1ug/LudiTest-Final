package cl.duoc.luditest_final.data.repository

import cl.duoc.luditest_final.data.local.PersonalityData
import cl.duoc.luditest_final.data.local.VideogameData
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.Videogame


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

    fun getVideogamesByPlatform(platform: String): List<Videogame> {
        return VideogameData.videogames.filter { it.platform.contains(platform) }
    }

    fun getVideogamesByGenre(genre: String): List<Videogame> {
        return VideogameData.videogames.filter { it.genres.contains(genre) }
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
}