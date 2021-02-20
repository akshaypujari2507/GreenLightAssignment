# GreenLightAssignment

# The app has following packages:


# data: It contains all the data accessing and manipulating components.
	I. local
		1. db for database
		2. doa for room data access
		3. entiries room database tables
	II. remote
		1. api for retrofit services
		2. models for api
	III. repository
		1. Repository local and remote data.
# di: It contains Injection class.
# ui: view and viewholders along with their corresponding Components
	1. adapters 
	2. interface
	3. view
		i. viewholder
	4. viewmodel
		i. factory
# util: Util class


## Description
Metrics app loads the data from the "http://demo1929804.mockable.io/" and stores it in persistent storage. 
Records will be always loaded from the local database. 

Metrics app is offline capable.
Here is the API being used.

Built with 🛠
Kotlin - First class and official programming language for Android development.
Coroutines - For network call and asynchronous
Android Architecture Components - Collection of libraries that help you design robust and maintainable apps.
LiveData - Data objects that notify views when the underlying database changes.
ViewModel - Stores UI-related data that isn't destroyed on UI changes.
Room - SQLite object mapping library.
Retrofit - A type-safe HTTP client for Android and Java.
Material design guideline.


## Key Feature
Support search for a specific employee name.
Can view the records in ascending and descending order by name.

# Demo:

https://user-images.githubusercontent.com/42991989/108594324-6bc50380-739f-11eb-84cb-4ff88b3ae912.mp4

