<?php 
include('../core/AController.php');
include('../model/City.php');

class cityController extends AController{

	function GET($Role = 'GUEST'){
		parent::GET($Role);
		$model = new City();
		foreach($model->GetProperties() as $key => $value){
			$model->SetValue($key, parent::getRequest($key));
		}
		$data = $model->Select(-1 , -1, 'Id', 'DESC');
		parent::setData($data);
		parent::returnData();
	}

	function POST($Role = 'ADMIN'){ 
		parent::POST($Role);
		$model = new City();	
		foreach($model->GetProperties() as $key => $value){
			$model->SetValue($key, 
				(parent::getRequest($key) == null) ? $value : parent::getRequest($key)
			);
		}
		$model->Insert();
		parent::setData($model->GetProperties());
		parent::returnData();
	}

	function PUT($Role = 'ADMIN')
	{
		parent::PUT($Role);
		$model = new City();
		foreach($model->GetProperties() as $key => $value){
			if (parent::getRequest($key) == null)
				continue;
			$model->SetValue($key, parent::getRequest($key));
			$model->SetOperand($key);
		}
		$model->Update(parent::getRequest("previousId"));
		parent::setData($model->GetProperties());
		parent::returnData();
	}

	function DELETE($Role = 'ADMIN'){
		parent::DELETE($Role);
		$model = new City();
		$model->SetValue("Id", parent::getRequest("Id"));
		$model->Delete();
		parent::setData($model->GetProperties());
		parent::returnData();
	}
}

$city = new cityController();
?>