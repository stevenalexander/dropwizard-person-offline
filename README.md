# Dropwizard simple offline web

Example project using Service Worker to provide limited offline support to a web application.

Requires:
- JDK 8
- [Gradle](https://gradle.org/) (uses gradle wrapper)
- [Service Worker](http://www.html5rocks.com/en/tutorials/service-worker/introduction/) compatible browser (currently Chrome/Chrome (Android)/Firefox/Opera)

## Run

```
./gradlew build
./gradlew run
```

## Deploy to Heroku

Requires [Heroku toolkit](https://toolbelt.heroku.com/).

```
heroku login
heroku create
git push heroku master
heroku ps:scale web=1
heroku open
```

# Notes

- Service Worker scope controls what requests it can handle and by default it is fixed to the relative path it is served from, e.g. http://localhost:8090/assets/serviceworker.js can only affect requests under http://localhost:8090/assets/. You can override this by serving it with an explicit header `Service-Worker-Allowed`, but it was difficult to do in Dropwizard with the way the AssetsBundle works.

# Coding offline support in web applications

I can remember back when I started coding I had a hard time getting my head around the idea of HTTP requests and how you record state in a web application. The fact that a user could hit the back button or refresh the screen and would lose all the data they had entered into a form seemed crazy. It all seemed so fragile and failure prone next to desktop applications. But once I understood the concepts better and knew how to design applications with HTTP in mind, I realised the elegance of the pattern and my career has mostly been creating and maintaining web applications.

Recently I've been looking a lot at failures and how to make a production system resilent. After watched a video about offline web applications I realised there is one part of application up time which everyone seems to dismiss and avoid considering, user connectivity. If your user loses internet they lose your application.

This may sound too pointless to think about since you can do nothing to control this, but consider the impact from the user perspective. They could have been in the middle of submitting large amounts of data, viewing vital records or even just browsing static documents. All of this information was on their browser a second ago and then suddenly all they have is an error screen. Anything not submitted would be lost and need to be redone and until connection is back the user can do nothing. This is not a fringe scenario, in a mobile world people lose connection all the time. If your web application is likely to be used by anyone on a mobile device this will happen and your users are suffering downtime that you are unaware of.

Fortunately, things have changed in HTML standards and browsers that give developers options to deal with offline access, appcache and service worker. Using these allow you to enhance your web application with limited offline access functionality that improves the usability of your website on mobile devices and meets offline requirements which previously required native applications.

Links:
- [Jake Archibald - Offline cookbook](http://jakearchibald.com/2014/offline-cookbook/)
- [Introduction to Service Worker](http://www.html5rocks.com/en/tutorials/service-worker/introduction/)
- [Video - The ServiceWorker: The network layer is yours to own](https://www.youtube.com/watch?v=4uQMl7mFB6g)
- [Video - Polymer Going offline](https://www.youtube.com/watch?v=BucGrYACJdQ)
- [Service Worker draft specification](https://slightlyoff.github.io/ServiceWorker/spec/service_worker/index.html)
- [W3C samples](https://github.com/w3c-webmob/ServiceWorkersDemos)
- [Making Your App Work Offline: Tips and Cautionary Tales](https://quickleft.com/blog/making-your-app-work-offline-tips-and-cautionary-tales/)
