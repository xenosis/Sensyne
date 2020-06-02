# Sensyne

To run the project in Android Studio choose the File menu, then Open and select the build.gradle file
at the top level of the project to open the project

Tu run the unit tests right click on the file HospitalsUnitTest and choose Run 'HospitalsUnitTest'

The code shows
* MVVM architecture
* Downloading of CSV data with Retrofit/OkHttp library
* Dynamic parsing of CSV into a data model
* Data flow through the app
* Dependency injection with Koin library
* 2 way scrolling of hospital data with easy to read UI
* Dynamic data binding of view model to dynamic UI views
* Selecting a row will show a screen
* Unit tests to show it is correctly parsing the headers and data


The app has been built to handle data dynamically, so if the Hospital.csv file is updated and
the data structure changes, the app will still work fine without having to release another version
of the app

Missing
Unfortunately I didn't have time to work on the filtering feature but am happy to discuss in detail
how I would implement this feature