<?php
include_once 'IModel.php';

abstract class AModel implements IModel
{
	private $props = []; // props [0] = key, props [1] = value
	private $operands = []; // props that will go for update
	private $table = ''; // table name in db
	private $pk = 'Id'; // pk in table
	private $pkType = 'Int'; // pk in table
	private $propscount = ''; // how many props passed to methods?

	function IsReserved($Field, $IgnoreAggregate = false)
	{
		return
			(
			($Field == $this->pk && !$IgnoreAggregate) ||
			(substr($Field, 0, 2) == "Is" && !$IgnoreAggregate ) ||
			(substr($Field, 0, 4) == "Code" && !$IgnoreAggregate ) ||
			substr($Field, 0, 3) == "Bin" ||
			substr($Field, 0, 4) == "Hash");
	}
	function DoHash($InputString)
	{
		return password_hash($InputString, PASSWORD_DEFAULT);
	}

	function SetValue($Key, $Value){
		$this->props[$Key] = $Value;
	}
	function SetOperand($Key){
		array_push($this->operands, $Key);	
	}
	function IsOperand($Key){
		return in_array($Key,$this->operands);
	}
	function SetProperties($Properties){
		$this->props = $Properties;
		$this->propscount = sizeof($Properties);
	}
	function GetProperties(){
		return $this->props;
	}
	function SetTable($Table)
	{
		$this->table = $Table;
	}
	function SetPrimaryKey($PrimaryKey)
	{
		$this->pk = $PrimaryKey;
	}
	function SetPrimaryKeyType($PrimaryKeyType)
	{
		$this->pkType = $PrimaryKeyType;
	}

	function Select($Skip = -1 , $Take = -1, $OrderField = 'Id', $OrderArrange = 'ASC', $Clause = '')
	{
		$fields = '';

		foreach($this->GetProperties() as $key => $value)
		{

			if (!$this->IsReserved($key, true))
				$fields .= '`' . $key . '`, ';
			
			if ($value != null)
			{
				if (($key != $this->pk && $value == "✓")
					|| $key == $this->pk)
				{
					if (substr($key, 0, 3) == "Bin")
						$fields .= "TO_BASE64(`" . $key . "`) as " . $key . ", ";
					else
						$fields .= '`' . $key . '`, ';
				}
			}
		}
		$fields = substr($fields, 0, -2);
		$query  = "SELECT " . $fields . " FROM `" . $this->table . "`";
		if ($this->GetProperties()[$this->pk] != null) // TODO: Any parameter that wasn't null
		{
			if (substr($this->pk, 0, 4) == "Code")
				$val = "'" . $this->GetProperties()[$this->pk] . "'";
			else
				$val = $this->GetProperties()[$this->pk];

			$query .= " WHERE `" . $this->pk . "` = " . $val . ";";
		}
		else
		{
			$query .= " " . $Clause . " ORDER BY " . $OrderField . " " . $OrderArrange .
			(($Take == -1)? "" : " LIMIT " . $Take) .
			(($Skip == -1)? "" : " OFFSET " . $Skip)
			. ";";
		}
		$db = new Db();
		$conn = $db->Open();
		$result = mysqli_query($conn, $query);
		if (!$result)
		{
			header("HTTP/1.0 404 Not Found");
			return;
		}
		$num = mysqli_num_rows($result);
		if ($num == 1)
		{
			$i=0;
			$fields = '';
			$values = mysqli_fetch_array($result);
			foreach($this->GetProperties() as $key => $value)
				if (isset($values[$key]))
				{
					// if (substr($key, 0, 3) == "Bin")
					// 	$values[$key] = base64_encode($values[$key]);
					$this->SetValue($key, $values[$key]);
				}
			// Return Single Record
			return $this->GetProperties();
		}
		else if ($num > 1)
		{
			// Return Multiple Rows
			$rows = array();
			while(($row = mysqli_fetch_assoc($result))) {
				$rows[] = $row;
			}
			return $rows;
		}
	}
	function Delete()
	{
		$this->Select();
		$db = new Db();
		$conn = $db->Open();
		if (substr($this->pk, 0, 4) == "Code")
			$val = "'" . $this->GetProperties()[$this->pk] . "'";
		else
			$val = $this->GetProperties()[$this->pk];

		$query  = "DELETE FROM `" . $this->table . "` WHERE " . $this->pk . "=" . $val;
		mysqli_query($conn, $query);
	}
	function Update($previousId)
	{
		$db = new Db();
		$conn = $db->Open();
		$query  = "UPDATE `" . $this->table . "` SET ";
		$i=0;
		foreach($this->GetProperties() as $key => $value)
		{
			if (!$this->IsOperand($key))
				continue;
			
			if ($this->IsReserved($key)
				&& substr($key, 0, 2) == "Is")
			{
				if ($value == "on")
					$value = "1";
				else if ($value == "off")
					$value = "0";
			}		
			else if ($this->IsReserved($key)
				&& substr($key, 0, 4) == "Hash")
			{
				$value = "'" . $this->DoHash($value) . "'";
			}
			else if ($this->IsReserved($key)
				&& substr($key, 0, 4) == "Code")
			{
				$value = "'" . strtoupper($value) . "'";
			}
			else if ($this->IsReserved($key)
				&& substr($key, 0, 3) == "Bin")
			{
				$value = "FROM_BASE64('" . explode(',', urldecode($value))[1] . "')";
			}
			if (isset($value))
				if ($this->IsReserved($key))
					$query .= '`' . $key . "` = " . $value . ", ";
				else
					$query .= '`' . $key . "` = '" . $value . "', ";
		}
		$query = substr($query, 0, -2); // Delete last ,
		if (substr($this->pk, 0, 4) == "Code")
			$val = "'" . $previousId . "'";
		else
			$val = $previousId;
		$query .=" WHERE " . $this->pk . "=" . $val;
		if (!$this->IsOperand($this->pk))
			$this->SetValue($this->pk,$previousId);
		$this->Select();
		mysqli_query($conn, $query);
	}
	function Insert()
	{
		$db = new Db();
		$conn = $db->Open();
		$query  = "INSERT INTO `" . $this->table . "` (";
		$i=0;
		foreach($this->GetProperties() as $key => $value){
			$query .= '`' . $key . '`'. ((++$i === $this->propscount) ? "" : ", " );
		}
		$query = $query . ") VALUES (";
		$i=0;
		foreach($this->GetProperties() as $key => $value){
			if ($this->IsReserved($key)
				&& substr($key, 0, 2) == "Is")
			{
				if ($value == "on")
					$value = "1";
				else if ($value == "off")
					$value = "0";
			}		
			else if ($this->IsReserved($key)
				&& substr($key, 0, 4) == "Hash")
			{
				$value = "'" . $this->DoHash($value) . "'";
			}
			else if ($this->IsReserved($key)
				&& substr($key, 0, 4) == "Code")
			{
				$value = "'" . strtoupper($value) . "'";
			}
			else if ($this->IsReserved($key)
				&& substr($key, 0, 3) == "Bin")
			{
				if ($value != null)
					$value = "FROM_BASE64('" . explode(',', $value)[1] . "')";
				// $value = "'" . base64_decode(explode(',', $value)[1]) . "'";
			}
			if (isset($value))
				if ($this->IsReserved($key) and $this->pkType != "String")
					$query .=  $value;
				else
					$query .= "'" . $value . "'";
			else $query .= "NULL";

			$query .=  ", ";
		}
		$query = substr($query, 0, -2); // Delete last ,
		$query = $query . ");";
		mysqli_query($conn, $query);
		$this->SetValue($this->pk, mysqli_insert_id($conn));
	}
}
?>