<?php
require_once 'Config.php';
use core\Config;

class Db
{
	function Open()
	{
		return
		new mysqli(Config::ConnectionString_SERVER,Config::ConnectionString_USERNAME,Config::ConnectionString_PASSWORD,Config::ConnectionString_DATABASE);
	}
	function Close()
	{
		try {
		    mysqli_close($conn);
		}
		catch (Exception $exp) { }
	}
	function Log($Notes, $UserId, $Connection){
		$conn = $this->Open();
		$query  = "INSERT INTO `Logs` (`Content`, `UserId`, `Event`, `Agent`, `Connection`) VALUES ('" .
		mysqli_real_escape_string($conn, $Notes ) 
		. "', " . $UserId . ", NOW(), 'API', '" . 
		mysqli_real_escape_string($conn, $Connection ) 
		. "');";
		mysqli_query($conn, $query);
	}
}
?>
