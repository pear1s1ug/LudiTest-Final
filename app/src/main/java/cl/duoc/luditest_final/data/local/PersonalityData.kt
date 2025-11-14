package cl.duoc.luditest_final.data.local

import cl.duoc.luditest_final.data.model.PersonalityInfo
import cl.duoc.luditest_final.data.model.PersonalityType


object PersonalityData {
    val personalities = listOf(
        PersonalityInfo(
            type = PersonalityType.DOMINANT,
            title = "Estratega Determinado",
            description = "Eres directo, competitivo y orientado a resultados. Te gustan los desafíos y buscas constantemente superar obstáculos. En los videojuegos, prefieres títulos que te permitan demostrar tu habilidad y alcanzar la cima.",
            strengths = listOf(
                "Toma de decisiones rápida",
                "Liderazgo natural",
                "Enfoque en objetivos",
                "Competitividad saludable"
            ),
            recommendedGenres = listOf(
                "Shooter FPS / TPS",
                "Battle Royale",
                "Juegos de lucha",
                "Racing competitivo",
                "Deportes tradicionales y extremos",
                "Hack and slash",
                "Beat 'em up",
                "Run and gun",
                "Survival action",
                "MOBA",
                "Action RPG",
                "Tactical RPG competitivo"
            )
        ),
        PersonalityInfo(
            type = PersonalityType.INFLUENTIAL,
            title = "Explorador Social",
            description = "Eres entusiasta, creativo y te encanta conectar con otros. Disfrutas las experiencias compartidas y las historias emocionantes. En los videojuegos, valoras la narrativa y las interacciones sociales.",
            strengths = listOf(
                "Comunicación efectiva",
                "Creatividad e innovación",
                "Trabajo en equipo",
                "Adaptabilidad social"
            ),
            recommendedGenres = listOf(
                "Party",
                "Juegos casuales",
                "Co-op",
                "Karaoke / Canto",
                "Juegos de ritmo / Música",
                "Sandbox creativo social",
                "MMORPG",
                "Minijuegos",
                "Trivia / Quiz",
                "Juegos Narrativos",
                "Simuladores Sociales",
                "Simulaciones de vida"
            )
        ),
        PersonalityInfo(
            type = PersonalityType.STEADY,
            title = "Guardian Metódico",
            description = "Eres confiable, paciente y valoras la consistencia. Prefieres entornos predecibles donde puedas desarrollar tu expertise. En los videojuegos, disfrutas títulos que permiten mastery progresivo.",
            strengths = listOf(
                "Paciencia y perseverancia",
                "Confiabilidad",
                "Escucha activa",
                "Trabajo metódico"
            ),
            recommendedGenres = listOf(
                "Juegos cooperativos no competitivos",
                "Simuladores de vida",
                "Simuladores de gestión chill",
                "Adventure",
                "Walking simulator",
                "Puzzle",
                "Visual novel",
                "Idle",
                "Point & Click"
            )
        ),
        PersonalityInfo(
            type = PersonalityType.CONSCIENTIOUS,
            title = "Analista Preciso",
            description = "Eres detallista, analítico y valoras la precisión. Te gusta entender cómo funcionan las cosas y optimizar procesos. En los videojuegos, prefieres sistemas complejos que puedas dominar.",
            strengths = listOf(
                "Pensamiento crítico",
                "Atención al detalle",
                "Planificación estratégica",
                "Análisis de datos"
            ),
            recommendedGenres = listOf(
                "Estrategia por turnos",
                "Puzzles complejos / brain teasers",
                "Juegos de lógica y acertijos",
                "Simuladores complejos",
                "4X / Grand strategy",
                "Tactical RPG",
                "Estrategia en tiempo real",
                "Roguelike / Roguelite",
                "Bullet Hell / juegos de precisión"
            )
        )
    )
}