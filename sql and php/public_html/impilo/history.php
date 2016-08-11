	<?php
		$bank=$_GET['bank'];
		
		$conn = new mysqli("mysql.hostinger.in", "u869259413_user", "akash123", "u869259413_db");
		// Check connection
		if ($conn->connect_error) 
		{
  			  die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql="SELECT * FROM BloodRequest WHERE BloodBankName ='".$bank."' and Accept=1";
	
	?>
<head>
	<title>BLOOD REQUEST HISTORY</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function accept(id,bbname)
		{
			window.location="AcceptReject.php?id="+id+"&AR=1&bbname="+bbname;
		}
		function reject(id,bbname){
			window.location="AcceptReject.php?id="+id+"&AR=0&bbname="+bbname;
		}
		function deleteRec(id,bbname,command)
		{
			window.location="deleteRec.php?id="+id+"&bbname="+bbname+"&command="+command;
		}
	</script>
</head>
<body class="container">
		<br/>
		
	<?php
				$result = $conn->query($sql);
				if ($result->num_rows > 0) {
     ?>
	 
	 <div class="well well-sm" style="padding-bottom:20px; vertical-align:middle;" ><?php  echo "You have accepted ".$result->num_rows." request.";?>
 <button type="button"  id="btndel<?php echo $row['Id']; ?>" onclick="deleteRec('<?php echo $row['Id']; ?>','<?php echo $row['BloodBankName']; ?>',1)" class="btn btn-danger btn-sm pull-right"><span class="glyphicon glyphicon-trash"></span>  Delete All</button>
	 </div>
	 <br/>
	 
	 <table class="table table-striped">
		<thead>
			<th>Name</th>
			<th>Hospital</th>
			<th>Address</th>
			<th>Blood Group</th>
			<th>Contact</th>
			<th>Message</th>
			<th></th>
		</thead>
		<tbody>	
	 <?php
     			while($row = $result->fetch_assoc()) {
     			?>
     			<tr>
					<td><?php echo $row['Name']; ?></td>
					<td><?php echo $row['Hospital']; ?></td>
					<td><?php echo $row['Address']; ?></td>
					<td><?php echo $row['BloodGroup']; ?></td>
					<td><?php echo $row['Contact']; ?></td>
					<td><?php echo $row['Message']; ?></td>
					<td>	<button type="button"  id="btndel<?php echo $row['Id']; ?>" onclick="deleteRec('<?php echo $row['Id']; ?>','<?php echo $row['BloodBankName']; ?>',2)" class="btn btn-danger btn-sm pull-right"><span class="glyphicon glyphicon-trash"></span>  Delete</button></td>
				</tr>	
     			<?php		
       			
     			}
			} 
				else { ?>
     				<div class="well well-sm"><?php  echo "You have accepted 0 request!!";?></div>
				<?php	}
				$conn->close();
			?>
			
			
		</tbody>
	</table>
</body>
						