<?php

 if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
	$type=$_REQUEST['type'];
	if($type=="new")
	{
		$name = $_REQUEST['name'];

		$hospital=$_REQUEST['hospital'];

                $bbank = $_REQUEST['bbank'];
		$address=$_REQUEST['address'];

		$bgroups=$_REQUEST['bgroups'];
		$contact=$_REQUEST['contact'];
		$message=$_REQUEST['message'];

		$date=$_REQUEST['date'];
                $custEmail = $_REQUEST['custEmail'];
	
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 

		$sql = "INSERT INTO BloodRequest(Name,Hospital,Address,BloodGroup,Contact,Message,Date,BloodBankName,CustEmail) 
VALUES ('".$name."' , '".$hospital."','".$address."','".$bgroups."', '".$contact."','".$message."','".$date."','".$bbank."','".$custEmail."')";
if ($conn->query($sql) === TRUE) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}   
}
?>	