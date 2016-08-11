<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
   	$type=$_REQUEST['type'];
	if($type=="new")
	{
			
		$bname=$_REQUEST['bname'];
		$bphone=$_REQUEST['bphone'];
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 
	
$sql = "SELECT * FROM BloodBankData WHERE Bank_Name='".$bname."' AND Phone='".$bphone."'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {

       
	$ap = $row['Ap']."A";
	$am = $row['Am']."B";
	$bp = $row['Bp']."C";
	$bm = $row['Bm']."D";
	$op = $row['Op']."E";
	$om = $row['Om']."F";
	$abp = $row['ABp']."G";
	$abm = $row['ABm'];
	
	
echo $ap.$am.$bp.$bm.$op.$om.$abp.$abm;
     }
} else {
     echo "0 results";
}
$conn->close();
}
}
?>