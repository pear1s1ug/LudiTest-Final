package cl.duoc.luditest_final.data.local

import cl.duoc.luditest_final.data.model.Videogame

object VideogameData {
    val videogames = listOf(
        Videogame(
            id = 1,
            name = "1000xResist",
            genres = listOf("Adventure", "Puzzle", "Narrative"),
            platform = listOf("PS5", "Xbox Series X/S"),
            description = "Un intrigante juego de aventuras narrativo donde exploras memorias y realidades alternas en una experiencia única de ciencia ficción.",
            rating = 4.5,
            trailerUrl = "https://www.youtube.com/watch?v=example_1000xresist",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co6r4a.jpg",
            featured = true
        ),
        Videogame(
            id = 2,
            name = "Age of Empires IV: Anniversary Edition",
            genres = listOf("Strategy", "Real-Time Strategy", "Historical"),
            platform = listOf("PS5"),
            description = "El aclamado juego de estrategia en tiempo real llega a consolas con contenido especial de aniversario y civilizaciones únicas.",
            rating = 4.7,
            trailerUrl = "https://www.youtube.com/watch?v=example_aoe4",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co4k7r.jpg",
            featured = true
        ),
        Videogame(
            id = 3,
            name = "Football Manager 26",
            genres = listOf("Sports", "Simulation", "Management"),
            platform = listOf("PC"),
            description = "La experiencia definitiva de gestión futbolística con mecánicas mejoradas, base de datos actualizada y nuevas características tácticas.",
            rating = 4.3,
            trailerUrl = "https://www.youtube.com/watch?v=example_fm26",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co7d2z.jpg",
            featured = false
        ),
        Videogame(
            id = 4,
            name = "Sonic Rumble",
            genres = listOf("Battle Royale", "Action", "Multiplayer"),
            platform = listOf("PC", "Smartphones"),
            description = "Un battle royale competitivo con los personajes clásicos de Sonic, donde hasta 32 jugadores compiten en arenas dinámicas.",
            rating = 4.0,
            trailerUrl = "https://www.youtube.com/watch?v=example_sonicrumble",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co7f0a.jpg",
            featured = true
        ),
        Videogame(
            id = 5,
            name = "Hyrule Warriors: La Era del Destierro",
            genres = listOf("Action", "Hack and Slash", "Adventure"),
            platform = listOf("Switch 2"),
            description = "Una nueva entrega de la serie Warriors ambientada en el universo de Zelda, explorando eventos nunca antes vistos en la cronología.",
            rating = 4.6,
            trailerUrl = "https://www.youtube.com/watch?v=example_hyrulewarriors",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co6w3a.jpg",
            featured = true
        ),
        Videogame(
            id = 6,
            name = "Lumines Arise",
            genres = listOf("Puzzle", "Music", "Rhythm"),
            platform = listOf("PC", "PS5"),
            description = "El clásico puzzle musical regresa con gráficos mejorados, nuevas pistas y modos de juego innovadores que sincronizan con la música.",
            rating = 4.2,
            trailerUrl = "https://www.youtube.com/watch?v=example_lumines",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co6x9b.jpg",
            featured = false
        ),
        Videogame(
            id = 7,
            name = "Dead Static Drive",
            genres = listOf("Survival Horror", "Action", "Open World"),
            platform = listOf("PC", "Xbox One", "Xbox Series X/S"),
            description = "Un survival horror de mundo abierto inspirado en los años 80, donde enfrentas criaturas lovecraftianas en un pueblo maldito.",
            rating = 4.4,
            trailerUrl = "https://www.youtube.com/watch?v=example_deadstatic",
            imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co5l8a.jpg",
            featured = false
        )
    )
}