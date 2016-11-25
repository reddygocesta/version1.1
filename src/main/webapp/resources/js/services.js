'use strict';

myapp.service('Session', function () {
    this.create = function (data) {
        this.id = data.id;
        this.login = data.login;
        this.firstName = data.firstName;
        this.lastName = data.familyName;
        this.email = data.email;
        this.userRoles = [];
        angular.forEach(data.authorities, function (value, key) {
            this.push(value.name);
        }, this.userRoles);
    };
    this.invalidate = function () {
        this.id = null;
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.userRoles = null;
    };
    return this;
});


myapp.service('AuthSharedService', function ($rootScope, $http, $resource, authService, Session, $location, $window, $q) {
    return {
        login: function (userName, password, rememberMe) {
            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('authenticate', $.param({
                username: userName,
                password: password,
                rememberme: rememberMe
            }), config)
                .success(function (data, status, headers, config) {
                    authService.loginConfirmed(data);
                })
                .error(function (data, status, headers, config) {
                    $rootScope.authenticationError = true;
                    Session.invalidate();
                });
        },
       getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('security/account')
                .then(function (response) {
                    authService.loginConfirmed(response.data);
                });
        },
        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
            }
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function (authorizedRole) {
                var authorized = (!!Session.login &&
                Session.userRoles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },
        logout: function () {
            $rootScope.authenticationError = false;
            $rootScope.temporaryPassword = false; 
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $rootScope.uploadConact = null;
            $http.get('logout');
            Session.invalidate();
            authService.loginCancelled();
        },
        forgotPassword: function (emailAddress) {
        	 var deferred = $q.defer();
             $http.post('forgotPassword', emailAddress)
                 .then(
                 function (response) {
                 	           	
                	 if(response.data == "success"){
                	   $window.alert("Email has been sent successfully");
                  	   var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/login");
  		                $location.path(nextLocation).replace();
                	 }else if(response.data == "Email Address not Exit"){
 	            		$window.alert("Email Address does not exist");
 	            	}else if(response.data == "EMAIL_INVITATION_FAILED"){
 	            		$window.alert("Mail not able to trigger to your mail address");
 	            	}
                 },
                 function(errResponse){
                	 $window.alert("Mail not able to trigger to your mail address");
                     deferred.reject(errResponse);
                 }
             );
             return deferred.promise;
        },
        changePassword: function (oldPassword,newPassword) {
       	 var userdata = { 
       			 oldPassword: oldPassword,
       			 newPassword:newPassword, 
       			 userId : Session.id
       	 };
           $http.post("users/changePassword", userdata)
	            .success(function (data) {
	            	if(data == "success"){
	            		$window.alert("Password has been changed successfully");
	            		var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/logout");
		                $location.path(nextLocation).replace();
	            	}else if(data == "passwordsSame"){
	            		$window.alert("Please enter New Password which is not used previously");
	            	}else if(data == "invalidOldPassword"){
	            		$window.alert("Please enter valid old password");
	            	}
	            })
	            .error(function (data) {
	            	var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/logout");
	                $location.path(nextLocation).replace();
	            });
       }
    };
});

myapp.service('HomeService', function ($log, $resource) {
    return {
        getTechno: function () {
            var userResource = $resource('resources/json/techno.json', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return userResource.query();
        }
    }
});


myapp.service('UsersService', function ($log, $resource, $http) {
    return {
        getAll: function (startRecord,recordToShow) {
            var userResource = $resource('users/getUsers', {}, {
                query: {method: 'GET', params: {"startRecord":startRecord,"recordToShow":recordToShow}, isArray: true}
            });
            return userResource.query();
        },addUser: function (data) {
        	
        	 return $http.post("users/addUser", data).then( function(res) {
     			return res.data;
     		});
        	 
        },getRoles: function () {
        	
       	 return $http.get("users/getRole").then( function(res) {
    			return res.data;
    		});
       	 
       },deleteUser: function (data) {
       	
       	 return $http.post("users/deleteuser", data).then( function(res) {
    			return res.data;
    		});
       	 
       },getUserList : function (config) {
			  
    	   return $http.get("users/getUsers",config).then(function(response) {
			    	 return response.data;
			      /* $scope.total = response.data.myHashMap.totalCount;
			       angular.copy(response.data.myHashMap.userList.myArrayList, $scope.userList);
*/
			    });
       }
    }
});

myapp.service('ContactsService', function ($log, $resource, $http) {
    return {
    	getAll: function (startRecord,recordToShow) {
            var contactsResoucre = $resource('contact/getContacts', {}, {
                query: {method: 'GET', params: {"startRecord":startRecord,"recordToShow":recordToShow}, isArray: true}
            });
            return contactsResoucre.query();
        },
        getContacts: function () {
        	
          	 return $http.get("common/getCountryList").then( function(res) {
       			return res.data;
       		})
        },
        getRevenues: function () {
        	
         	 return $http.get("common/getRevenueList").then( function(res) {
      			return res.data;
      		})
       },
       getCompanySizes: function () {
       	
        	 return $http.get("common/getCompanySizeList").then( function(res) {
     			return res.data;
     		})
      },
      getIndustries: function () {
      	
       	 return $http.get("common/getIndustryList").then( function(res) {
    			return res.data;
    		})
     },
     getContactStatus: function () {
     	
      	 return $http.get("common/getContactStatus").then( function(res) {
   			return res.data;
   		})
    },
    getJobFunctions: function () {
     	
     	 return $http.get("common/getJobFunctionList").then( function(res) {
  			return res.data;
  		})
   },
   getJobTitles: function () {
    	
   	 return $http.get("common/getJobTitleList").then( function(res) {
			return res.data;
		})
   },
   getContactsList : function (config) {
		  
	  $http({
           method: 'POST',
           url: 'contact/getContacts',           
           data:  JSON.stringify(config)
       })
       .success(function(response){  
    	   console.log(response.data);
       })
      .error(function(error){
   	   console.log(error);        	   
      });
    }
  
    }
    
});


myapp.service('TokensService', function ($log, $resource) {
    return {
        getAll: function () {
            var tokensResource = $resource('security/tokens', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return tokensResource.query();
        }
    }
});

myapp.service('EmailCheckService', function ($http ,$log, $resource) {
    return {
        checkUniqueValue : function (id, property, value) {
		  var data = {
			emailAddress: value
		  };
		  return $http.post("users/isEmailExist", data).then( function(res) {
			return res.data;
		  });
		}
    }
    
});

/*
 * UploadContact service is used to upload the file and also used for download template.
 * Get the error contacts file
 */
myapp.service('UploadContactService', function ($rootScope, $http,$window) {
    return {
    	upload: function (templateName,file,$scope) {
    		
    		var formData = new FormData();
            formData.append("file",file);
            formData.append("templateName",templateName);
            
    		$http({
                method: 'POST',
                url: 'contact/contactUpload',
                transformRequest : angular.identity,
                headers : {
                	'Content-Type' : undefined
                },
                data:  formData
            })
            .success(function(response){               	 
            	$rootScope.uploadConact = response;
            	$window.location.href = '#/uploadSuccess';            	
            })
           .error(function(error){
        	   console.log(error);        	   
           });
        },
        downloadTemplate: function ($q, $timeout, $window) {
    		        	
        	 var defer = $q.defer();
             $timeout(function() {
                 $window.location = 'contact/downloadTemplate';
             }, 1000)
             .then(function() {
                 defer.resolve('success');
             }, function() {
                 defer.reject('error');
             });
             return defer.promise;
        },
        downloadContact: function (filePath,$q, $timeout, $window) {
        	
        	if(filePath != '' && filePath != null){        		
        		$window.location = 'contact/contactDownload?filePath='+filePath;
        	}
       },
    };
});

myapp.service('CampaignService', function ($log, $resource, $http) {
    return {
    	getCampaignList : function (config) {
			  
    	   return $http.get("campaign/getCampaignList",config).then(function(response) {
			    	 return response.data;
			      /* $scope.total = response.data.myHashMap.totalCount;
			       angular.copy(response.data.myHashMap.userList.myArrayList, $scope.userList);
*/
			    });
       },
       getCampaignStatus: function () {
       	
        	 return $http.get("common/getCampaignStatus").then( function(res) {
     			return res.data;
     		})
       },
       getCampaignType: function () {
      	
       	 	return $http.get("common/getCampaignType").then( function(res) {
    			return res.data;
    		})
       },
       getCampaignBusinessUnit: function () {
     	
      	 	return $http.get("common/getCampaignBusinessUnit").then( function(res) {
   				return res.data;
   			})
       },
       getCampaignRegion: function () {
    	
    	   return $http.get("common/getCampaignRegion").then( function(res) {
  				return res.data;
  			})
       },
       getServiceOffering: function () {
       	
    	   return $http.get("common/getContactServiceOfferingList").then( function(res) {
  				return res.data;
  			})
       }
    }
});


myapp.service('ContactListCheckService', function ($http ,$log, $resource) {
    return {
    	
    checkUniqueContactList : function (value) {
    
    var formdata = { 
    		contactListName: value
  	 };
     return $http.post("contact/isContactListExist", formdata).then( function(res) {
			return res.data;
		  });
    	}
    }
});

/*
 * Created By : Narasimha Reddy K 
 * This service is used to handle the Configuration HTTP requests
 * 
 * */

myapp.service('ConfigurationService', function ($log, $resource, $http) {
    return {
    	getAllCampaignConfiguration: function (startRecord,recordToShow) {
            var userResource = $resource('configuration/getCampaingConfiguration', {}, {
                query: {method: 'GET', params: {"startRecord":startRecord,"recordToShow":recordToShow}, isArray: true}
            });
            return userResource.query();
        },addCampaignConfiguration: function (data) {
        	
        	 return $http.post("configuration/saveCampaignConfiguration", data).then( function(res) {
     			return res.data;
     		});
        	 
        },addFieldConfiguration: function (data) {
        	
       	 return $http.post("configuration/saveFieldCongiguration", data).then( function(res) {
    			return res.data;
    		});
       	 
       }
    }
});

