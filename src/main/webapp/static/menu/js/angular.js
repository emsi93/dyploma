console.log(list);
var app = angular.module("myModule", []).controller("myController",function($scope){
		var bankAccounts = list;
		$scope.bankAccounts = bankAccounts;
	});