<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
   	$type=$_REQUEST['type'];
	if($type=="new")
	{
			$bankid=$_REQUEST['id'];
			$ap=$_REQUEST['ap'];
			$am=$_REQUEST['am'];
			$bp=$_REQUEST['bp'];
			$bm=$_REQUEST['bm'];
			$op=$_REQUEST['op'];
			$om=$_REQUEST['om'];
			$abp=$_REQUEST['abp'];
			$abm=$_REQUEST['abm'];
		
		
		
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) {
  			  die("Connection failed: " . $conn->connect_error);
		} 
	

$sql = "update BloodBankData set Ap='".$ap."',Am='".$am."',Bp='".$bp."',Bm='".$bm."',Op='".$op."',Om='".$om."',ABp='".$abp."',ABm='".$abm."' where Bank_ID='".$bankid."'";
if ($conn->query($sql) === TRUE) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}   
}
?>