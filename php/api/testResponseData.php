<?php 
$serverName = "localhost";
$userName = "root";
$password = "161511";
$dbname = "demo";

$mysql = new mysqli($serverName,$userName,$password,$dbname);

    $request = $_GET['data'];
    
    echo json_encode($request);