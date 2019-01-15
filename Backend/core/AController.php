<?php
error_reporting(E_ALL);
ini_set('display_errors', '1');

include_once 'IController.php';
include_once 'Auth.php';

abstract class AController implements IController{

	private $data = '' ;
	private $request = [];

	function login($Role = null)
	{
		$query = [];
		$parts = parse_url($_SERVER['REQUEST_URI']);
		if (isset($parts['query']))
			parse_str($parts['query'], $query);
		

		if ($Role == 'GUEST' or $Role == null)
		{
			return null;
		}
		else if (isset($query['Username']) && isset($query['Password']))
		{
			$LoginResult = (new Authentication())->IsValid($query['Username'], $query['Password']);
			$_GET['_LOGGINID'] = $LoginResult[0];
			if
			(
				!(
					($Role != null)
					and
					(
						// ($Role != 'USER' and $Role != 'OPERATOR' and $Role != 'ADMIN') or
						($_GET['_LOGGINID'] == null) or
						($LoginResult[1] == 'USER' and $Role == 'OPERATOR') or
						(($LoginResult[1] == 'USER' or $LoginResult[1] == 'OPERATOR') and $Role == 'ADMIN')
					)
				)
			)
			{
				return $_GET['_LOGGINID'];
			}
		}
		header("HTTP/1.1 401 Unauthorized");
		exit;
	}

	function __construct(){
		// (new Db())->Log(json_encode($_REQUEST), $this->login(), json_encode(apache_request_headers()));	
		$this->{$_SERVER['REQUEST_METHOD']}();
	}
	function __destruct(){
		// TODO: What to do. What not to do? -Arastoo Amel!
	}
	function setData($data){
		$this->data = $data;
	}
	function setRequest($request){
		$this->request = $request;
	}
	function getAllRequests(){
		return $this->request;
	}
	function getRequest($index){
		return (isset($this->request[$index])?$this->request[$index]:null);
	}
	function returnData (){
		if (constant('self::'.'resultType')=='JSON')
		{
			header("Content-Type: application/json");
			echo json_encode($this->data);
		}
		else echo $this->data;
	}
	function GET($Role = 'GUEST'){
		$this->login($Role);

		$this->setRequest($_GET);
	}
	function POST($Role = 'GUEST'){
		$this->login($Role);

		$this->setRequest($_POST);
	}
	function DELETE($Role = 'GUEST'){
		$this->login($Role);
		
		$raw_data = file_get_contents('php://input');
		$_DELETE = array();
		$boundary = substr($raw_data, 0, strpos($raw_data, "\r\n"));
		if ($boundary == null) // x-www-form-urlencoded
		{
			$split_parameters = explode('&', $raw_data);

			for($i = 0; $i < count($split_parameters); $i++) {
				$final_split = explode('=', $split_parameters[$i]);
				$_DELETE[$final_split[0]] = $final_split[1];
			}
		}
		else // form-data
		{
			$parts = array_slice(explode($boundary, $raw_data), 1);
			foreach ($parts as $part) {
				if ($part == "--\r\n") break; 
				$part = ltrim($part, "\r\n");
				list($raw_headers, $body) = explode("\r\n\r\n", $part, 2);
				$raw_headers = explode("\r\n", $raw_headers);
				$headers = array();
				foreach ($raw_headers as $header) {
					list($name, $value) = explode(':', $header);
					$headers[strtolower($name)] = ltrim($value, ' '); 
				}
				if (isset($headers['content-disposition'])) {
					$filename = null;
					preg_match(
						'/^(.+); *name="([^"]+)"(; *filename="([^"]+)")?/', 
						$headers['content-disposition'], 
						$matches
					);
					list(, $type, $name) = $matches;
					isset($matches[4]) and $filename = $matches[4]; 
					switch ($name) {
						case 'userfile':
							file_put_contents($filename, $body);
							break;
						default: 
							$_DELETE[$name] = substr($body, 0, strlen($body) - 2);
							break;
					} 
				}

			}
		}
		$this->setRequest($_DELETE);
	}
	function PUT($Role = 'GUEST'){
		$this->login($Role);

		$raw_data = file_get_contents('php://input');
		$_PUT = array();
		$boundary = substr($raw_data, 0, strpos($raw_data, "\r\n"));
		if ($boundary == null) // x-www-form-urlencoded
		{
			$split_parameters = explode('&', $raw_data);

			for($i = 0; $i < count($split_parameters); $i++) {
				$final_split = explode('=', $split_parameters[$i]);
				$_PUT[$final_split[0]] = $final_split[1];
			}
		}
		else // form-data
		{
			$parts = array_slice(explode($boundary, $raw_data), 1);
			foreach ($parts as $part) {
				if ($part == "--\r\n") break; 
				$part = ltrim($part, "\r\n");
				list($raw_headers, $body) = explode("\r\n\r\n", $part, 2);
				$raw_headers = explode("\r\n", $raw_headers);
				$headers = array();
				foreach ($raw_headers as $header) {
					list($name, $value) = explode(':', $header);
					$headers[strtolower($name)] = ltrim($value, ' '); 
				}
				if (isset($headers['content-disposition'])) {
					$filename = null;
					preg_match(
						'/^(.+); *name="([^"]+)"(; *filename="([^"]+)")?/', 
						$headers['content-disposition'], 
						$matches
					);
					list(, $type, $name) = $matches;
					isset($matches[4]) and $filename = $matches[4]; 
					switch ($name) {
						case 'userfile':
							file_put_contents($filename, $body);
							break;
						default: 
							$_PUT[$name] = substr($body, 0, strlen($body) - 2);
							break;
					} 
				}

			}
		}
		$this->setRequest($_PUT);
	}
}
?>