<?php

interface IController{

	const resultType = 'JSON';

	function getRequest($index);
	function setData($Data);
	function returnData();

	function GET($Role = 'GUEST');
	function POST($Role = 'GUEST');
	function PUT($Role = 'GUEST');
	function DELETE($Role = 'GUEST');
}

?>