<?php

include('../core/AModel.php');
require_once '../core/Db.php';

class Answer extends AModel
{

	function __construct()
	{
		self::SetTable('Answers');
		self::SetProperties(array(
			// 'KEY' => DEFAULT_VALUE,
			'Id'=>NULL,
			'Submit'=>NULL,
			'UserId'=>NULL,
			'Choice'=>NULL,
			'QuestionId'=>NULL,
		));
	}
}
?>