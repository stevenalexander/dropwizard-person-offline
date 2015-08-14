<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Offline support via Service Worker</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="/css/bootstrap/3.3.4/bootstrap.min.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="/js/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="/js/libs/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <link rel="icon" href="/favicon.ico">
</head>
<body>

<nav class="navbar navbar-inverse" style="border-radius: 0;">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Offline support</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
    </div><!--/.navbar-collapse -->
  </div>
</nav>

<div class="container">

  <@page_body/>

  <hr>

  <footer>
    <p>Steven Alexander (MIT license)</p>
  </footer>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/libs/bootstrap/3.3.4/bootstrap.min.js"></script>

<script src="/js/main.js"></script>
</body>
</html>
