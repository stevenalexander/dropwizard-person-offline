importScripts('/serviceworker-cache-polyfill.js');

var CACHE_NAME = 'OFFLINE';
var urlsToCache = [
  './',
  '/css/bootstrap/3.3.4/bootstrap.min.css',
  '/js/libs/bootstrap/3.3.4/bootstrap.min.js',
  '/js/libs/jquery/1.11.2/jquery.min.js',
  '/js/main.js',
  '/favicon.ico'
];

self.oninstall = function(event) {
  console.log('Installed!');
  self.skipWaiting();

  event.waitUntil(
    caches.open(CACHE_NAME).then(function(cache) {
      return cache.addAll(urlsToCache);
    })
  );
};

self.onactivate = function(event) {
  console.log('Activated!');
};

self.onfetch = function(event) {
  console.log('Fetching!: ' + event.request.url);
  event.respondWith(
    caches.match(event.request)
      .then(function(response) {
        if (response) {
          console.log('Fetched from cache!: ' + event.request.url);
          return response;
        }

        console.log('Requesting!: ' + event.request.url);
        return fetch(event.request);
      }
    )
  );
};
