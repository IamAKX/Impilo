<?php

 if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
	$type=$_REQUEST['type'];
	if($type=="new")
	{
		$name = $_REQUEST['uname'];
		$gender=$_REQUEST['gender'];
		$email=$_REQUEST['email'];
		$gurl=$_REQUEST['gurl'];
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 

		$sql = "INSERT INTO user(Username,Gender, Email, GLink) 
VALUES ('".$name."' , '".$gender."','".$email."','".$gurl."')";

if ($conn->query($sql) === TRUE) {
    echo "Success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}

    
}



?>