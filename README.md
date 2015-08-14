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

## Details

This simple example uses the the Service Worker to front load and cache the root URL (index.htm) and main.js. This means
that the first time you visit the site the Service Worker (in serviceworker.js) will request these URLs and cache the
response. The next time you request either of these URLs the Service Worker will be running and will intercept the
events and return the response from the cache EVEN if you are offline or cannot hit the server.

Expanding this approach you can selectively cache responses and depending on if the client can hit the server use
responses from the cache to avoid client issues when the user loses connection, such as during a form submission.

### Testing offline locally using npm [localtunnel](https://www.npmjs.com/package/localtunnel)

Requires NPM.

Testing locally can be difficult, as disconnecting your wifi/network will not affect requests to localhost. Instead you
can use localtunnel to create a temporary tunnel to the port on your machine and open/close it to simulate the effect of
losing connection.

```
# install localtunnel
npm install -g localtunnel

# run application
./gradlew run

# create localtunnel for faking
lt --port 8090

# 1. Goto the url for the tunnel in your browser, which should display "Offline"
# 2. Close the tunnel
# 3. Goto the url for the tunnel in your browser, which should still display "Offline" as Service Worker has cached the response
```

### Testing offline in Heroku

Requires [Heroku toolkit](https://toolbelt.heroku.com/).

```
# Deploy to Heroku
heroku login
heroku create
git push heroku master
heroku ps:scale web=1
heroku open
```

Try visiting the site and then disconnecting your wifi/network then visiting again.
Service Worker will cache the response, allowing you to see the page offline.

### Notes
- Service Worker scope controls what requests it can handle and by default it is fixed to the relative path it is served from, e.g. http://localhost:8090/assets/serviceworker.js can only affect requests under http://localhost:8090/assets/. You can override this by serving it with an explicit header `Service-Worker-Allowed`, but it was difficult to do in Dropwizard with the way the AssetsBundle works so I moved assets to use root url.

## Links

- [Jake Archibald - Offline cookbook](http://jakearchibald.com/2014/offline-cookbook/)
- [Introduction to Service Worker](http://www.html5rocks.com/en/tutorials/service-worker/introduction/)
- [Video - The ServiceWorker: The network layer is yours to own](https://www.youtube.com/watch?v=4uQMl7mFB6g)
- [Video - Polymer Going offline](https://www.youtube.com/watch?v=BucGrYACJdQ)
- [Service Worker draft specification](https://slightlyoff.github.io/ServiceWorker/spec/service_worker/index.html)
- [W3C samples](https://github.com/w3c-webmob/ServiceWorkersDemos)
- [Making Your App Work Offline: Tips and Cautionary Tales](https://quickleft.com/blog/making-your-app-work-offline-tips-and-cautionary-tales/)
