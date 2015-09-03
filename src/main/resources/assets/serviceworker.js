importScripts('/serviceworker-cache-polyfill.js');

var CACHE_NAME = 'OFFLINE';
var CACHE_UNAVAILABLE = '/unavailable.htm';

var urlsToCache = [
  './',
  '/css/bootstrap/3.3.4/bootstrap.min.css',
  '/js/libs/bootstrap/3.3.4/bootstrap.min.js',
  '/js/libs/jquery/1.11.2/jquery.min.js',
  '/js/main.js',
  '/favicon.ico',
  CACHE_UNAVAILABLE
];

var AvailableOfflineUrlPatterns = [
  '/persons$',
  '/persons/[0-9]*$'
];

var UnavailableOfflineUrlPatterns = [
  '/persons/add',
  '/persons/[0-9]*/edit$',
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
  if (hasMatchInPatternArray(event.request.url, AvailableOfflineUrlPatterns)) {
    event.respondWith(fetchAndCacheWithCacheAsBackup(event.request));
  } else if (hasMatchInPatternArray(event.request.url, UnavailableOfflineUrlPatterns)) {
    event.respondWith(fetchWithUnavailableAsBackup(event.request));
  } else {
    event.respondWith(getCachedPage(event.request));
  }
};

function hasMatchInPatternArray(str, patternArray) {
  for (var i = 0; i < patternArray.length; i++) {
    if (str.match(patternArray[i])) {
      return true;
    }
  }
  return false;
}

function getCachedPage(request) {
  return caches.match(request).then(function(response) {
    if (response) {
      return response;
    }
    return fetch(request);
  });
}

function fetchAndCacheWithCacheAsBackup(request) {
  return fetch(request).then(function(response) {
    if (response) {
      caches.open(CACHE_NAME).then(function(cache) {
        console.log('Cached request: ' + request.url);
        markOfflineContent(response.clone()).then(function(modifiedResponse) {
          cache.put(request, modifiedResponse);
        });
      });
      console.log('Fetched request: ' + request.url);
      return response;
    }

    return caches.match(request).then(function(cachedResponse) {
      console.log('Using cached response: ' + request.url);
      return cachedResponse;
    });
  }, function(error) {
    return caches.match(request).then(function(cachedResponse) {
        console.log('Using cached response: ' + request.url);
        return cachedResponse;
    });
  });
}

function fetchWithUnavailableAsBackup(request) {
  return fetch(request).then(function(response) {
    if (response) {
      console.log('Fetched request: ' + request.url);
      return response;
    }

    return caches.match(CACHE_UNAVAILABLE).then(function(cachedResponse) {
      console.log('Using unavailable response: ' + request.url);
      return cachedResponse;
    });
  }, function(error) {
   return caches.match(CACHE_UNAVAILABLE).then(function(cachedResponse) {
       console.log('Using unavailable response: ' + request.url);
       return cachedResponse;
   });
  });
}

function markOfflineContent(response) {
  // Response body is a stream so must be read then modified and turned into a blob
  return response.text().then(function(content) {
    var now = new Date();
    var modifiedContent = content.replace("hide-offline-text", "offline-text");
    var modifiedContent = modifiedContent.replace("CACHE_DATETIME", now.toLocaleDateString() + " " + now.toLocaleTimeString());
    var modifiedContent = modifiedContent.replace("offline-hide", "hide");
    var blob = new Blob([modifiedContent], { type: 'text/html' });
    return new Response(blob, { headers: response.headers });
  });
}
