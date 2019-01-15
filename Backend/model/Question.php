<?php

include('../core/AModel.php');
require_once '../core/Db.php';

class Question extends AModel
{

	function __construct()
	{
		self::SetTable('Questions');
		self::SetProperties(array(
            'Id' => NULL,
            'Submit' => NULL,
            'Title' => NULL,
            'Choice1' => NULL,
            'Choice2' => NULL,
            'Choice3' => NULL,
            'Choice4' => NULL,
		));
	}
}
?>