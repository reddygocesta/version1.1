
/*
 * This controller contains all operation related to Campaign Configuration Screen
 * Created By : Narasimha Reddy, Shahul
Created Date: 	11/24/2016

*/

'use strict';   

myapp.controller('ConfigController', function ($scope,$http,$q,Session,$window,ContactsService,$mdDialog,ConfigurationService) {
			  
			$scope.configuration={};
			$scope.campaign={};
			$scope.mappings={};
			$scope.daytypes={};
			$scope.statusmappings={};
			$scope.campaignConfiguration = {};
			getstatus();
			getdaystypes();
			getFieldConfiguration();
			$scope.commentLength = 10;
			$scope.currentPage = 1;
			$scope.limit= 10;
			$scope.campaignConfigurationList = [];
			getcampaignConfigurationList();
			$scope.saveUnConfigCampaigns =[];
			  
			  
			  
		// switch flag
			$scope.switchMessage = function (value) {
				$scope[value] = !$scope[value];
			};
			  
    		function getstatus()
    		{  
	    		var deferred = $q.defer();
	    	      $http.get('users/getstatus').then(
						function(response) {
							console.log(response)
							$scope.mappings=response.data;
							deferred.resolve(response.data);
	
						}, function(errResponse) {
							console.error('Error while creating status');
							deferred.reject(errResponse);
						});
	
						return deferred.promise;
    		}
				
    		function getdaystypes()
    		{
    			var deferred = $q.defer();
    			$http.get('users/getdaystype').then(

					function(response) {
						console.log(response)
						$scope.daytypes=response.data;
						$scope.configuration.campaignLeadTimeType = response.data[0];
						$scope.configuration.allowAssignContactsType = response.data[0];
						$scope.configuration.deleteThresholdType = response.data[0];
						$scope.configuration.saveSearchThresholdType = response.data[1];
						
						console.log("$scope.daytypes");
						deferred.resolve(response.data);

					}, function(errResponse) {
						console.error('Error while creating daystypes');
						deferred.reject(errResponse);
					});
					return deferred.promise;
    		}
    		
    		function getFieldConfiguration(){
    			var parameters = {
    					userId:Session.id
    				};
    				var config = {
    					params : parameters
    				};
    				var deferred = $q.defer();
    				
    				$http({
    					method: 'POST',
    					url: 'configuration/getFieldCongiguration',
    					params: parameters
    				}).success(function (result) {
    					deferred.resolve(result);
    					if(null != result){
    						 $scope.configuration = result;
    					}
    					
    					 
    				}).error(function (error){
    					console.log(JSON.stringify(error));
    				});
    					
    				//}
    				return deferred.promise;
    		}
    		$scope.saveFieldConfig=function() {
    			        console.log($scope.configuration);
    			        if($scope.configuration.campaignContactStatus == undefined && $scope.configuration.campaignContactStatus ==null )
    			        {
    			        	$scope.campaignMsg = 'Please select either one of the status.';
							$window.scrollTo(0, 0);
							return false;
    			        }
    			        else{
    			        if($scope.configuration.campaignContactStatus.campaignContactStatusId == undefined && $scope.configuration.campaignContactStatus.campaignContactStatusId ==null )
    			        {
    			 
    			        	$scope.campaignMsg = 'Please select either one of the status.';
							$window.scrollTo(0, 0);
							
    			        	 return false;
    			        }else{
    			        	  $scope.configuration.userId = Session.id;
    	    			        var deferred = $q.defer();
    							//if ($scope.userForm.$valid) {
    							ConfigurationService.addFieldConfiguration($scope.configuration)
    								.then(
    								function (response) {
    									deferred.resolve(response);
    									
    									if(response == 1) {
    										$scope.messages = 'Field Configuration details added successfully.';
    										$window.scrollTo(0, 0);
    									} else {
    										$scope.messages = 'Unable to add Field Confirutaion';
    										$window.scrollTo(0, 0);
    									}
    									
    									
    								},
    								function(errResponse){
    									console.error('Error while save filed configuration');
    									deferred.reject(errResponse);
    								}
    							);
    							return deferred.promise;
    			        }
    			 }
    			      
    		}
    	  
    		 $scope.saveCampaignConfig=function() {
    			 
		           var deferred = $q.defer();
					ConfigurationService.addCampaignConfiguration($scope.campaignConfiguration)
						.then(function (response) {
							deferred.resolve(response);
							
							if(response == 1) {
								$scope.messages = 'Campaign configuration details added successfully.';
								$window.scrollTo(0, 0);
								$scope.campaignConfiguration = {}
								getcampaignConfigurationList();
								
							}else if(response == -1){
								$scope.messages = 'Already Campaign configuration Exist.';
								$window.scrollTo(0, 0);
								
							}else {
							
								$scope.messages = 'Unable to add Campaign configuration.';
								$window.scrollTo(0, 0);
							}
						},
						function(errResponse){
							console.error('Error while save Campaign configuration');
							deferred.reject(errResponse);
						}
					);
					return deferred.promise;
    		 }
			$scope.$watch('configuration.campaignLeadTime', function (newValue, oldValue) {
				if (newValue) {
					console.log(newValue.length);
					if (newValue.length > 3) {
						alert("yes");
					  $scope.configuration.campaignLeadTime = oldValue;
					}
					$scope.commentLength = 10 - newValue.length;
				}
			});
			
			
			$scope.updateBody = function (event) {
				//event.stop();
				console.log(event);
				return false;
			};
			
			$scope.fillCountryList = function () {
	    		ContactsService.getContacts()
				.then(function (result) {
					$scope.countryList = result.countryList;
				});
			};
			
			$scope.countryChange = function (country) {
	    		$scope.stateList=country.states;
			};
			 function getcampaignConfigurationList() {

				 var begin = (($scope.currentPage - 1) * $scope.limit)
				    , end = begin + $scope.limit;
				  
				   $http.get("configuration/getCampaingConfiguration?startRecord="+begin+"&recordToShow="+end)
				   
				     .then(function(response) {
				    	 console.log(response);
				       $scope.total = response.data.totalCount;
				       angular.copy(response.data.configurationList, $scope.campaignConfigurationList);
				       $scope.campaignConfigurationList=response.data.configurationList;
				       if($scope.campaignConfigurationList.length == 0){
				    	   angular.copy("No Records found", $scope.tableMessage);
				       }

				     });
				 }
			//get another portions of data on page changed
			 $scope.pageChanged = function() {
				 getcampaignConfigurationList();
			 };
			 $scope.saveUnConfigCampaigns = function(ev){
				 console.log($scope.configurationIds);
				 var deconfigureList = '';
				    $scope.campaignConfigurationList.forEach(function(configuration) {

				      if (configuration.checkedStatus) {

				        // If this is not the first item
				        if (deconfigureList) {
				        	deconfigureList += ','
				        }
				        deconfigureList += configuration.campaignConfigurationId;
				      }

				    })
				    if(deconfigureList == ''){
				    	 $window.alert("Please select atleast single item to deconfigure");
				    }else{
				    	getDeConfiguration(deconfigureList,ev);
				    }
			 }
			
			 function getDeConfiguration(deconfigureList,ev){
				 var confirm = $mdDialog.confirm()
				  .title('Deconfigure campaigne')
				  .content('Campaign Configuration  is being Deconfigured. Are you okay to proceed?')
				  .targetEvent(ev)
				  .ok('Delete')
				  .cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				var parameters = {
    					configurationIds:deconfigureList
    				};
    				var deferred = $q.defer();
    				$http({
    					method: 'POST',
    					url: 'configuration/deConfigureCampaign',
    					params: parameters
    				}).success(function (response) {
    					deferred.resolve(response);
    					
    					if(response == 1) {
							$scope.messages = ' DeConfigured  Successfuly';
							$window.scrollTo(0, 0);
							getcampaignConfigurationList();
						}else {
							$scope.messages = 'Unable to DeConfigured';
							$window.scrollTo(0, 0);
						}
    					 
    				}).error(function (error){
    					console.log(JSON.stringify(error));
    				});
    					
    				return deferred.promise;
			  
			});
	   }
    })