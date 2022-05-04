<?php 
require_once('config.php');

$username = $_GET["username"];
$password = $_GET["password"];

$sql = "select * from account where username = '$username' and password = '$password'";
$response = array();
$result = $con -> query($sql);
if($result ->num_rows > 0){
    $response["success"] = 1;
    $response["message"] = "đăng nhập thành công";
    $response["infoUser"] = $result -> fetch_assoc();
    echo json_encode($response);
}else{
    $response["success"] = 0;
    $response["message"] = "đăng nhập thất bại";
    echo json_encode($response);
}