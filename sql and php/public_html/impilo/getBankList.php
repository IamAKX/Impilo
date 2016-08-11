<?php
  
if ($_SERVER["REQUEST_METHOD"] == "POST") {
   	$type=$_REQUEST['type'];
	if($type=="new")
	{
	$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) 
		{
		
  			  die("Connection failed: " . $conn->connect_error);
		} 
	
	$sql = "SELECT DISTINCT Bank_Name FROM BloodBankData ORDER BY Bank_Name";

	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
	echo $result->num_rows ."#.--Select Bank--#";
     // output data of each row
     while($row = $result->fetch_assoc()) {
 	 
		echo $row['Bank_Name']."#";
     }
	 } else 
	 {
     echo "No Bank found.#";
	}
	$conn->close();
	}
}
?>	