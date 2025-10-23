## 💻 Clean MVVM Architecture with Dagger Hilt 💻

- This project demonstrates the implementation of a Clean MVVM architecture using Dagger Hilt for dependency injection.

## 📚 Libraries 📚

-  Kotlin Coroutines - For asynchronous programming
-  Retrofit - For networking and API communication
-  Paging 3 - For pagination
-  Dagger Hilt - For dependency injection
-  Jetpack Compose - For building UI components
-  JUnit and MockK - For testing
-  Lottie: For Animations.
-  Material 3: For the visual part.
-  Detekt: For Code Optimization.



##  🧮 Architecture 🧮

The project follows the Clean MVVM architecture, which consists of the following layers:

-  **Presentation Layer**: Contains the UI components, ViewModels, and Compose screens.
-  **Domain Layer**: Contains the business logic and use cases.
-  **Data Layer**: Contains the repositories, data sources, pagination and API communication.
-  **Dependency Injection**: Uses Dagger Hilt for dependency injection.

## 📁 Folder Structure 📁

The project is structured as follows:
-  app
 -   src
  -   main
    -    java/com/example/myapp
        -  data -> Contains repository implementations and data sources
        - domain -> Contains use cases and business logic
        -  presentation -> Contains ViewModels and UI components
        -  models -> Contains model classes and common constants
   
## Usage

1. Clone the repository: https://github.com/Deiivid/Clean_Arquitecture_Compose.git

## 🧮 DETEKT 🧮

This project integrates **Detekt** — a static analysis tool for Kotlin — with **custom Clean Architecture rules** and **auto-correction enabled** (`autoCorrect = true`).

It enforces architectural boundaries **per layer and folder** (`domain`, `data`, `ui`) to ensure a consistent, scalable, and maintainable structure.

### ⚙️ Configuration

```kotlin
detekt {
    buildUponDefaultConfig = true
    autoCorrect = true // ✅ Enables automatic code formatting
    config.setFrom(files("$rootDir/detekt.yml"))
}
```
![Captura de pantalla 2023-11-23 a las 17 52 21](https://github.com/Deiivid/Clean_Arquitecture_Compose/assets/60486280/a428dd13-bce3-4e36-942c-8edf924882d9)

## 🗺️ Navigation 🗺️

![Navigation](https://github.com/Deiivid/Clean_Arquitecture_Compose/assets/60486280/b1bb18c3-b3e6-4b0b-8695-1642e1c1b760)


##  🙂 Images 🙂

![Ricky morty images](https://github.com/Deiivid/Clean_Arquitecture_Compose/assets/60486280/7a7bc88b-ce68-4c47-b6b7-ce98c08b3530)

