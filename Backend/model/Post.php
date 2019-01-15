<?php

include('../core/AModel.php');
require_once '../core/Db.php';

class Post extends AModel
{
	function __construct()
	{
		self::SetTable('Posts');
		self::SetProperties(array(
			'Id'=>NULL,
			'Title'=>NULL,
			'Description'=>NULL,
			'BinImage'=>NULL,
			'Type'=>NULL
		));
	}
}
?>