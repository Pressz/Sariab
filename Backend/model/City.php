<?php

include('../core/AModel.php');
require_once '../core/Db.php';

class City extends AModel
{

	function __construct()
	{
		self::SetTable('Cities');
		self::SetProperties(array(
			// 'KEY' => DEFAULT_VALUE,
			'Id'=>NULL,
			'Name'=>NULL,
			'Country'=>NULL,
		));
	}
}
?>