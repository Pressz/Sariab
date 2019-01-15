<?php 
include('../core/AController.php');

class BridgeController extends AController{

	function GET($Role = 'GUEST'){
		parent::GET($Role);
		$query  = file_get_contents("../scripts/" . parent::getRequest("Id"));
		
		if (parent::getRequest("Params") != null && parent::getRequest("Params") != "")
		{
			$params = substr(parent::getRequest("Params"), 1, -1);
			$paramsArray = explode(',', $params);
			for ($i = 0; $i < sizeof($paramsArray); $i++ )
			{
				$fieldsArray = explode(':', $paramsArray[$i]);;
				$query = str_replace("@" . $fieldsArray[0], $fieldsArray[1], $query);
			}
		}

		$db = new Db();
		$conn = $db->Open();
		$result = mysqli_query($conn, $query);
		$num = mysqli_num_rows($result);
        $rows = [];
        while(($row = mysqli_fetch_assoc($result))) {
            $rows[] = $row;
		}
		
		parent::setData($rows);
		parent::returnData();
	}
}

$bridge = new BridgeController();
?>