<?php 
include('../core/AController.php');
include_once '../core/Auth.php';

class dashboardController extends AController{

	function GET($Role = 'USER'){
            // TODO: if role was admin, generateentiregraph from ~

		// parent::setData(
            // (new Authentication)
            // ->GenerateEntireGraph("Users.Id"
            //       ,parent::getRequest('UserId')
            // ));

            parent::setData(
            (new Authentication)
            ->GenerateEntireGraph('~'
            ));

		parent::returnData();
    }
}
$dashboard = new dashboardController();
?>