<?php

 if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
	$type=$_REQUEST['type'];
	if($type=="new")
	{
		$bbname = $_REQUEST['bbname'];
		
		$conn = new mysqli("mysql.hostinger.in", "u186878343_akash", "akash123", "u186878343_pknrs");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql="SELECT * FROM BloodBankData WHERE Bank_Name ='".$bbname."'";
$result = $conn->query($sql);


if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {

       echo $row['Bank_Name']."!".$row['Address']."*".$row['Phone']."#";
     }
} else {
     echo "no results";
}
$conn->close();
}

    
}



?>