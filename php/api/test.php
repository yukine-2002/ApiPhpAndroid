<?php
$servername = "localhost";
$username = "root";
$password = "161511";
$dbname = "demo";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT id, name, address FROM student";
$result = $conn->query($sql);
$temparray = array();
if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    $temparray[] = $row;
  }
  echo json_encode($temparray[1]["name"]);
} else {
  echo "0 results";
}
$conn->close();
?>