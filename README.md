# SpotFree

Looking for a hassle-free way to find unoccupied parking slots in Melbourne city? Look no further than SoftFree! Our app is the perfect solution for anyone who wants to save time and avoid the frustration of circling around the block looking for a place to park.

With SoftFree, you can quickly and easily find a parking spot that's close to your destination. Our intuitive interface and powerful search engine make it easy to locate unoccupied parking slots, so you can get on with your day without any unnecessary delays.

Whether you're a resident of Melbourne city or just visiting for the day, SoftFree is the smart choice for anyone who wants to save time and enjoy a hassle-free parking experience. Download our app today and start finding parking spots in Melbourne city with ease!

### Technologies Used
This app has been developed using the MVVM (Model-View-ViewModel) architecture, which is a software design pattern that separates the user interface from the business logic of the application. In the MVVM pattern, the "Model" represents the data and business logic of the application, the "View" is responsible for displaying the user interface, and the "ViewModel" acts as an intermediary between the Model and the View.

The ViewModel is responsible for processing the data from the Model and making it available to the View for display, as well as taking input from the View and translating it into actions on the Model. This architecture has several benefits, including separating concerns and making the app easier to maintain, test and scale.

To make it easier to access the views in the UI, the app also uses View Binding. View Binding is a feature introduced in Android Studio 3.6 that allows you to generate a binding class for your XML layouts. This binding class contains references to all the views in the layout, allowing you to easily access them in your code without having to use findViewById() calls. View Binding can help to reduce the amount of boilerplate code in your activities and fragments, and also makes it less error-prone when it comes to accessing views.

The app also uses Coroutines to perform asynchronous operations in a sequential way without blocking the main thread. Coroutines are a concurrency design pattern that allows you to write asynchronous code in a sequential way. They are especially useful for long-running operations, such as network requests, because they allow you to perform these operations without blocking the main thread, which would cause the UI to freeze. Coroutines are light-weight threads that can run on the main thread or a background thread.

In summary, this app uses the MVVM architecture to separate the data and business logic from the UI, View Binding to make it easier to access the views in the UI, and Coroutines to perform asynchronous operations without blocking the main thread. These three components work together to create a well-structured and efficient app that is easy to maintain, test and scale.

### Thought Process
In the app development process, the initial step is to identify the specific user requirements that the app needs to address. Once the user needs are clearly defined, the next step is to explore and evaluate the available APIs, and select the ones that best meet the user requirements. After this, the scope of the app is defined and the UI design is planned, ensuring that the app is intuitive and user-friendly.

The next step is to evaluate and select the appropriate technologies and tools for the development of the app, based on the requirements and constraints of the project.

Finally, the app development process involves building the app and testing it thoroughly to ensure that it is free of errors and bugs.

### Challenges
Discovering the appropriate API was a primary difficulty, as it is contingent on the particular requirements and needs of the project. Additionally, it took some effort to update my knowledge of Android and Kotlin, as it has been a while since I've worked with these technologies. The technology landscape is constantly changing, and keeping up with the latest advancements can be demanding.

Fortunately, there are many resources available to assist in learning and remaining up-to-date with the latest technologies, such as online tutorials, documentation, and community forums. Learning new programming languages and technologies can be time-consuming, but it is an essential component of remaining current and relevant in the ever-evolving field of software development.

In this project, I was able to overcome the challenge of using new technologies by taking the time to refresh my knowledge of Android and Kotlin and utilizing available resources to learn about new features and best practices. This allowed me to successfully implement the MVVM architecture, view binding, and coroutines in the project.

### How to use
Open the `local.properties` in your project level directory, and then add the following code. Replace `YOUR_API_KEY` with your API key.
```kotlin
MAPS_API_KEY=YOUR_API_KEY
