<?php 
require_once('config.php');
$fullname = $_GET['fullname'];
$username = $_GET['username'];
$password = $_GET['password'];
$diachi = $_GET['diachi'];
$sdt = $_GET['sdt'];
$sql = "insert into account (fullname,username,password,sdt,diachi,phanquyen)
values('$fullname','$username','$password','$sdt','$diachi',1)";
echo $sql;
if( $con -> query($sql) === true){
    $response = 1;
    echo json_encode($response);
}else{
    $response = 0;
    echo json_encode($response);
}