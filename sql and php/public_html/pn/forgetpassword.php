<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
   	$type=$_REQUEST['type'];
	if($type=="new")
	{
		$bid=$_REQUEST['bid'];
		$name=$_REQUEST['name'];
		$email=$_REQUEST['email'];
		$username=$_REQUEST['username'];
		$phone=$_REQUEST['phone'];
		$conn = new mysqli("mysql.hostinger.in", "u186878343_akash", "akash123", "u186878343_pknrs");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 
	
$sql = "SELECT * FROM BloodBankData WHERE Bank_ID='".$bid."' AND Owner_Name='".$name."' AND Email='".$email."' AND User_Name='".$username."' AND Phone='".$phone."'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {

      mail($email,"Password recovery","Dear ".$name.", Your password have been recovered from our database and your identity is found to be true. Your Password is : ".$row['Password']);
	
echo $row['Password'];
     }
} else {
     echo "fail";
}
$conn->close();
}
}
?>