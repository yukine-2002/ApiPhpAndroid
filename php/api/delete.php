<?php 
require_once('config.php');
$id = $_GET['id'];
$sql = "DELETE FROM account WHERE id = '$id';";

if( $con -> query($sql) === true){
    $response = 1;
    echo json_encode($response);
}else{
    $response = 0;
    echo json_encode($response);
}