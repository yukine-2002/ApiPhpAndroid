<?php 
require_once('config.php');

$sql = "select * from account";
$response = array();
$result = $con -> query($sql);
if($result ->num_rows > 0){
    while($row = $result->fetch_assoc()) {
        $response[]= $row;
      }
    echo json_encode($response);
}else{
    echo json_encode($response);
}