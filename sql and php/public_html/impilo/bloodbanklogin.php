<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
   	$type=$_REQUEST['type'];
	if($type=="new")
	{
			$bankid=$_REQUEST['bankid'];
		$username=$_REQUEST['username'];
		$password=$_REQUEST['password'];
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 
	
$sql = "SELECT * FROM BloodBankData WHERE Bank_ID='".$bankid."' AND User_Name='".$username."' AND Password='".$password."'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {
         echo "success#".$row['Bank_Name'];
     }
} else {
     echo "0 results";
}
$conn->close();
}
}
?>