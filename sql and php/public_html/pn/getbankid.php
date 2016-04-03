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
		$conn = new mysqli("mysql.hostinger.in", "u186878343_akash", "akash123", "u186878343_pknrs");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 

		



$sql = "SELECT * FROM BloodBankData WHERE  Owner_Name='".$oname."' AND Address='".$address."' AND Email='".$email."' AND User_Name='".$uname."' AND Phone='".$phone."' AND Password='".$password."'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {

       
 mail($email,"POCKET NURSE Registration Acknowledgement!!","Dear ".$oname.", Your Blood bank has been successfully registered to our server and now your bank and bank's blood availability is now visible to our android application users. Kindly update your Blood banks's blood availibility as soon as possible so that the users do not get any fake data. \n\n-------Your  Personal Detail------\n\nYour Bank Id : ".$row['Bank_ID']."\nYor user name :   ".$uname."\nYour password : ".$password."\n\nTHANK YOU!!!");	
	
echo $row['Bank_ID'];
     }
} else {
     echo "no results";
}






$conn->close();
}

    
}



?>