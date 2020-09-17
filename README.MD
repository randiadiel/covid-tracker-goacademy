# Covid Tracker GoAcademy

**Covid Tracker GoAcademy** is an application that is developed during BNCC x Go-Academy Bootcamp in mobile programming as final project. The programming language that is used in this project is Kotlin. The main feature of this application is to know the number of Covid-19 positive, recovered, and death cases all around Indonesia

## Features

### Main

part of MainActivity which became the main screen after the application was opened, there were a total of confirmed cases of COVID-19 in Indonesia, total cases that were positive, recovered, and death case.

From this page you can access other features, namely About Dialog, Look Up, and Hotline.

### Hotline

focuses on display a list of province name in Indonesia alongside the phone number and logo. This feature can be access from bottom sheet in Home. The list can be select and will be direct to default phone app with the number filled.

### Lookup

focuses on displaying a list of province name in Indonesia alongside the number of positive case, recovered case, and death case. This feature has a search bar to help browse certain province name with ease.

### BottomSheet

We are using bottom sheet library from [material.io](https://material.io/develop/android/components/sheets-bottom/) to show the hotline features in Main Activity

## Dependencies

We use some dependencies at this project.

build.gradle

```bash
implementation 'androidx.constraintlayout:constraintlayout:2.0.1'
implementation 'androidx.coordinatorlayout:coordinatorlayout:1.1.0'
implementation 'com.google.android.material:material:1.2.1'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'androidx.recyclerview:recyclerview:1.1.0'
implementation 'com.squareup.okhttp3:okhttp:4.8.1'
implementation 'com.squareup.picasso:picasso:2.71828'
```

to sync the depedencies, use

```bash
./gradlew build
```

## Requirements

- SDK as Marshmallow (6.0 or API level 23) and max SDK as the latest stable Android (10 or API level 29).

## Authors

- Randi
- Stacia
- Dennis
- Ricco
- Daniel
