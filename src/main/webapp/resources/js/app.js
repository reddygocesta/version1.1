'use strict';

var myapp = angular
    .module('myApp', ['ngCookies','ngMaterial','ngResource', 'ngRoute', 'swaggerUi', 'http-auth-interceptor', 'ngAnimate', 'angular-spinkit', 'ui.bootstrap','angularjs-dropdown-multiselect']);


myapp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
});

myapp.config(['$httpProvider', function($httpProvider) {
    //initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};    
    }    

    // Answer edited to include suggestions from comments
    // because previous version of code introduced browser-related errors

    //disable IE ajax request caching
    //$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}]);

myapp.config(function ($routeProvider, USER_ROLES) {

    $routeProvider.when("/home", {
        templateUrl: "partials/home.html",
        controller: 'HomeController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/', {
        redirectTo: '/login'
    }).when('/users', {
        templateUrl: 'partials/users.html',
        controller: 'UsersController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/configuration', {
        templateUrl: 'partials/configuration.html',
        controller: 'ConfigController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/contacts', {
        templateUrl: 'partials/contacts.html',
        controller: 'ContactsController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/viewContact/:param', {
        templateUrl: 'partials/viewContact.html',
        controller: 'ContactsController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/campaigns', {
        templateUrl: 'partials/campaigns.html',
        controller: 'CampaignController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/addCampaign', {
        templateUrl: 'partials/addCampaign.html',
        controller: 'CampaignController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/apiDoc', {
        templateUrl: 'partials/apiDoc.html',
        controller: 'ApiDocController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/tokens', {
        templateUrl: 'partials/tokens.html',
        controller: 'TokensController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/editContacts', {
        templateUrl: 'partials/editContacts.html',
        controller: 'ContactsController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/forgotPassword', {
        templateUrl: 'partials/forgotPassword.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/changePassword', {
        templateUrl: 'partials/changepassword.html',
        controller: 'LoginController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/loading', {
        templateUrl: 'partials/loading.html',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/logout", {
        template: " ",
        controller: "LogoutController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/error/:code", {
        templateUrl: "partials/error.html",
        controller: "ErrorController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/upload", {
        templateUrl: "partials/contactUpload.html",
        controller: "uploadController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }  
    }).when("/uploadSuccess", {
        templateUrl: "partials/UploadSuccess.html",
        controller: "uploadController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }  
    }).otherwise({
        redirectTo: '/error/404',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/addContact', {
        templateUrl: 'partials/addContacts.html',
        controller: 'ContactsController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.all]
        }
    });
});

myapp.run(function ($rootScope, $location, $http, AuthSharedService, Session, USER_ROLES, $q, $timeout) {

    $rootScope.$on('$routeChangeStart', function (event, next) {

        if(next.originalPath === "/login" && $rootScope.authenticated) {
            event.preventDefault();
        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {});
        } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
            //$rootScope.$broadcast("event:auth-loginRequired", {});
        }
    });

    $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
        $rootScope.$evalAsync(function () {
            $.material.init();
        });
    });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
        console.log('login confirmed start ' + data);
        $rootScope.loadingAccount = false;
        var nextLocation = '';
        if(data.isActiveTempPassword == 1){
        	$rootScope.temporaryPassword = true; 
        	nextLocation = "/changePassword";
        }else{
        	$rootScope.temporaryPassword = false; 
        	nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
        }
        var delay = ($location.path() === "/loading" ? 1500 : 0);

        $timeout(function () {
            Session.create(data);
            $rootScope.account = Session;
            
			$rootScope.account.role =  angular.lowercase($rootScope.account.userRoles[0]);

			$rootScope.isAdmin = false;
			$rootScope.isCampaignOwner = false;
			$rootScope.isManager = false;

			if($rootScope.account.role == 'admin') {
				$rootScope.isAdmin = true;
			} else if($rootScope.account.role == 'campaign owner') {
				$rootScope.isCampaignOwner = true;
			} else if($rootScope.account.role == 'manager') {
				$rootScope.isManager = true;
			} else {
				$rootScope.isAdmin = false;
				$rootScope.isCampaignOwner = false;
				$rootScope.isManager = false;
			}
            
            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (event, data) {
        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path()
            $location.path('/loading');
        } else {
            Session.invalidate();
            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            $location.path('/login');
        }
    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-forbidden', function (rejection) {
        $rootScope.$evalAsync(function () {
            $location.path('/error/403').replace();
        });
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('/login').replace();
    });

    // Get already authenticated user account
    AuthSharedService.getAccount();
    
    $rootScope.myClass = [];
    $rootScope.checkClass = function() {
      if($rootScope.myClass.indexOf('toggled-2') == -1) {
    	  $rootScope.myClass.push('toggled-2');
      } else {
    	  $rootScope.myClass.pop('toggled-2');
      }
    }


});
