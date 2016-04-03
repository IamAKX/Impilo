<?php
		$bankid=$_GET['id'];
		$command = $_GET['command'];
		$bbname = $_GET['bbname'];
	
		$conn = new mysqli("mysql.hostinger.in", "u186878343_akash", "akash123", "u186878343_pknrs");
		// Check connection
		if ($conn->connect_error) 
		{
  			  die("Connection failed: " . $conn->connect_error);
		} 
		echo $bankid;
		echo $btn;
		if($command==1)
		{
			$sql="delete from BloodRequest where Accept=1";
			$result = $conn->query($sql);
			echo $result;
			header('location:history.php?token=1&bank='.$bbname);
		}
		else{
			$sql="delete from BloodRequest where Accept=1 and Id =".$bankid;
			$result = $conn->query($sql);	
			echo $result;	
			header('location:history.php?bank='.$bbname);
		}

		
	

?>