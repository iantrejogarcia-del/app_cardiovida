# Cardio Vida

Aplicación Android de **bienestar y salud cardiovascular**. Reúne herramientas de seguimiento (calorías, peso ideal, estado de ánimo, salud mental), consejos de salud, recetas saludables y un reproductor de sonidos relajantes, todo detrás de un registro e inicio de sesión de usuarios.

> **Aviso:** el contenido es informativo y educativo. No sustituye la opinión de un profesional de la salud.

## Funcionalidades

- **Onboarding** de bienvenida y **registro / inicio de sesión** con usuario, correo y contraseña guardados en una base de datos SQLite local; la sesión se recuerda con `SharedPreferences`.
- **Contador de calorías**: eliges un alimento y una fecha, se suma al total del día y se muestra en una **gráfica de barras** (MPAndroidChart). Los datos se guardan en SQLite.
- **Peso ideal**: a partir de estatura, edad y peso indica si estás por debajo, por encima o en tu peso ideal.
- **Estado de ánimo**: recomendaciones según cómo te sientes.
- **Salud mental**: cuestionario que muestra un porcentaje de salud mental.
- Sección de **sueño** y de salud **cardiovascular**.
- **Consejos de salud**: agua, comida saludable, control de peso, ejercicios, quinoa y presión arterial.
- **Recetas saludables**: ensalada de garbanzos, ensalada de pollo, agua de fresa, muffins de plátano y sopa de lentejas.
- **Música relajante**: reproductor con sonidos de agua, bosque, lluvia y música relajante, con barra de progreso.
- **Cuenta**: políticas de privacidad, calificar la app y datos del usuario.

## Tecnologías

- Java (con algunas dependencias de Kotlin)
- Android SDK (Material Components, AppCompat, ConstraintLayout, Navigation Component, ViewPager2)
- SQLite (`SQLiteOpenHelper`) y `SharedPreferences`
- `MediaPlayer` para el audio
- Librerías: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart), [Picasso](https://github.com/square/picasso), [ImageSlideshow](https://github.com/denzcoskun/ImageSlideshow) y [chip-navigation-bar](https://github.com/ismaeldivita/chip-navigation-bar) (se descargan desde JitPack)
- Gradle (Kotlin DSL)

## Requisitos

- Android Studio (versión reciente)
- Android 11 o superior (`minSdk 30`)
- Conexión a internet la primera vez, para que Gradle descargue las librerías

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone https://github.com/iantrejogarcia-del/app_cardiovida.git
   ```
2. Abre el proyecto en Android Studio y espera a que termine la sincronización de Gradle.
3. Conecta un dispositivo Android o inicia un emulador.
4. Presiona **Run** y crea una cuenta para entrar.

## Estructura del proyecto

```
app/src/main/java/com/example/cardvascular/
├── Onboarding/          # Pantallas de bienvenida
├── login/               # Registro, inicio de sesión y base de datos de usuarios
├── fragments/           # Inicio, estilo de vida y cuenta
├── vetnanas_inicio/     # Emociones, salud mental, sueño, cardiovascular, calorías y peso
├── Ventanas_EstVida/    # Consejos de salud y recetas
├── Ventanas_cuenta/     # Cuenta, políticas de privacidad y calificación
└── Musica/              # Reproductor de sonidos relajantes
```

## Posibles mejoras

- Guardar las contraseñas cifradas (hash con sal) en lugar de texto plano.
- Agregar más alimentos al contador de calorías y permitir editar o borrar registros.
- Usar una fórmula de peso ideal más completa (por ejemplo, IMC) y distinguir por sexo.
- Comprimir los audios e imágenes para reducir el tamaño de la app.
- Liberar el `MediaPlayer` al cerrar la pantalla de música.

## Autor

**Ian Trejo García** — Estudiante de Ingeniería en Sistemas Computacionales
GitHub: [iantrejogarcia-del](https://github.com/iantrejogarcia-del)
