importScripts('/serviceworker-cache-polyfill.js');

self.oninstall = function(event) {
  console.log('Installed!');
  self.skipWaiting();

  event.waitUntil(
    caches.open("OFFLINE").then(function(cache) {
      return cache.addAll([
        './',
        'main.js'
      ]);
    })
  );
};

self.onactivate = function(event) {
  console.log('Activated!');
};

self.onfetch = function(event) {
  console.log('Fetching!: ' + event.request.url);
  var requestURL = new URL(event.request.url);

  event.respondWith(
    caches.match(event.request, {
      ignoreVary: true
    })
  );
};