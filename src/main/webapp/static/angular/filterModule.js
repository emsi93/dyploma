var app = angular
		.module("myModule",[])
		.controller("myController", function($scope){
			var data = datas;
			$scope.data = data;
			$scope.reverseSort = false;
			$scope.sortData = function(columnName){
				$scope.reverseSort = ($scope.sortColumn == columnName) ? !$scope.reverseSort: false;
				$scope.sortColumn = columnName;
			};
	});