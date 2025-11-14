package cl.duoc.luditest_final.data.local

import cl.duoc.luditest_final.data.model.AnswerOption
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.Question

object QuestionData {
    val questions = listOf(
        Question(
            id = 1,
            text = "Cuando juegas un juego nuevo por primera vez, ¿cuál es tu enfoque?",
            options = listOf(
                AnswerOption(
                    text = "Exploro cada rincón y leo todas las instrucciones antes de avanzar",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Salto directamente a la acción y aprendo sobre la marcha",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Sigo el camino principal pero me detengo en detalles interesantes",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Busco atajos y formas eficientes de progresar rápidamente",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                )
            )
        ),
        Question(
            id = 2,
            text = "En un proyecto grupal del trabajo/estudios, tu rol natural sería:",
            options = listOf(
                AnswerOption(
                    text = "El organizador que crea cronogramas y asigna tareas",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "El motivador que mantiene el ánimo y la comunicación",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "El colaborador confiable que completa su parte diligentemente",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "El investigador que verifica cada detalle y calidad",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 3,
            text = "¿Qué tipo de misiones secundarias te atraen más?",
            options = listOf(
                AnswerOption(
                    text = "Las que tienen recompensas únicas o ventajas significativas",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Las que involucran interacciones sociales o historias emotivas",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Las que me permiten ayudar a personajes NPC necesitados",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Las que requieren resolver acertijos complejos o coleccionar items específicos",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.STEADY to 1
                    )
                )
            )
        ),
        Question(
            id = 4,
            text = "Cuando enfrentas un problema en la vida real, tu enfoque es:",
            options = listOf(
                AnswerOption(
                    text = "Enfrentarlo directamente y tomar acción inmediata",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Buscar apoyo y opiniones de amigos o colegas",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Analizar cuidadosamente todas las opciones antes de decidir",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Mantener la calma y abordarlo de manera metódica y paciente",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                )
            )
        ),
        Question(
            id = 5,
            text = "En juegos multijugador, prefieres:",
            options = listOf(
                AnswerOption(
                    text = "Ser el líder que guía al equipo hacia la victoria",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Ser el animador que mantiene el buen ambiente del grupo",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Ser el apoyo confiable que protege a los compañeros",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Ser el estratega que analiza tácticas y estadísticas",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 6,
            text = "Al elegir qué jugar después, priorizas:",
            options = listOf(
                AnswerOption(
                    text = "Juegos que me den ventajas competitivas o reconocimiento",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Juegos con comunidades activas y oportunidades sociales",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Continuar juegos familiares donde me siento cómodo",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Juegos con sistemas complejos que pueda dominar técnicamente",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 7,
            text = "En tu tiempo libre, ¿qué actividad te resulta más satisfactoria?",
            options = listOf(
                AnswerOption(
                    text = "Aprender una nueva habilidad que me dé ventajas profesionales",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Reunirme con amigos y conocer gente nueva",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Dedicarme a hobbies relajantes que ya domino",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Investigar temas que me interesan hasta entenderlos completamente",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 8,
            text = "Cuando un juego se vuelve difícil, tu reacción típica es:",
            options = listOf(
                AnswerOption(
                    text = "Persistir hasta superar el desafío, sin importar cuántos intentos tome",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Buscar consejos en foros o ver tutoriales de otros jugadores",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Tomar un descanso y volver más tarde con paciencia",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Analizar patrones y mecánicas para encontrar la solución óptima",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 9,
            text = "En decisiones importantes de la vida, tiendes a:",
            options = listOf(
                AnswerOption(
                    text = "Confiar en tu instinto y tomar decisiones rápidas",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Consultar con varias personas antes de decidir",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Considerar cómo afectará a las personas importantes para ti",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Crear listas de pros y contras basadas en datos",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 10,
            text = "¿Qué aspecto valoras más en un juego?",
            options = listOf(
                AnswerOption(
                    text = "La competencia y posibilidad de demostrar habilidad",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "La historia emocionante y personajes memorables",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "La atmósfera relajante y experiencia consistente",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Los sistemas complejos y profundidad mecánica",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        ),
        Question(
            id = 11,
            text = "En situaciones sociales nuevas, tu comportamiento es:",
            options = listOf(
                AnswerOption(
                    text = "Tomar la iniciativa y dirigir la conversación",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.INFLUENTIAL to 1
                    )
                ),
                AnswerOption(
                    text = "Socializar fácilmente y hacer nuevos contactos",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Observar primero y participar cuando me siento cómodo",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Analizar el ambiente y las dinámicas antes de participar",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.STEADY to 1
                    )
                )
            )
        ),
        Question(
            id = 12,
            text = "Al organizar tu biblioteca de juegos, prefieres:",
            options = listOf(
                AnswerOption(
                    text = "Tener solo los juegos que juego activamente, eliminando el resto",
                    personalityWeights = mapOf(
                        PersonalityType.DOMINANT to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Mantener una colección diversa para compartir con amigos",
                    personalityWeights = mapOf(
                        PersonalityType.INFLUENTIAL to 2,
                        PersonalityType.STEADY to 1
                    )
                ),
                AnswerOption(
                    text = "Conservar todos mis juegos organizados por fecha de adquisición",
                    personalityWeights = mapOf(
                        PersonalityType.STEADY to 2,
                        PersonalityType.CONSCIENTIOUS to 1
                    )
                ),
                AnswerOption(
                    text = "Crear categorías detalladas y etiquetas específicas",
                    personalityWeights = mapOf(
                        PersonalityType.CONSCIENTIOUS to 2,
                        PersonalityType.DOMINANT to 1
                    )
                )
            )
        )
    )
}