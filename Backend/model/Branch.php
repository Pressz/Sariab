<?php

include('../core/AModel.php');
require_once '../core/Db.php';

class Branch extends AModel
{

	function __construct()
	{
		self::SetTable('Branches');
		self::SetProperties(array(
			// 'KEY' => DEFAULT_VALUE,
			'Id'=>NULL,
			'Name'=>NULL,
			'CityId'=>NULL,
			'UserId'=>NULL,
		));
	}
}
?>