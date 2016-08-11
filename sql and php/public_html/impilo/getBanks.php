<?php
class connect{
	
				private $db_name="u186878343_pknrs";
				private $db_host="mysql.hostinger.in";
				private $db_username="u186878343_akash";
				private $db_password="akash123";
				protected $con;
				private $db;
		function __construct()
				{
					$this->con=@mysql_connect($this->db_host,$this->db_username,$this->db_password) or die ('unable to connect to the server');	
					$this->db=mysql_select_db($this->db_name,$this->con) or die('Unable to connect to the database');										
				}
		function display_all()
				{					
					$query="SELECT * FROM BloodBankData";
					$val=mysql_query($query) or die(mysql_error());
					return $val;	
				}
			}
			
		$obj=new connect();
		$res2=$obj->display_all();
		while ($row = mysql_fetch_array($res2))
		{
     		echo $row['Bank_Name']."!".$row['Address']."*".$row['Phone']."#";
		}
			

?>