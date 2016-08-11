<?php

 if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
	$type=$_REQUEST['type'];
	if($type=="new")
	{
		$bbname = $_REQUEST['bbname'];
		$oname=$_REQUEST['oname'];
		$address=$_REQUEST['address'];
		$email=$_REQUEST['email'];
		$uname=$_REQUEST['uname'];
		$phone=$_REQUEST['phone'];
		$password=$_REQUEST['password'];
		$zero="0";
		//servername,username,password,databasename
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 

		$sql = "INSERT INTO BloodBankData(Bank_Name,Owner_Name,Address,Email,User_Name,Phone,Password,Ap,Am,Bp,Bm,Op,Om,ABp,ABm) 
VALUES ('".$bbname."' , '".$oname."','".$address."','".$email."', '".$uname."','".$phone."','".$password."','".$zero."','".$zero."','".$zero."','".$zero."','".$zero."','".$zero."','".$zero."','".$zero."')";
 echo $row['Bank_ID'];
if ($conn->query($sql) === TRUE) {
    echo $row['Bank_ID'];
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}

    
}



?>