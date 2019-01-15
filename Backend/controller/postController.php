<?php 
include('../core/AController.php');
include('../model/Post.php');

class postController extends AController{

	function GET($Role = 'GUEST'){
		parent::GET($Role);
		$model = new Post();
		foreach($model->GetProperties() as $key => $value){
			$model->SetValue($key, parent::getRequest($key));
		}
		$data = $model->Select(-1 , -1, 'Id', 'DESC');
		parent::setData($data);
		parent::returnData();
	}

	function POST($Role = 'OPERATOR'){ 
		parent::POST($Role);
		$model = new Post();
		foreach($model->GetProperties() as $key => $value){
			$model->SetValue($key, 
				(parent::getRequest($key) == null) ? $value : parent::getRequest($key)
			);
		}
		$model->Insert();
		parent::setData($model->GetProperties());
		parent::returnData();
	}

	function PUT($Role = 'OPERATOR')
	{
		parent::PUT($Role);
		$model = new Post();
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

	function DELETE($Role = 'OPERATOR'){
		parent::DELETE($Role);
		$model = new Post();
		$model->SetValue("Id", parent::getRequest("previousId"));
		$model->Delete();
		parent::setData($model->GetProperties());
		parent::returnData();
	}
}

$controller = new postController();
?>