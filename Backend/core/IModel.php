<?php

interface IModel {
	function SetProperties($Properties);
	function GetProperties();
	function SetTable($Table);
	function SetValue($Key, $Value);

	function Select($Skip = 0 , $Take = 10, $OrderField = 'Id', $OrderArrange = 'ASC', $Clause = '');
	function Delete();
	function Update($previousId);
	function Insert();
}
?>