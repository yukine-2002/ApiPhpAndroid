<?php 
require_once('config.php');
$id = $_GET['id'];
$fullname = $_GET['fullname'];
$username = $_GET['username'];
$password = $_GET['password'];
$diachi = $_GET['diachi'];
$sdt = $_GET['sdt'];
$sql = "UPDATE account
SET fullname = '$fullname', username = '$username', password = '$password',
diachi = '$diachi',sdt = '$sdt',phanquyen = 1
WHERE id = '$id' ";

if( $con -> query($sql) === true){
    $response = 1;
    echo json_encode($response);
}else{
    $response = 0;
    echo json_encode($response);
}