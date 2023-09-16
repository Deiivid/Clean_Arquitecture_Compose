# RickyMortyDN

# Clean MVVM Architecture with Dagger Hilt

This project demonstrates the implementation of a Clean MVVM architecture using Dagger Hilt for dependency injection.

## Libraries Used

- Kotlin Coroutines - For asynchronous programming
- Retrofit - For networking and API communication
- Dagger Hilt - For dependency injection
- Jetpack Compose - For building UI components
- JUnit and MockK - For testing

## Architecture

The project follows the Clean MVVM architecture, which consists of the following layers:

- **Presentation Layer**: Contains the UI components, ViewModels, and Compose screens.
- **Domain Layer**: Contains the business logic and use cases.
- **Data Layer**: Contains the repositories, data sources, and API communication.
- **Dependency Injection**: Uses Dagger Hilt for dependency injection.

## Folder Structure

The project is structured as follows:
- app
  - src
    - main
      - java/com/example/myapp
        - di -> Contains Dagger Hilt modules and components for dependency injection
        - data -> Contains repository implementations and data sources
        - domain -> Contains use cases and business logic
        - presentation -> Contains ViewModels and UI components
        - utils -> Contains utility classes and extensions
    - test
      - java/com/example/myapp
        - data -> Contains data-related tests
        - domain -> Contains domain-related tests
        - presentation -> Contains presentation-related tests
    - androidTest
      - java/com/example/myapp
        - ui -> Contains UI tests using Espresso




## Usage

1. Clone the repository: https://github.com/Deiivid/RickyMortyDN.git
