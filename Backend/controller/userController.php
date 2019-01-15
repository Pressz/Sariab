<?php 
include('../core/AController.php');
include('../model/User.php');

class userController extends AController{

	function GET($Role = 'USER'){		
		parent::GET($Role);
		$model = new User();
		if (parent::getRequest('LOGINHELLO') == "true")
		{
			$model->SetValue("Id", $_GET['_LOGGINID']);
		}
		else
		{
			$this->login('ADMIN');
			foreach($model->GetProperties() as $key => $value){
				$model->SetValue($key, parent::getRequest($key));
			}
		}
		$data = $model->Select(-1 , -1, 'IsActive DESC, Id', 'DESC');
		parent::setData($data);
		parent::returnData();
	}

	function POST($Role = 'GUEST'){
		// TODO: register needs access level
		parent::POST($Role);
		$user = new User();	
		foreach($user->GetProperties() as $key => $value){
			$user->SetValue($key, 
				(parent::getRequest($key) == null) ? $value : parent::getRequest($key)
			);
		}
		$user->Insert();
		(new Authentication())->AddAccess("~", "~", "Users.Id", $user->GetProperties()['Id']);
		parent::setData($user->GetProperties());
		parent::returnData();
	}

	function PUT($Role = 'ADMIN')
	{
		// TODO: Change pass needs access level
		parent::PUT($Role);
		$user = new User();
		foreach($user->GetProperties() as $key => $value){
			if (parent::getRequest($key) == null)
				continue;
			$user->SetValue($key, parent::getRequest($key));
			$user->SetOperand($key);
		}
		$user->Update(parent::getRequest("previousId"));
		parent::setData($user->GetProperties());
		parent::returnData();
	}

	function DELETE($Role = 'ADMIN'){
		parent::DELETE($Role);
		$user = new User();
		$user->SetValue("Id", parent::getRequest("Id"));
		$user->Delete();
		(new Authentication())->RemoveAccess("~", "~", "Users.Id", parent::getRequest("Id"));
		parent::setData($user->GetProperties());
		parent::returnData();
	}
}

$user = new userController();
?>