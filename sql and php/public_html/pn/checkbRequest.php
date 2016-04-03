	<?php
		$bank=$_GET['bank'];
		
		$conn = new mysqli("mysql.hostinger.in", "u186878343_akash", "akash123", "u186878343_pknrs");
		// Check connection
		if ($conn->connect_error) 
		{
  			  die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql="SELECT * FROM BloodRequest WHERE BloodBankName ='".$bank."' and Accept=0";
	
	?>
<head>
	<title>BLOOD REQUEST</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	


		<?php if (isset($_GET['token'])): 
				
		echo "<script>
$(document).ready(function(){
  
        $('#myModal').modal();
  
});



				
			</script>";
		
	 endif; ?>
		<script type="text/javascript">
		function accept(mail,custname,bgroup,id,bbname)
		{	
						
			window.location="AcceptReject.php?id="+id+"&AR=1&bbname="+bbname+"&mail="+mail+"&custname="+custname+"&bgroup="+bgroup;
			
		}				
		function reject(id,bbname){
			window.location="AcceptReject.php?id="+id+"&AR=0&bbname="+bbname;
		}
	</script>
</head>
<body class="container">
		<br/>
		
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
    
      <!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Blood request accepted from this user and confirmation email is sent to customer.</h4>
				</div>
			</div>
		</div>
		</div>

	
			<?php
				$result = $conn->query($sql);
				if ($result->num_rows > 0) {
     ?>
	 <table class="table table-striped">
		<thead>
			<th>Name</th>
			<th>Hospital</th>
			<th>Address</th>
			<th>Blood Group</th>
			<th>Contact</th>
			<th>Message</th>
			<th>Deadline</th>
			<th></th>
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
					<td><?php echo $row['Date']; ?></td>
					<td><button type="button" id="btnys<?php echo $row['Id']; ?>" onclick="accept('<?php echo $row['CustEmail']; ?>','<?php echo $row['Name']; ?>','<?php echo $row['BloodGroup']; ?>','<?php echo $row['Id']; ?>','<?php echo $row['BloodBankName']; ?>')" class="btn btn-success">Accept</button></td>
					<td><button type="button" id="btnys<?php echo $row['Id']; ?>" onclick="reject('<?php echo $row['Id']; ?>','<?php echo $row['BloodBankName']; ?>')" class="btn btn-danger">Reject</button></td>
				</tr>	
     			<?php		
       			
     			}
			} 
				else { ?>
     				<div class="well well-sm"><?php  echo "No Request!!";?></div>
				<?php	}
				$conn->close();
			?>
			
			
		</tbody>
	</table>
</body>
						