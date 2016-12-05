
/*
 * This controller contains all operation related to Campaign Screen
 * Created By : Marshall, Saranya
 * Created Date: 11/24/2016
 */

'use strict';

myapp.controller('UsersController', function($scope, $modal, $http, $q, UsersService, $mdDialog, $window) {
					
	$scope.user = {};
	$scope.searchUser = {};
	$scope.pagination = {
		    currentPage: 1
		};
	$scope.limit = 10;
	$scope.roleList = null;
	$scope.userList = [];

	//Gets the User List
	function getUserList() {

	    var firstName = '';
	    var lastName = '';
	    var emailAddress = '';
	    var phoneNumber = '';
	    var roleName = '';

	    var deferred = $q.defer();

	    if ($scope.searchUser.firstName != undefined && $scope.searchUser.firstName != '' && $scope.searchUser.firstName != 'undefined') {
	        firstName = $scope.searchUser.firstName;
	    }
	    if ($scope.searchUser.lastName != undefined && $scope.searchUser.lastName != '' && $scope.searchUser.lastName != 'undefined') {
	        lastName = $scope.searchUser.lastName;
	    }
	    if ($scope.searchUser.emailAddress != undefined && $scope.searchUser.emailAddress != '' && $scope.searchUser.emailAddress != 'undefined') {
	        emailAddress = $scope.searchUser.emailAddress;
	    }
	    if ($scope.searchUser.phoneNumber != undefined && $scope.searchUser.phoneNumber != '' && $scope.searchUser.phoneNumber != 'undefined') {
	        phoneNumber = $scope.searchUser.phoneNumber;
	    }
	    if ($scope.searchUser.role) {
	        roleName = $scope.searchUser.role["id"];
	    }


	    var begin = (($scope.pagination.currentPage - 1) * $scope.limit),
	        end = begin + $scope.limit;

	    var parameters = {
	        startRecord: begin,
	        recordToShow: end,
	        firstName: firstName,
	        lastName: lastName,
	        emailAddress: emailAddress,
	        roleName: roleName,
	        phoneNumber: phoneNumber

	    };
	    var config = {
	        params: parameters
	    };
	    
	    UsersService.getUserList(config)
	        .then(function(response) {
	            $scope.total = response.myHashMap.totalCount;
	            angular.copy(response.myHashMap.userList.myArrayList, $scope.userList);

	            deferred.resolve();
	        });
	}

	//used to call the modal pop up
	$scope.showForm = function(user) {
	    $scope.modalInstance = $modal.open({
	        templateUrl: 'partials/addUser.html',
	        controller: ModalInstanceCtrl,
	        scope: $scope,
	        resolve: {
	            userForm: function() {
	                if (user == null || user == 'undefined') {
	                    $scope.user = {};
	                } else {
	                    $scope.user = JSON.parse(JSON.stringify(user));
	                }
	                return $scope.userForm;
	            }

	        }
	    });
	    
	};

	// switch flag
	$scope.switchMessage = function(value) {
	    $scope[value] = !$scope[value];
	};

	//It is used to add a user
	$scope.addUserInfo = function() {

	    var deferred = $q.defer();

	    UsersService.addUser($scope.user)
	        .then(function(response) {

	            deferred.resolve(response);

	            var userId = $scope.user.id;

	            if (userId > 0) {
	                if (response == 1) {
	                    $scope.messages = 'User details updated successfully.';
	                    $window.scrollTo(0, 0);
	                } else {
	                    $scope.messages = 'Oops, unable to update user details.';
	                    $window.scrollTo(0, 0);
	                }
	            } else {
	                if (response == 1) {
	                    $scope.messages = 'User details added successfully.';
	                    $window.scrollTo(0, 0);
	                } else {
	                    $scope.messages = 'Oops, unable to add user details.';
	                    $window.scrollTo(0, 0);
	                }
	                $scope.searchUser = {};
	            }
	            
	          //Loads the User List after successful Add/Update of user
	            getUserList();

	        });

	    $scope.modalInstance.dismiss('cancel');
	    return deferred.promise;
	}

	
	
	//Declaring the function to load data from database
	$scope.fillRoleList = function() {
	    UsersService.getRoles()
	        .then(function(result) {
	            $scope.roleList = result;
	        });
	};

	//clears the search filter
	$scope.clearSearch = function() {
	    $scope.searchUser = {};
	    getUserList();
	};


	//It is used to delete a user with Modal comfirm dialog 
	$scope.showConfirm = function(ev, user) {

	    var confirm = $mdDialog.confirm()
	        .title('Deleting a User')
	        .content('User is being deleted. Are you okay to proceed?')
	        .targetEvent(ev)
	        .ok('Delete')
	        .cancel('Cancel');


	    
	    $mdDialog.show(confirm).then(function() {

	        var deferred = $q.defer();

	        UsersService.deleteUser(user)
	            .then(function(response) {

	                deferred.resolve();

	                if (response == true) {
	                    $scope.messages = 'User deleted successfully.';
	                    $window.scrollTo(0, 0);
	                } else {
	                    $scope.messages = 'Oops, unable to delete user.';
	                    $window.scrollTo(0, 0);
	                }
	                
	              //Loads the User List after successful deletion of user
	                getUserList();


	            });

	        return deferred.promise;


	    });
	};


	//Loads the User List initially
	getUserList();



	//get another portions of data on page changed
	$scope.pageChanged = function() {
	    getUserList();
	};
});

//closing the modal pop up
var ModalInstanceCtrl = function($scope, $modalInstance, userForm, $q, $http) {

    if ($scope.user == null || $scope.user == 'undefined') {
        $scope.user = {};
    }

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};