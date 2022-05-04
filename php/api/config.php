<?php
define('HOST','localhost');
define('USER','root');
define('PASS','161511');
define('DB','demo');

$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');