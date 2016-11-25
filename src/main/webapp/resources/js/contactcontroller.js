'use strict';


myapp.controller('ContactsController', function ($scope, $log,$q, $http,  ContactsService ,$routeParams) {
    	
    	$scope.searchContact = {};
    	$scope.countryList={};
    	$scope.stateList={};
    	$scope.industryDetailsList={};
    	$scope.sicCodeList={};
    	
    	$scope.fillCountryList = function () {
    		ContactsService.getContacts()
			.then(function (result) {
				$scope.countryList = result.countryList;
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
			});
		};
		
		
		$scope.fillCompanySizes = function () {
    		ContactsService.getCompanySizes()
			.then(function (result) {
				$scope.companySizesList = result.companySizeDetails;
			});
		};
		
		
		$scope.resetContact = function () {
    		$scope.searchContact={};
    		$scope.jobTitle={};
    		getContactsList();
		};
		
		$scope.fillIndustries = function () {
    		ContactsService.getIndustries()
			.then(function (result) {
				$scope.industryDetailsList = result.industryDetails;
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
			});
		};
		
		$scope.fillJobFunctions = function () {
    		ContactsService.getJobFunctions()
			.then(function (result) {
				$scope.jobFunctionList = result.jobFunctionList;
			});
		};
		
		$scope.fillJobTitles = function () {
    		ContactsService.getJobTitles()
			.then(function (result) {
				$scope.jobTitleList = result.jobTitleList;
			});
		};
    	
    	
    	$scope.param = $routeParams.param;
    	
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
				  companyName = $scope.searchContact.company;
			   }
			  if($scope.searchContact.contactSource != undefined && $scope.searchContact.contactSource != ''  && $scope.searchContact.contactSource != 'undefined'){
				  contactSource = $scope.searchContact.contactSource;
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
			  if($scope.jobTitle != undefined && $scope.jobTitle != ''  && $scope.jobTitle != 'undefined'){
				  jobTitle = $scope.jobTitle.jobTitleName;
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
	    		
	    		 $http({
	    	           method: 'POST',
	    	           url: 'contact/getContacts',           
	    	           data:  JSON.stringify(parameters)
	    	       })
	    	       .success(function(response){  
	    	    	   $scope.total = response.totalCount;
				       $scope.contactsList=response.contactList;
				       if($scope.contactsList.length == 0){
				    	   //angular.copy("No Records found", $scope.tableMessage);
				       }
	    	       })
	    	      .error(function(error){
	    	   	   console.log(error);        	   
	    	      });
			   
			 }
		 
		 
		//Declaring the function to load data from database
			$scope.viewContact = function () {
				
				$http.get("contact/viewContact/"+$scope.param)
			     .then(function(response) {
			    	 $scope.contact = response.data.contact;

			     });
			};

		 
		//get another portions of data on page changed
		 $scope.pageChanged = function() {
			 getContactsList();
		 };
		 
		 if($scope.contactsList.length == 0){
			 $scope.tableMessage = "No Records found";
		 }
             
})
 