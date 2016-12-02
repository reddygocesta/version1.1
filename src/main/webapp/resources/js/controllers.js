'use strict';


myapp.controller('LoginController', function ($cookies, $rootScope, $scope, AuthSharedService) {
        $scope.rememberMe = true;
        $scope.login = function () {
            $rootScope.authenticationError = false;
            $scope.loginCookie($scope.username,$scope.password,$scope.keepMe); 
            if($scope.username != undefined && $scope.password != undefined){	       
            	 AuthSharedService.login(
                     $scope.username,
                     $scope.password,
                     $scope.keepMe
                 );   
		    } 
        }
        
        $scope.checkCookie = function () {
        	
        	var userName = $cookies.get("username");
        	var password = $cookies.get("password");
        	
        	if (userName!=null && userName!=""){
        		$scope.keepMe = true;
        		$scope.username = userName;
          	}
        	if (password!=null && password!="") {
        		$scope.password = password;
        	}
        	
        }
        
        $scope.loginCookie = function (userName,password,remember){
        	
        	if(remember){
        		$scope.setCookie($scope.username, $scope.password)
        	}else{
        		$scope.setCookie("","");
        	}
        }
        
        $scope.setCookie = function (username, password) {
           
            var now = new Date();
            now.setDate(now.getDate() + 1);

            $cookies.put("username", username , {
                expires: now
            });
            $cookies.put("password", password , {
                expires: now
            });
        }
        
        $scope.forgotPaaword = function(form){debugger
        	AuthSharedService.forgotPassword($scope.emailAddress);
        }
        $scope.changePassword = function(form){
        	if(form.$valid) {
        		AuthSharedService.changePassword($scope.oldPassword,$scope.newPassword);
        	}else{
        		return false;
        	}  
        }
        
    })
    .controller('HomeController', function ($scope, HomeService) {
    })
 
    .controller('LogoutController', function (AuthSharedService) {
        AuthSharedService.logout();
    })
    .controller('ErrorController', function ($scope, $routeParams) {
        $scope.code = $routeParams.code;

        switch ($scope.code) {
            case "403" :
                $scope.message = "Oops! you have come to unauthorised page."
                break;
            case "404" :
                $scope.message = "Page not found."
                break;
            default:
                $scope.code = 500;
                $scope.message = "Oops! unexpected error"
        }

    })

/*
 * UploadContact controller is used to upload the file and also used for download template.
 * Get the error contacts file
 */
.controller('uploadController', function ($rootScope, $scope,UploadContactService,$q, $timeout, $window) {
  
  $scope.uploadcontact = function () {    
  	 $rootScope.authenticationError = false;
  	 var file = $scope.myFile;
  	 var templateName = $scope.templateName;      	
  	 UploadContactService.upload(templateName,file,$scope);          
  }   
  $scope.downloadTemplate = function () {
  	 UploadContactService.downloadTemplate($q, $timeout, $window);          
  } 
  $scope.downloadContact = function () {
	  UploadContactService.downloadContact($rootScope.uploadConact.errorPath,$q, $timeout, $window);
  }
 $scope.redirectToHomePage = function () {
	  $window.location.href = '#/home';            	
  }
});