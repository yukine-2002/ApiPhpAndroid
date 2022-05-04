<?php 
    require_once('config.php');
    
    $fullname = $_GET["fullname"];
    $username = $_GET["username"];
    $password = $_GET["password"];
    $sdt = $_GET["sdt"];
    $diachi = $_GET["diachi"];

$sql = "insert into account (fullname, username, password,sdt,diachi,phanquyen) values('$fullname','$username','$password','$sdt','$diachi',1) ";
if ($con->query($sql)) {
    $response = array();
    $response["success"] = 1;
    $response["message"] = "đăng ký thành công";
  echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "đăng ký that bai" ;
  echo json_encode($response);
}
$con->close();
?>