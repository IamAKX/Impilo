<?php
		$bankid=$_GET['id'];
		$btn = $_GET['AR'];
		$bbname = $_GET['bbname'];
		$mail = $_GET['mail'];
		$bgroup = $_GET['bgroup'];
		$custName = $_GET['custname'];
		
	
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) 
		{
  			  die("Connection failed: " . $conn->connect_error);
		} 
		echo $bankid;
		echo $btn;
		if($btn==1)
		{
			$sql="update BloodRequest set Accept=1 WHERE Id =".$bankid;
			$result = $conn->query($sql);
			echo $result;
			mail($mail,"Confirmation for the Blood Request","Dear ".$custName.",\n\tYour request for blood group ".$bgroup." is confirmed and the blood is ready to dispatch from the Blood Bank ".$bbname.". Please contact the blood bank's owner as soon as possible.\n\n You are receiving this email because you the requested for the blood from the app IMPILO.\n\nThank you!\nBlood Bank Owner".$bbname);
			header('location:checkbRequest.php?token=1&bank='.$bbname);
		}
		else{
			$sql="delete from BloodRequest WHERE Id =".$bankid;
			$result = $conn->query($sql);	
			echo $result;	
			header('location:checkbRequest.php?bank='.$bbname);
		}

		
	

?>