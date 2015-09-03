if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/serviceworker.js').then(function(reg) {
    console.log('Service worker installed!', reg);
  }).catch(function(err) {
    console.log('Failed to install Service worker!', reg);
    $("#no-service-worker-text").show();
  });
} else {
  console.log('Browser does not support Service worker!');
  $("#no-service-worker-text").show();
}
