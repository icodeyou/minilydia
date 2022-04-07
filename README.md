# MiniLydia
An app that fetches and display data from the API Random User

## Get Started

This app consists of getting data from the API [Random User](https://randomuser.me/).

The aim is to get and display data from this JSON :

`https://randomuser.me/api/1.0/?page=2&results=10&seed=lydia`

## Architecture

The project follows the MVVM pattern, and respects the standards of Clean Architecture.
Here is the documentation :
* [Clean Architecture](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29)
* [MVVM](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwwtTmBRBqEiwA-b6c_xzTC-8dos110OOgVQtVX2pFi1lNuJ7M4ZDlZo78pG2gFcQgAYyrIRoCOLAQAvD_BwE)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)

## Libraries
The following Android libraries are used :
* [Navigation](https://developer.android.com/guide/navigation)
* [Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging/)

And here are the outsourced libraries :
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Retrofit](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [Timber](https://github.com/JakeWharton/timber)


## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    © Copyright 2022 - Jean VALLON