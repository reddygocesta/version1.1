
/*
 * This controller contains all operation related to Campaign Screen
 * Created By : Marshall
 * Created Date: 11/24/2016
 */

'use strict';

myapp.controller('CampaignController', function ($scope, $q, $http, CampaignService,ContactsService) {
	  
	$scope.campaignSearch = {};
	$scope.campaignList = [];
	$scope.currentPage = 1;
	$scope.limit= 10;
	$scope.campaignDetails={};
	$scope.status=false;
	$scope.campaignContactSearch = {};
	$scope.countryList = {};
	$scope.stateList = {};
	$scope.industryDetailsList = {};
	$scope.sicCodeList = {};
	
	$scope.fillCampaignCategory = function () {
		CampaignService.getCampaignCategory()
		.then(function (result) {
			$scope.campaignCategoryList = result.campaignCategoryList;
		});
	};
	
	//Gets the Campaign Status Metadata
	$scope.fillCampaignStatus = function () {
		CampaignService.getCampaignStatus()
		.then(function (result) {
			$scope.campaignStatusList = result.campaignStatusList;
		});
	};
	
	//Gets the Campaign Type Metadata
	$scope.fillCampaignType = function () {
		CampaignService.getCampaignType()
		.then(function (result) {
			
			$scope.campaignTypeList = result.campaignTypeList;
		});
	};
	
	//Gets the Business Unit Metadata
	$scope.fillCampaignBusinessUnit = function () {
		CampaignService.getCampaignBusinessUnit()
		.then(function (result) {
			$scope.businessUnitList = result.campaignBusinessUnitList;
			
		});
	};
	
	//Gets the Campaign Region Metadata
	$scope.fillCampaignRegion = function () {
		CampaignService.getCampaignRegion()
		.then(function (result) {
			$scope.regionList = result.campaignRegionList;
		});
	};
	
	//Gets the Service Offering Metadata
	$scope.fillServiceOffering = function () {
		CampaignService.getServiceOffering()
		.then(function (result) {
			$scope.serviceOfferingList = result.serviceOfferingList;
		});
	};
	
	//clears the search filter
	$scope.reset = function(){
		$scope.campaignSearch = {};
		getCampaignList();
	}
	
	//get another portions of data on page changed
	 $scope.pageChanged = function() {
		 getCampaignList();
	 };
	
	//Loads the campaign List initially
	getCampaignList();
	
	//Gets the campaign List
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
			  campaignNumber = $scope.campaignSearch.campaignNumber;
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
    $scope.clearfield = function()
	 {
		 $http({
		   method: 'POST',
		   url: "configuration/getCampaignOwner",
		   headers : {
           	'Content-Type' : undefined
           },
		   transformRequest : angular.identity,
		   data: $scope.campaignDetails.OwnerName
		 }).then(
		   function (result) {
		     var data = result.data;
		     if(result.data.length==0)
		    	 {
		    	 scope.campaignDetails.OwnerName='';
		    	 }
		     response(data);
		   }, function (error) {
		     var data = error.data;
		   });
	 }
    
    $scope.saveCampaignDetails= function()
    {    
    	var date=$scope.campaignDetails.plannedRunDate;
    
    	console.log($scope.campaignDetails.plannedRunDate);
    	if (date == '' || date== undefined ) 
    	{
       		alert("SELECT A VALID DATE FROM CALENDAR");
            return;
    	    }
    	else
    	 {
    		alert($scope.campaignDetails);
    	
    	console.log($scope.campaignDetails);
    	 $scope.status=true;
    	 }
    	
    }
    
    $scope.disableType=function()
    {
    	return false;
    }
    
    $scope.confirmChange = function(ev,value){
		 console.log("value="+value);
		 
		    
		    /*if(deconfigureList == ''){
		    	 $window.alert("Please select atleast single item to deconfigure");
		    }else{
		    	getDeConfiguration(deconfigureList,ev);
		    }*/
	 }
	//Deconfiguring selected record in the list
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
	 $scope.showNext=function()
	 {
		
	 }
	 
	//siddhesh
		$scope.fillCountryList = function() {
			ContactsService.getContacts().then(function(result) {
				$scope.countryList = result.countryList;
			});
		};

		$scope.countryChange = function(country) {
			console.log("countryChange" + country);
			$scope.stateList = country.states;
		};

		$scope.fillRevenues = function() {
			ContactsService.getRevenues().then(function(result) {
				$scope.revenueList = result.revenueDetailsList;
			});
		};
		$scope.fillCompanySizes = function() {
			ContactsService.getCompanySizes().then(function(result) {
				$scope.companySizesList = result.companySizeDetails;
			});
		};

		$scope.fillIndustries = function() {
			ContactsService.getIndustries().then(function(result) {
				$scope.industryDetailsList = result.industryDetails;
			});
		};

		$scope.IndustryChange = function(industry) {
			console.log("IndustryChange" + industry);
			$scope.sicCodeList = industry.sicCodeDetailsList;
		};

		$scope.fillContactStatus = function() {
			ContactsService.getContactStatus().then(function(result) {
				$scope.contactStatuslist = result.contactStatuslist;
			});
		};

		$scope.fillJobFunctions = function() {
			ContactsService.getJobFunctions().then(function(result) {
				$scope.jobFunctionList = result.jobFunctionList;
			});
		};

		$scope.fillJobTitles = function() {
			ContactsService.getJobTitles().then(function(result) {
				$scope.jobTitleList = result.jobTitleList;
			});
		};
		
		
		//Save contact Search Name 
		 $scope. SaveContactSearch = function() {
			 SaveContactSearchName();
		 }; 
	 
		 function SaveContactSearchName() {
		    	if((null == $scope.campaignContactSearch.countryName || undefined == $scope.campaignContactSearch.countryName) 
		    			&&(null == $scope.campaignContactSearch.revenue || undefined == $scope.campaignContactSearch.revenue)
		    			&&(null == $scope.campaignContactSearch.stateName || undefined == $scope.campaignContactSearch.stateName) 
		    			&&(null == $scope.campaignContactSearch.jobFunction || undefined == $scope.campaignContactSearch.jobFunction)
		    			&&(null == $scope.campaignContactSearch.industry || undefined == $scope.campaignContactSearch.industry)
		    			&&(null == $scope.campaignContactSearch.sicCode || undefined == $scope.campaignContactSearch.sicCode)
		    			&&(null == $scope.campaignContactSearch.companySize || undefined == $scope.campaignContactSearch.companySize)
		    			&&(null == $scope.campaignContactSearch.jobTitle || undefined == $scope.campaignContactSearch.jobTitle)
		    			&&(null == $scope.campaignContactSearch.serviceOffering || undefined == $scope.campaignContactSearch.serviceOffering)
		    			&&(null == $scope.campaignContactSearch.companyEmailId || undefined == $scope.campaignContactSearch.companyEmailId || "" == $scope.campaignContactSearch.companyEmailId)
		    			&&(null == $scope.campaignContactSearch.zipcode || undefined == $scope.campaignContactSearch.zipcode || "" == $scope.campaignContactSearch.zipcode)
		    			&&(null == $scope.campaignContactSearch.companyName || undefined == $scope.campaignContactSearch.companyName || "" == $scope.campaignContactSearch.companyName)
		    			&&(null == $scope.campaignContactSearch.contactSource || undefined == $scope.campaignContactSearch.contactSource || "" == $scope.campaignContactSearch.contactSource)
		    			&&(null == $scope.campaignContactSearch.website || undefined == $scope.campaignContactSearch.website || "" == $scope.campaignContactSearch.website)
		    			&&(null == $scope.campaignContactSearch.contactEmailId || undefined == $scope.campaignContactSearch.contactEmailId || "" == $scope.campaignContactSearch.contactEmailId)
		    			&&(null == $scope.campaignContactSearch.firstName || undefined == $scope.campaignContactSearch.firstName || "" == $scope.campaignContactSearch.firstName)
		    			&&(null == $scope.campaignContactSearch.lastName || undefined == $scope.campaignContactSearch.lastName || "" == $scope.campaignContactSearch.lastName)
		    			&&(null == $scope.campaignContactSearch.phoneNumber || undefined == $scope.campaignContactSearch.phoneNumber || "" == $scope.campaignContactSearch.phoneNumber)
		    			&&(null == $scope.campaignContactSearch.mobile || undefined == $scope.campaignContactSearch.mobile || "" == $scope.campaignContactSearch.mobile)
		    			&&(null == $scope.campaignContactSearch.contactAddedBy || undefined == $scope.campaignContactSearch.contactAddedBy || "" == $scope.campaignContactSearch.contactAddedBy)
		    			&&(null == $scope.campaignContactSearch.contactUpdateDate || undefined == $scope.campaignContactSearch.contactUpdateDate || "" == $scope.campaignContactSearch.contactUpdateDate)
		    			&&(null == $scope.campaignContactSearch.contactAddedDate || undefined == $scope.campaignContactSearch.contactAddedDate || "" == $scope.campaignContactSearch.contactAddedDate)
		    	){
		    		alert("Select Any One Field");
		    	}else{
		    		$http({
		    			method : 'POST',
		    			url : 'campaign/saveSearchName',
		    			data : JSON.stringify($scope.campaignContactSearch)
		    		}).success(function(response) {


		    		}).error(function(error) {
		    			console.log(error);
		    		});
		    	}		    	
		    }
});

