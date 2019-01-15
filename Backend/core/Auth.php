<?php
require_once '../core/Db.php';

class Authentication {

	function CheckHash($InputHash, $StoredHash)
	{
		return password_verify($InputHash, $StoredHash);
	}

    public function IsValid($Username, $Password) {
		$query  = "SELECT " 
		. "`Id`, `HashPassword`, `Type`"
		. " FROM `" . "Users"
		. "` WHERE `IsActive`=1 AND `Username`='" . $Username . "';";
		$db = new Db();
		$conn = $db->Open();
		$result = mysqli_query($conn, $query);
		if (!$result)
			return null;
		$num = mysqli_num_rows($result);
		if ($num === 1)
		{
			$row = mysqli_fetch_assoc($result);
			if ($this->CheckHash($Password, $row['HashPassword'])) {
				return [$row['Id'],$row['Type']];
			}
		}
		return null;
	}
	public function AddAccess($InitKey, $InitValue, $FinalKey, $FinalValue)
	{
		/*
		TODO: Do not insert duplicate
		*/
		$query  = "INSERT INTO `AccessLevels`(`InitKey`,`InitValue`,`FinalKey`,`FinalValue`,`IsOnline`) VALUES('". $InitKey . "','". $InitValue . "','". $FinalKey . "','". $FinalValue . "',b'1');";
		$db = new Db();
		$conn = $db->Open();
		mysqli_query($conn, $query);
	}
	public function RemoveAccess($InitKey, $InitValue, $FinalKey, $FinalValue)
	{
		$query  = "DELETE FROM `AccessLevels` WHERE `InitKey` = '". $InitKey . "' AND `InitValue` = '". $InitValue . "' AND `FinalKey` = '". $FinalKey . "' AND `FinalValue` = '". $FinalValue . "'";
		$db = new Db();
		$conn = $db->Open();
		mysqli_query($conn, $query);
	}
	public function GetDirectAccessList($InitKey, $InitValue, $FinalKey)
	{
		$query  = "SELECT * FROM `AccessLevels` WHERE `InitKey`='" . $InitKey . "' AND `InitValue`='" . $InitValue . "' AND `FinalKey`='" . $FinalKey . "'";
		$db = new Db();
		$conn = $db->Open();
		$result = mysqli_query($conn, $query);
		return mysqli_fetch_assoc($result);
	}
	public function LeavesCount($InitKey, $InitValue = null)
	{
		
		$query  = "SELECT Count(*) as LeavesCount FROM `AccessLevels` WHERE `InitKey`='" . $InitKey . "'";
		if ($InitValue != null)
			$query .= " AND `InitValue`='" . $InitValue . "'";
		$db = new Db();
		$conn = $db->Open();
		
		$result = mysqli_query($conn, $query);
		if (!$result)
			return 0;
		
		$row = $result->fetch_assoc();
		return $row['LeavesCount'];
	}
	
	// TODO: Recursive
	// TODO: Circle Loop

	private $Points = array();
	public function GenerateEntireGraph($Key, $Value = null, $IsLeaf = false)
	{
		$db = new Db();
		$conn = $db->Open();
		$query = "SELECT DISTINCT * FROM `AccessLevels`";
		if ($IsLeaf)
		{
			$query  .= " WHERE `FinalKey`='" . $Key . "'";
			if ($Value != null)
				$query .= " AND `FinalValue`='" . $Value . "'";
			$query .= " ORDER BY `InitKey`";
		}
		else
		{
			if ($this->LeavesCount($Key, $Value) == "0")
				return null;
			$query  .= " WHERE `InitKey`='" . $Key . "'";
			if ($Value != null)
				$query .= " AND `InitValue`='" . $Value . "'";
		}
		$query .= " AND `IsOnline` = 1";
		$results = array();
		$result = mysqli_query($conn, $query);		
		while($row = mysqli_fetch_array($result))
		{
			if ($IsLeaf)
				$results[] = array(
					'Key' => $row['FinalKey'],
					'Value' => $row['FinalValue'],
					'Leaves' => null
				);
			else
			{
				if (in_array($row['Id'], $this->Points))
					return null;
				else
					array_push($this->Points,$row['Id']);
				
				$results[] = array(
					'Key' => $row['InitKey'],
					'Value' => $row['InitValue'],
					'Leaves' => $this->GenerateEntireGraph(
						$row['FinalKey'],
						$row['FinalValue'],
						($this->LeavesCount($row['FinalKey'], $row['FinalValue']) == "0")
					)
				);
			}
		}
		return $results;
		
	}
	public function deep_search($key, $value, $array)
	{
		$kk = '';
		$vv = '';
		$i = 0;
		$result = false;
		foreach($array as $k => $v)
		{
			if ($k === "Key")
				$kk = $v;
			else if ($k === "Value")
				$vv = $v;
			else if ($k === "Leaves")
				if ($result == false)
					$result = $this->deep_search($key, $value, $v);
			$i++;
		}

		if ($kk === $key && $vv === $value)
			$result = true;
		
		return $result;
	}
	public function HasAccess($InitKey, $InitValue, $FinalKey, $FinalValue)
	{
		return
		$this->deep_search($FinalKey, $FinalValue,
			$this->GenerateEntireGraph($InitKey, $InitValue));
	}
}
?>