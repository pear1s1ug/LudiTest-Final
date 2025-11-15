package cl.duoc.luditest_final.data.local

import cl.duoc.luditest_final.data.model.PersonalityInfo
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.GameGenre // ✅ Nuevo import

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
                GameGenre.FPS,           // ✅ Enums en lugar de strings
                GameGenre.TPS,
                GameGenre.BATTLE_ROYALE,
                GameGenre.FIGHTING,
                GameGenre.RACING,
                GameGenre.SPORTS,
                GameGenre.HACK_AND_SLASH,
                GameGenre.BEAT_EM_UP,
                GameGenre.RUN_AND_GUN,
                GameGenre.SURVIVAL_ACTION,
                GameGenre.MOBA,
                GameGenre.ACTION_RPG,
                GameGenre.TACTICAL_RPG
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
                GameGenre.PARTY,
                GameGenre.CASUAL,
                GameGenre.CO_OP,
                GameGenre.KARAOKE,
                GameGenre.RHYTHM,
                GameGenre.MUSIC,
                GameGenre.CREATIVE_SANDBOX,
                GameGenre.MMORPG,
                GameGenre.SOCIAL_SIM,
                GameGenre.NARRATIVE_ADVENTURE,
                GameGenre.LIFE_SIM,
                GameGenre.VISUAL_NOVEL
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
                GameGenre.CO_OP,
                GameGenre.LIFE_SIM,
                GameGenre.MANAGEMENT_SIM,
                GameGenre.ADVENTURE,
                GameGenre.WALKING_SIMULATOR,
                GameGenre.PUZZLE,
                GameGenre.VISUAL_NOVEL,
                GameGenre.IDLE_GAME,
                GameGenre.POINT_AND_CLICK
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
                GameGenre.TURN_BASED_STRATEGY,
                GameGenre.PUZZLE,
                GameGenre.LOGIC_PUZZLE,
                GameGenre.BRAIN_TEASER,
                GameGenre.SIMULATION,
                GameGenre.FOUR_X,
                GameGenre.GRAND_STRATEGY,
                GameGenre.TACTICAL_RPG,
                GameGenre.REAL_TIME_STRATEGY,
                GameGenre.ROGUELIKE,
                GameGenre.ROGUELITE,
                GameGenre.BULLET_HELL
            )
        )
    )
}