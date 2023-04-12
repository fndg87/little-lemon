# little-lemon
This project is the Little Lemon restaurant Android App Capstone for the Meta - Android Developer Certificate course.

The scope and workflow for this initial version of this app is limited to the following steps: 
1-) Onboard a user (Register) and persist data
2-) Display the restaurants Menu (fetching data from the internet)
3-) Display a Profile screen showing the persisted data.

# Application screens

![title](assets/littlelemon.gif)

## Built with

- [Kotlin](https://kotlinlang.org/) - For coding.
- [Ktor](https://ktor.io) -Create asynchronous client and server applications. Anything from
    - microservices to multiplatform HTTP client apps in a simple way.
- [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences)
    - Interface for accessing and modifying preference data
- [Glide Compose](https://bumptech.github.io/glide/int/compose.html) -This library integrates with
    - Compose to allow you to load images in your Compose apps with Glide in a performant manner.
- Jetpack
    - [Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAiAzKqdBhAnEiwAePEjkkbfP8b_r6c57F3jtdwOjxWpBbNOXVmpSnAUu4HKCid7KtSvfiiYeRoC1wYQAvD_BwE&gclsrc=aw.ds)
        - Android’s recommended modern toolkit for building native UI.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
        - Handling and manage the navigation in the app.
    - [Room database](https://developer.android.com/training/data-storage/room)
        - The Room persistence library provides an abstraction layer over SQLite to allow fluent database
        - access while harnessing the full power of SQLite.