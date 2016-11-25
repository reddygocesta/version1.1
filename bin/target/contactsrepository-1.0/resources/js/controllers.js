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
    	$scope.myClass = [];
        $scope.checkClass = function() {
          if($scope.myClass.indexOf('toggled-2') == -1) {
             $scope.myClass.push('toggled-2');
          } else {
             $scope.myClass.pop('toggled-2');
          }
        }
    })
    .controller('ContactsController', function ($scope, $log,$q, $http,  ContactsService ,$routeParams) {
    	
    	$scope.searchContact = {};
    	$scope.countryList={};
    	$scope.stateList={};
    	$scope.industryDetailsList={};
    	$scope.sicCodeList={};
    	
    	
    	$scope.fillCountryList = function () {
    		ContactsService.getContacts()
			.then(function (result) {
				$scope.countryList = result.countryList;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
		
		$scope.countryChange = function (country) {
    		console.log("countryChange" +country);
    		$scope.stateList=country.states;
		};
		
		
		$scope.fillRevenues = function () {
    		ContactsService.getRevenues()
			.then(function (result) {
				$scope.revenueList = result.revenueDetailsList;
				//console.log("::getRevenues  "+$scope.countryList);
				//console.log("::getRevenues  "+JSON.stringify($scope.countryList));
			});
		};
		
		
		$scope.fillCompanySizes = function () {
    		ContactsService.getCompanySizes()
			.then(function (result) {
				$scope.companySizesList = result.companySizeDetails;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
		
		
		$scope.resetContact = function () {
    		$scope.searchContact={};
    		getContactsList();
		};
		
		$scope.fillIndustries = function () {
    		ContactsService.getIndustries()
			.then(function (result) {
				$scope.industryDetailsList = result.industryDetails;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
		
		$scope.IndustryChange = function (industry) {
    		console.log("IndustryChange" +industry);
    		$scope.sicCodeList=industry.sicCodeDetailsList;
		};
		
		
		$scope.fillContactStatus = function () {
    		ContactsService.getContactStatus()
			.then(function (result) {
				$scope.contactStatuslist = result.contactStatuslist;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
		
		$scope.fillJobFunctions = function () {
    		ContactsService.getJobFunctions()
			.then(function (result) {
				$scope.jobFunctionList = result.jobFunctionList;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
		
		$scope.fillJobTitles = function () {
    		ContactsService.getJobTitles()
			.then(function (result) {
				$scope.jobTitleList = result.jobTitleList;
				//console.log(":: "+$scope.countryList);
				//console.log(":: "+JSON.stringify($scope.countryList));
			});
		};
    	
    	
    	$scope.param = $routeParams.param;
    	
    	//console.log("$scope.param :"+$scope.param);
    	
    	$scope.searchContact={};
    	$scope.currentPage = 1;
		$scope.limit= 10;
		
		 $scope.contactsList = [];
		 getContactsList();

		 function getContactsList() {
			  
			  var contactListName = '';
			  var contactStatus = '';
			  var countryName = '';
			  var stateName = '';
			  var jobFunction = '';
			  var revenue = '';
			  var industry = '';
			  var sicCode = '';
			  var companySize = '';
			  var companyEmailId = '';
			  var zipcode = '';
			  var companyName = '';
			  var contactSource = '';
			  var website = '';
			  var contactEmailId = '';
			  var firstName = '';
			  var lastName = '';
			  var phoneNumber = '';
			  var mobile = '';
			  var jobTitle = '';
			  var contactAddedBy = '';
			  var contactAddedDate = '';
			  var contactUpdateDate = '';
			  
			  if($scope.searchContact.contactListName != undefined  && $scope.searchContact.contactListName != '' && $scope.searchContact.contactListName != 'undefined'){
				  contactListName = $scope.searchContact.contactListName;
			   }
			  if($scope.searchContact.contactStatus != undefined && $scope.searchContact.contactStatus != ''  && $scope.searchContact.contactStatus != 'undefined'){
				  contactStatus = $scope.searchContact.contactStatus.status;
			   }
			  if($scope.searchContact.country != undefined && $scope.country != ''  && $scope.searchContact.country != 'undefined'){
				  
				  countryName = $scope.searchContact.country.countryName;
			   }
			  if($scope.searchContact.state != undefined && $scope.searchContact.state != ''  && $scope.searchContact.state != 'undefined'){
				  stateName = $scope.searchContact.state.stateName;
			   }
			  
			  
			  if($scope.searchContact.jobFunction != undefined && $scope.searchContact.jobFunction != ''  && $scope.searchContact.jobFunction != 'undefined'){
				  jobFunction = $scope.searchContact.jobFunction.jobFunctionName;
			   }
			  
			  
			  
			  if($scope.searchContact.revenue != undefined && $scope.searchContact.revenue != ''  && $scope.searchContact.revenue != 'undefined'){
				  revenue = $scope.searchContact.revenue.revenueName;
			   }
			  if($scope.searchContact.industry != undefined && $scope.searchContact.industry != ''  && $scope.searchContact.industry != 'undefined'){
				  industry = $scope.searchContact.industry.industryName;
			   }
			  if($scope.searchContact.sicCode != undefined && $scope.searchContact.sicCode != ''  && $scope.searchContact.sicCode != 'undefined'){
				  sicCode = $scope.searchContact.sicCode.sicCodeName;
			   }
			  if($scope.searchContact.companySize != undefined && $scope.searchContact.companySize != ''  && $scope.searchContact.companySize != 'undefined'){
				  companySize = $scope.searchContact.companySize.companySizeName;
			   }
			  if($scope.searchContact.companyEmailId != undefined && $scope.searchContact.companyEmailId != ''  && $scope.searchContact.companyEmailId != 'undefined'){
				  companyEmailId = $scope.searchContact.companyEmailId;
			   }
			  if($scope.searchContact.zipcode != undefined && $scope.searchContact.zipcode != ''  && $scope.searchContact.zipcode != 'undefined'){
				  zipcode = $scope.searchContact.zipcode;
			   }
			  if($scope.searchContact.company != undefined && $scope.searchContact.company != ''  && $scope.searchContact.company != 'undefined'){
				  companyName = $scope.searchContact.company.companyName;
			   }
			  if($scope.searchContact.contactSource != undefined && $scope.searchContact.contactSource != ''  && $scope.searchContact.contactSource != 'undefined'){
				  contactSource = $scope.searchContact.contactSource.status;
			   }
			  
			  if($scope.searchContact.website != undefined && $scope.searchContact.website != ''  && $scope.searchContact.website != 'undefined'){
				  website = $scope.searchContact.website;
			   }
			  if($scope.searchContact.contactEmailId != undefined && $scope.searchContact.contactEmailId != ''  && $scope.searchContact.contactEmailId != 'undefined'){
				  contactEmailId = $scope.searchContact.contactEmailId;
			   }
			  
			  
			  if($scope.searchContact.firstName != undefined && $scope.searchContact.firstName != ''  && $scope.searchContact.firstName != 'undefined'){
				  firstName = $scope.searchContact.firstName;
			   }
			  if($scope.searchContact.lastName != undefined && $scope.searchContact.lastName != ''  && $scope.searchContact.lastName != 'undefined'){
				  lastName = $scope.searchContact.lastName;
			   }
			  if($scope.searchContact.phoneNumber != undefined && $scope.searchContact.phoneNumber != ''  && $scope.searchContact.phoneNumber != 'undefined'){
				  phoneNumber = $scope.searchContact.phoneNumber;
			   }
			  if($scope.searchContact.mobile != undefined && $scope.searchContact.mobile != ''  && $scope.searchContact.mobile != 'undefined'){
				  mobile = $scope.searchContact.mobile;
			   }
			  if($scope.searchContact.jobTitle != undefined && $scope.searchContact.jobTitle != ''  && $scope.searchContact.jobTitle != 'undefined'){
				  jobTitle = $scope.searchContact.jobTitle.jobTitleName;
			   }
			  if($scope.searchContact.contactAddedBy != undefined && $scope.searchContact.contactAddedBy != ''  && $scope.searchContact.contactAddedBy != 'undefined'){
				  contactAddedBy = $scope.searchContact.contactAddedBy;
			   }
			  if($scope.searchContact.contactAddedDate != undefined && $scope.searchContact.contactAddedDate != ''  && $scope.searchContact.contactAddedDate != 'undefined'){
				  var date = new Date($scope.searchContact.contactAddedDate);
				  contactAddedDate = formatDate(date);
				  
			   }
			  
			  function formatDate(date) {
				    var d = new Date(date),
				        month = '' + (d.getMonth() + 1),
				        day = '' + d.getDate(),
				        year = d.getFullYear();

				    if (month.length < 2) month = '0' + month;
				    if (day.length < 2) day = '0' + day;

				    return [year, month, day].join('-');
				}
			  
			  if($scope.searchContact.contactUpdateDate != undefined && $scope.searchContact.contactUpdateDate != ''  && $scope.searchContact.contactUpdateDate != 'undefined'){
				  var date = new Date($scope.searchContact.contactUpdateDate);
				  contactUpdateDate = formatDate(date);
			   }
			  
			  

			  var begin = (($scope.currentPage - 1) * $scope.limit)
			    , end = begin + $scope.limit;
			  
			  /* $http.get("contact/getContacts?startRecord="+begin+"&recordToShow="+end)
			     .then(function(response) {
			    	 console.log(response);
			       $scope.total = response.data.totalCount;
			       //angular.copy(response.data.contactList, $scope.contactsList);
			       $scope.contactsList=response.data.contactList;
			       if($scope.contactsList.length == 0){
			    	   angular.copy("No Records found", $scope.tableMessage);
			       }

			     });*/
			   
			   var parameters = {
						 startRecord:begin,
						 recordToShow:end,
						 contactListName:contactListName,
						 contactStatus:contactStatus,
						 countryName:countryName,
						 stateName:stateName,
						 jobFunction:jobFunction,
				  		 revenue:revenue,
				  	     industry:industry,
				         sicCode:sicCode,
				         companySize:companySize,
				         companyEmailId:companyEmailId,
				         zipcode:zipcode,
				        companyName:companyName,
				        contactSource:contactSource,
				        website:website,
				        contactEmailId:contactEmailId, 
				        firstName:firstName,
				        lastName:lastName,
				        phoneNumber:phoneNumber,
				        mobile:mobile,
				        jobTitle:jobTitle,
				        contactAddedBy:contactAddedBy,
				        contactAddedDate:contactAddedDate,
				        contactUpdateDate:contactUpdateDate
	    				};
	    				var config = {
	    					params : parameters
	    				};
				  
	    		ContactsService.getContactsList(config)
					.then(function (response) {
					
						//deferred.resolve();
						// $scope.total = response.totalCount;
					    // angular.copy(response.userList, $scope.userList);
					     
					     	console.log(response);
					       //$scope.total = response.data.totalCount;
					       //angular.copy(response.data.contactList, $scope.contactsList);
					       $scope.contactsList=response.contactList;
					       if($scope.contactsList.length == 0){
					    	   angular.copy("No Records found", $scope.tableMessage);
					       }
						
					});
			   
			 }
		 
		 
		//Declaring the function to load data from database
			$scope.viewContact = function () {
				
				$http.get("contact/viewContact/"+$scope.param)
			     .then(function(response) {
			    	 console.log(":: "+response.data);
			    	 $scope.contact = response.data.contact;
			    	 console.log("::2 "+$scope.contact );

			     });
			};

		 
		//get another portions of data on page changed
		 $scope.pageChanged = function() {
			 console.log("......pageChanged");
			 getContactsList();
		 };
		 
		 if($scope.contactsList.length == 0){
			 $scope.tableMessage = "No Records found";
		 }
             
				    
    })
 
    .controller('UsersController', function ($scope, $log,$q, $http,  UsersService) {
		//UsersController.$inject = ["NgTableParams", "ngTableSimpleList"];
		//self.tableParams.filter({ $: term });
    	var startRecord =1;
    	var recordToShow = 5;
    	/*var startRecord = 1;
    	var recordToShow = 5
        $scope.users = UsersService.getAll(startRecord,recordToShow);
		$scope.usersTable = new NgTableParams({}, 
		{ dataset: $scope.users });*/
		
		
              $scope.searchUser = function() {
						var details = search($scope.user);
                           
						details.then(function(data) {
							console.log(data);
							$scope.contacts;
						}, function(error) {
							console.error(data);
						})

					}
					function search(user) {
						/*var parameters = {
							firstName : fname,
							lastName:lname,
							Email:email,
							Phone:phone,
							Role:role
						};
						var config = {
							params : parameters
						};*/

						var deferred = $q.defer();
						$http.get('users/searchfname', user).then(

						function(response) {
							console.log(response)
							deferred.resolve(response.data);

						}, function(errResponse) {
							console.error('Error while creating User');
							deferred.reject(errResponse);
						});

						return deferred.promise;
					}

				    
    })
    .controller('ApiDocController', function ($scope) {
        // init form
        $scope.isLoading = false;
        $scope.url = $scope.swaggerUrl = 'v2/api-docs';
        // error management
        $scope.myErrorHandler = function (data, status) {
            console.log('failed to load swagger: ' + status + '   ' + data);
        };

        $scope.infos = false;
    })
    .controller('TokensController', function ($scope, UsersService, TokensService, $q) {

        var browsers = ["Firefox", 'Chrome', 'Trident']

        $q.all([
            UsersService.getAll().$promise,
            TokensService.getAll().$promise
        ]).then(function (data) {
            var users = data[0];
            var tokens = data[1];

            tokens.forEach(function (token) {
                users.forEach(function (user) {
                    if (token.userLogin === user.login) {
                        token.firstName = user.firstName;
                        token.familyName = user.familyName;
                        browsers.forEach(function (browser) {
                            if (token.userAgent.indexOf(browser) > -1) {
                                token.browser = browser;
                            }
                        });
                    }
                });
            });

            $scope.tokens = tokens;
        });


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
	.controller('FormSubmitController', function($scope, $modal, $http, $q, $log, UsersService,  $mdDialog, $mdMedia,$window) {
					
					$scope.user = {};
					$scope.searchUser = {};
					$scope.currentPage = 1;
					$scope.limit= 10;
					  
					$scope.userList = [];
					 
					$scope.showForm = function (user) {
			            $scope.modalInstance = $modal.open({
			                templateUrl: 'partials/addUser.html',
			                controller: ModalInstanceCtrl,
			                scope: $scope,
			                resolve: {
			                    userForm: function () {
									if(	user == null || user == 'undefined') {
										$scope.user = {};
									} else {
										$scope.user = JSON.parse(JSON.stringify(user));
									}
			                        return $scope.userForm;
			                    }
								
			                }
			            });

			            $scope.modalInstance.result.then(function (userm, selectedItem) {
			                $scope.selected = selectedItem;
			            });
						
			        };
					
					// switch flag
					$scope.switchMessage = function (value) {
						$scope[value] = !$scope[value];
					};
					
					$scope.addUserInfo = function() {
		
						var deferred = $q.defer();
						//if ($scope.userForm.$valid) {

						UsersService.addUser($scope.user)
						.then(function (response) {

							deferred.resolve(response);
								
							var userId = $scope.user.id;
							
							if(userId > 0) {
								if(response == 1) {
									$scope.messages = 'User details updated successfully.';
									$window.scrollTo(0, 0);
								} else {
									$scope.messages = 'Oops, unable to update user details.';
									$window.scrollTo(0, 0);
								}
							}else {
								if(response == 1) {
									$scope.messages = 'User details added successfully.';
									$window.scrollTo(0, 0);
								} else {
									$scope.messages = 'Oops, unable to add user details.';
									$window.scrollTo(0, 0);
								}
									$scope.searchUser = {};
							}
							
							getUserList();
						
						});

						$scope.modalInstance.dismiss('cancel');
						return deferred.promise;
					}
			       
					$scope.roleList = null;
					//Declaring the function to load data from database
					$scope.fillRoleList = function () {
						UsersService.getRoles()
						.then(function (result) {
							$scope.roleList = result;
						});
					};
					
					$scope.clearSearch = function () {
						$scope.searchUser = {};
						getUserList();
					};
					
					
					
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
							.then(function (response) {
							
								deferred.resolve();
								
								if(response == true) {
									$scope.messages = 'User deleted successfully.';
									$window.scrollTo(0, 0);
								} else {
									$scope.messages = 'Oops, unable to delete user.';
									$window.scrollTo(0, 0);
								}
								
								getUserList();
								
								
							});
							
							return deferred.promise;
						  
						  
						});
					};

					
					//This section belongs to user list pagination
				
				 getUserList();
				 
				 function getUserList() {
					  
					 var firstName = '';
					  var lastName = '';
					  var emailAddress = '';
					  var phoneNumber = '';
					  var roleName = '';
					  
					  var deferred = $q.defer();
					  
					  if($scope.searchUser.firstName != undefined  && $scope.searchUser.firstName != '' && $scope.searchUser.firstName != 'undefined'){
						   firstName = $scope.searchUser.firstName;
					   }
					  if($scope.searchUser.lastName != undefined && $scope.searchUser.lastName != ''  && $scope.searchUser.lastName != 'undefined'){
						  lastName = $scope.searchUser.lastName;
					   }
					  if($scope.searchUser.emailAddress != undefined && $scope.searchUser.emailAddress != ''  && $scope.searchUser.emailAddress != 'undefined'){
						  emailAddress = $scope.searchUser.emailAddress;
					   }
					  if($scope.searchUser.phoneNumber != undefined && $scope.searchUser.phoneNumber != ''  && $scope.searchUser.phoneNumber != 'undefined'){
						  phoneNumber = $scope.searchUser.phoneNumber;
					   }
					  if($scope.searchUser.role){
						  roleName = $scope.searchUser.role["roleName"];
					   }
					  

					 var begin = (($scope.currentPage - 1) * $scope.limit)
					    , end = begin + $scope.limit;
					 
					 var parameters = {
							 startRecord:begin,
							 recordToShow:end,
							 firstName:firstName,
							 lastName:lastName,
							 emailAddress:emailAddress,
							 roleName:roleName,
							 phoneNumber:phoneNumber
							 
		    				};
		    				var config = {
		    					params : parameters
		    				};
					  
	    				UsersService.getUserList(config)
						.then(function (response) {
							$scope.total = response.myHashMap.totalCount;
						    angular.copy(response.myHashMap.userList.myArrayList, $scope.userList);
							deferred.resolve();
						});
		    				
				}
				 
				//get another portions of data on page changed
				 $scope.pageChanged = function() {
					 getUserList();
				 };
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
})

.controller('CampaignsController', function ($scope,$q, CampaignService) {
	  
	$scope.campaignSearch = {};
	$scope.campaignList = [];
	$scope.currentPage = 1;
	$scope.limit= 10;
  
	$scope.fillCampaignStatus = function () {
		CampaignService.getCampaignStatus()
		.then(function (result) {
			$scope.campaignStatusList = result.campaignStatusList;
		});
	};
	
	$scope.fillCampaignType = function () {
		CampaignService.getCampaignType()
		.then(function (result) {
			$scope.campaignTypeList = result.campaignTypeList;
		});
	};
	
	
	$scope.fillCampaignBusinessUnit = function () {
		CampaignService.getCampaignBusinessUnit()
		.then(function (result) {
			$scope.businessUnitList = result.campaignBusinessUnitList;
			
		});
	};
	
	
	$scope.fillCampaignRegion = function () {
		CampaignService.getCampaignRegion()
		.then(function (result) {
			$scope.regionList = result.campaignRegionList;
		});
	};
	
	$scope.fillServiceOffering = function () {
		CampaignService.getServiceOffering()
		.then(function (result) {
			$scope.serviceOfferingList = result.serviceOfferingList;
		});
	};
	
	
	$scope.reset = function(){
		$scope.campaignSearch = {};
		getCampaignList();
	}
	
	//get another portions of data on page changed
	 $scope.pageChanged = function() {
		 getCampaignList();
	 };
	
	getCampaignList();
	
	
	
  
    function getCampaignList() {
		  var campaignName = '';
		  var campaignType = 0;
		  var campaignStatus = 0;
		  var campaignNumber = 0;
		  var ownerName = '';
		  var serviceOffering = 0;
		  var businessUnit = 0;
		  var region = 0;
		  var campaignRunDate = '';
		  
		  var deferred = $q.defer();
		  
		  if($scope.campaignSearch.campaignName != undefined  && $scope.campaignSearch.campaignName != '' && $scope.campaignSearch.campaignName != 'undefined'){
			  campaignName = $scope.campaignSearch.campaignName;
		   }
		  if($scope.campaignSearch.campaignType){
			  campaignType = $scope.campaignSearch.campaignType["id"];
		   }
		  if($scope.campaignSearch.campaignStatus){
			  campaignStatus = $scope.campaignSearch.campaignStatus["id"];
		   }
		  if($scope.campaignSearch.campaignNumber != undefined && $scope.campaignSearch.campaignNumber != ''  && $scope.campaignSearch.campaignNumber != 'undefined'){
			  camapignNumber = $scope.campaignSearch.campaignNumber;
		   }
		  if($scope.campaignSearch.ownerName != undefined && $scope.campaignSearch.ownerName != ''  && $scope.campaignSearch.ownerName != 'undefined'){
			  ownerName = $scope.campaignSearch.ownerName;
		   }
		  if($scope.campaignSearch.serviceOffering){
			  serviceOffering = $scope.campaignSearch.serviceOffering["seriveOfferingId"];
		   }
		  if($scope.campaignSearch.businessUnit){
			  businessUnit = $scope.campaignSearch.businessUnit["id"];
		   }
		  if($scope.campaignSearch.region){
			  region = $scope.campaignSearch.region["id"];
		   }
		  if($scope.campaignSearch.campaignRunDate != undefined && $scope.campaignSearch.campaignRunDate != ''  && $scope.campaignSearch.campaignRunDate != 'undefined'){
			  campaignRunDate = $scope.campaignSearch.campaignRunDate;
		   }
		  
		  /*if($scope.campaignSearch.role){
			  roleName = $scope.campaignSearch.role["roleName"];
		   }*/
		  
		
		 var begin = (($scope.currentPage - 1) * $scope.limit)
		    , end = begin + $scope.limit;
		 
		
		 
		 var parameters = {
				 startRecord:begin,
				 recordToShow:end,
				 campaignName:campaignName,
				 campaignTypeId:campaignType,
				 campaignStatusId:campaignStatus,
				 campaignNumber:campaignNumber,
				 ownerName:ownerName,
				 serviceOffering:serviceOffering,
				 businessUnit:businessUnit,
				 region:region,
				 campaignRunDate:campaignRunDate
				 
			};
			var config = {
				params : parameters
			};
		  
			CampaignService.getCampaignList(config)
			.then(function (response) {
			
				deferred.resolve();
				$scope.total = response.totalCount;
			    angular.copy(response.campaignList, $scope.campaignList);
				
			});
			
		}
});

 var ModalInstanceCtrl = function ($scope, $modalInstance, userForm, $q, $http) {
			        	
	if(	$scope.user == null || 	$scope.user == 'undefined') {
		$scope.user = {};
	}
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
};
