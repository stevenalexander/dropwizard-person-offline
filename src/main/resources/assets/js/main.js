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

// Online heartbeat check
(function poll(){
   setTimeout(function(){
      $.ajax({ url: "/persons/heartbeat", timeout: 2000, success: function(){
        $("#offline-text").hide();
        poll();
      }, error: function() {
        $("#offline-text").show();
        poll();
      }});
  }, 10000);
})();
