'use strict';

myapp.controller('ContactsController', function($scope, $log, $q, $http,
		ContactsService, $routeParams,$window, $mdDialog, $rootScope) {

	$scope.searchContact = {};
	$scope.countryList = {};
	$scope.stateList = {};
	$scope.industryDetailsList = {};
	$scope.sicCodeList = {};
	$scope.isContactVisible = false;
	$scope.isAddCompany = false;
	$scope.isAddLocation = false;
	$scope.isCompanyModeChanged = false;
	$scope.isLocationModeChanged = false;
	$scope.isShowCompanyInfo = false;
	$scope.isShowLocationInfo = false;
	$scope.isShowAddCompanyBtn = false;
	$scope.isShowAddLocationBtn = false;

	$scope.fillCountryList = function() {
		ContactsService.getContacts().then(function(result) {
			$scope.countryList = result.countryList;
		});
	};

	$scope.countryChange = function(country) {
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

	$scope.resetContact = function() {
		$scope.searchContact = {};
		$scope.jobTitle = {};
		getContactsList();
	};

	$scope.fillIndustries = function() {
		ContactsService.getIndustries().then(function(result) {
			$scope.industryDetailsList = result.industryDetails;
		});
	};
	
	$scope.fillSicCodesfromIndustry = function(sicCodeDetails) {
		var sicList ={sicCodeDetails : sicCodeDetails};
		$scope.sicCodeList=sicList;
		
	};

	$scope.IndustryChange = function(industry) {
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
    
    $scope.showContact = function() {
    	$scope.isContactVisible = $scope.showContactDetails;	
	};
	
	$scope.showEditContactForm= function(contactId) {
	    $rootScope.editContactId = contactId;
	    $window.location.href = '#/editContacts';
	};
	
	$scope.changeCompanyMode = function() {
		$scope.isCompanyModeChanged = false;
		$scope.isAddCompany = false;
		$scope.isShowCompanyInfo = false;
		$scope.isShowLocationInfo = false;
		$scope.isShowAddCompanyBtn = false;
		$scope.isLocationModeChanged = false;
		$scope.isShowAddLocationBtn = false;
		$scope.isAddLocation = false;
		$scope.contact.companyDetails = {};
		$scope.contact.companyDetails.addressDetail = {};
		$scope.locationList = {};
		$scope.form.contactForm.companyName.$setValidity('unique', true); 
	};
	
	$scope.changeLocationMode = function() {
		$scope.isLocationModeChanged = false;
		$scope.isShowLocationInfo = false;
		$scope.isShowAddLocationBtn = false;
		$scope.isAddLocation = false;
		$scope.contact.companyDetails.addressDetail = {};
	};
	
	$scope.addCompany = function(){
		$scope.isAddCompany = true;
		$scope.isCompanyModeChanged = true;
		$scope.isShowCompanyInfo = true;
		$scope.isShowAddCompanyBtn = true;
		$scope.isAddLocation = false;
		$scope.addLocation();
		$scope.isLocationModeChanged = false;
	}
	
	$scope.addLocation = function(){
		$scope.isAddLocation = true;
		$scope.isLocationModeChanged = true;
		$scope.isShowLocationInfo = true;
		$scope.isShowAddLocationBtn = true;
	}
	
	$scope.locationChange = function(){
		$scope.isLocationModeChanged = true;
		$scope.isShowLocationInfo = true;
		$scope.isShowAddLocationBtn = true;
		console.log($scope.contact.companyDetails.addressDetail.country);
		var slist ={state : $scope.contact.companyDetails.addressDetail.state};
		$scope.stateList=slist;
	}
	
	$scope.param = $routeParams.param;

	$scope.searchContact = {};
	$scope.currentPage = 1;
	$scope.limit = 10;

	$scope.contactsList = [];
	getContactsList();

	function getContactsList() {
		
		$scope.checkAllStatus = false;
		
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

		if ($scope.searchContact.contactListName != undefined
				&& $scope.searchContact.contactListName != ''
				&& $scope.searchContact.contactListName != 'undefined') {
			contactListName = $scope.searchContact.contactListName;
		}
		if ($scope.searchContact.contactStatus != undefined
				&& $scope.searchContact.contactStatus != ''
				&& $scope.searchContact.contactStatus != 'undefined') {
			contactStatus = $scope.searchContact.contactStatus.status;
		}
		if ($scope.searchContact.country != undefined && $scope.country != ''
				&& $scope.searchContact.country != 'undefined') {

			countryName = $scope.searchContact.country.countryName;
		}
		if ($scope.searchContact.state != undefined
				&& $scope.searchContact.state != ''
				&& $scope.searchContact.state != 'undefined') {
			stateName = $scope.searchContact.state.stateName;
		}

		if ($scope.searchContact.jobFunction != undefined
				&& $scope.searchContact.jobFunction != ''
				&& $scope.searchContact.jobFunction != 'undefined') {
			jobFunction = $scope.searchContact.jobFunction.jobFunctionName;
		}

		if ($scope.searchContact.revenue != undefined
				&& $scope.searchContact.revenue != ''
				&& $scope.searchContact.revenue != 'undefined') {
			revenue = $scope.searchContact.revenue.revenueName;
		}
		if ($scope.searchContact.industry != undefined
				&& $scope.searchContact.industry != ''
				&& $scope.searchContact.industry != 'undefined') {
			industry = $scope.searchContact.industry.industryName;
		}
		if ($scope.searchContact.sicCode != undefined
				&& $scope.searchContact.sicCode != ''
				&& $scope.searchContact.sicCode != 'undefined') {
			sicCode = $scope.searchContact.sicCode.sicCodeName;
		}
		if ($scope.searchContact.companySize != undefined
				&& $scope.searchContact.companySize != ''
				&& $scope.searchContact.companySize != 'undefined') {
			companySize = $scope.searchContact.companySize.companySizeName;
		}
		if ($scope.searchContact.companyEmailId != undefined
				&& $scope.searchContact.companyEmailId != ''
				&& $scope.searchContact.companyEmailId != 'undefined') {
			companyEmailId = $scope.searchContact.companyEmailId;
		}
		if ($scope.searchContact.zipcode != undefined
				&& $scope.searchContact.zipcode != ''
				&& $scope.searchContact.zipcode != 'undefined') {
			zipcode = $scope.searchContact.zipcode;
		}
		if ($scope.searchContact.company != undefined
				&& $scope.searchContact.company != ''
				&& $scope.searchContact.company != 'undefined') {
			companyName = $scope.searchContact.company;
		}
		if ($scope.searchContact.contactSource != undefined
				&& $scope.searchContact.contactSource != ''
				&& $scope.searchContact.contactSource != 'undefined') {
			contactSource = $scope.searchContact.contactSource;
		}

		if ($scope.searchContact.website != undefined
				&& $scope.searchContact.website != ''
				&& $scope.searchContact.website != 'undefined') {
			website = $scope.searchContact.website;
		}
		if ($scope.searchContact.contactEmailId != undefined
				&& $scope.searchContact.contactEmailId != ''
				&& $scope.searchContact.contactEmailId != 'undefined') {
			contactEmailId = $scope.searchContact.contactEmailId;
		}

		if ($scope.searchContact.firstName != undefined
				&& $scope.searchContact.firstName != ''
				&& $scope.searchContact.firstName != 'undefined') {
			firstName = $scope.searchContact.firstName;
		}
		if ($scope.searchContact.lastName != undefined
				&& $scope.searchContact.lastName != ''
				&& $scope.searchContact.lastName != 'undefined') {
			lastName = $scope.searchContact.lastName;
		}
		if ($scope.searchContact.phoneNumber != undefined
				&& $scope.searchContact.phoneNumber != ''
				&& $scope.searchContact.phoneNumber != 'undefined') {
			phoneNumber = $scope.searchContact.phoneNumber;
		}
		if ($scope.searchContact.mobile != undefined
				&& $scope.searchContact.mobile != ''
				&& $scope.searchContact.mobile != 'undefined') {
			mobile = $scope.searchContact.mobile;
		}
		if ($scope.jobTitle != undefined && $scope.jobTitle != ''
				&& $scope.jobTitle != 'undefined') {
			jobTitle = $scope.jobTitle.jobTitleName;
		}
		if ($scope.searchContact.contactAddedBy != undefined
				&& $scope.searchContact.contactAddedBy != ''
				&& $scope.searchContact.contactAddedBy != 'undefined') {
			contactAddedBy = $scope.searchContact.contactAddedBy;
		}
		if ($scope.searchContact.contactAddedDate != undefined
				&& $scope.searchContact.contactAddedDate != ''
				&& $scope.searchContact.contactAddedDate != 'undefined') {
			var date = new Date($scope.searchContact.contactAddedDate);
			contactAddedDate = formatDate(date);

		}

		function formatDate(date) {
			var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
					+ d.getDate(), year = d.getFullYear();

			if (month.length < 2)
				month = '0' + month;
			if (day.length < 2)
				day = '0' + day;

			return [ year, month, day ].join('-');
		}

		if ($scope.searchContact.contactUpdateDate != undefined
				&& $scope.searchContact.contactUpdateDate != ''
				&& $scope.searchContact.contactUpdateDate != 'undefined') {
			var date = new Date($scope.searchContact.contactUpdateDate);
			contactUpdateDate = formatDate(date);
		}

		var begin = (($scope.currentPage - 1) * $scope.limit), end = begin
				+ $scope.limit;

		var parameters = {
			startRecord : begin,
			recordToShow : end,
			contactListName : contactListName,
			contactStatus : contactStatus,
			countryName : countryName,
			stateName : stateName,
			jobFunction : jobFunction,
			revenue : revenue,
			industry : industry,
			sicCode : sicCode,
			companySize : companySize,
			companyEmailId : companyEmailId,
			zipcode : zipcode,
			companyName : companyName,
			contactSource : contactSource,
			website : website,
			contactEmailId : contactEmailId,
			firstName : firstName,
			lastName : lastName,
			phoneNumber : phoneNumber,
			mobile : mobile,
			jobTitle : jobTitle,
			contactAddedBy : contactAddedBy,
			contactAddedDate : contactAddedDate,
			contactUpdateDate : contactUpdateDate
		};

		$http({
			method : 'POST',
			url : 'contact/getContacts',
			data : JSON.stringify(parameters)
		}).success(function(response) {
			$scope.total = response.totalCount;
			$scope.contactsList = response.contactList;
			if ($scope.contactsList.length == 0) {
				// angular.copy("No Records found", $scope.tableMessage);
			}
		}).error(function(error) {
			console.log(error);
		});

	}

	// Declaring the function to load data from database
	$scope.viewContact = function() {

		$http.get("contact/viewContact/" + $scope.param).then(
				function(response) {
					$scope.contact = response.data.contact;

				});
	};

	// get another portions of data on page changed
	$scope.pageChanged = function() {
		
		getContactsList();
	};

	if ($scope.contactsList.length == 0) {
		$scope.tableMessage = "No Records found";
	}
	
	// switch flag
	$scope.switchMessage = function(value) {
	    $scope[value] = !$scope[value];
	};

		
			$scope.getLocation = function(company){
				
				 var deferred = $q.defer();
				
				var formData = new FormData();
	            formData.append("companyId",company.companyId);
				
	            $http({
					   method: 'POST',
					   url: "contact/getLocationDetails",
					   headers : {
		                	'Content-Type' : undefined
		                },
					   transformRequest : angular.identity,
					   data: formData
					 }).then(function (result) {
					     
					     $scope.locationList = result.data;;
					   }, function (error) {
					     var data = error.data;
					   });
/*	            ContactsService.deleteContact(contact)
	            .then(function(response) {

	            ContactsService.getLocation(formData).then(function(response){
	            	deferred.resolve();
	            	$scope.locationList = response;
	            });*/
			}
			
			//It is used to save contact information.
			$scope.saveContactInfo = function() {

			    var deferred = $q.defer();
			    
			    $scope.contact.createdBy = $rootScope.account.login; 
				$scope.contact.companyDetails.previousCompanyId = $scope.previousCompanyId;
				$scope.contact.companyDetails.addressDetail.previousLocationId = $scope.previousLocationId;

			    ContactsService.saveContactInfo($scope.contact)
			        .then(function(response) {
			        	
			        deferred.resolve(response);
			        var contactId = $scope.contact.contactId;
					   
					if (contactId > 0) {
						if (response == 1) {
							$rootScope.messages = 'Contact details updated successfully.';
							$window.scrollTo(0, 0);
						} else {
							$rootScope.messages = 'Oops, unable to update contact details.';
							$window.scrollTo(0, 0);
						}
					} else {
						if (response == 1) {
							$rootScope.messages = 'Contact details added successfully.';
							$window.scrollTo(0, 0);
						} else {
							$rootScope.messages = 'Oops, unable to add contact details.';
							$window.scrollTo(0, 0);
						}
					   
					}

					$rootscope.editContactId='';
					$window.location.href = '#/contacts';
			            
			    });

			    return deferred.promise;
			}
			
		$scope.deleteContact = function(env){
			console.log($scope.configurationIds);
			 var deleteContactList = '';
			    $scope.contactsList.forEach(function(contacts) {

			      if (contacts.checkedStatus) {

			        // If this is not the first item
			        if (deleteContactList) {
			        	deleteContactList += ','
			        }
			        deleteContactList += contacts.contactId;
			      }

			    })
			    if(deleteContactList == ''){
			    	$scope.showConfirm(deleteContactList, env);
			    }else{
			    	$scope.showConfirm(deleteContactList, env);
			    }
		}
			
		//It is used to delete a contact with Modal comfirm dialog 
		$scope.showConfirm = function(deleteContactList, ev) {
			
			if(deleteContactList){
			    var confirm = $mdDialog.confirm()
			        .title('Deleting a Contact')
			        .content('Contact is being deleted. Are you okay to proceed?')
			        .targetEvent(ev)
			        .ok('Delete')
			        .cancel('Cancel');
	
	
			    
			    $mdDialog.show(confirm).then(function() {
	
			        var deferred = $q.defer();
	
			        ContactsService.deleteContact(deleteContactList)
			            .then(function(response) {
	
			                deferred.resolve();
	
			                if (response == true) {
			                    $scope.messages = 'Contact deleted successfully.';
			                    $window.scrollTo(0, 0);
			                } else {
			                    $scope.messages = 'Oops, unable to delete contact.';
			                    $window.scrollTo(0, 0);
			                }
			                
			              //Loads the contact List after successful deletion of user
			                getContactsList();
	
	
			            });
	
			        return deferred.promise;
	
	
			    });
			}else{
				 var alert = $mdDialog.alert()
			        .title('Deleting a Contact')
			        .content('Please select atleast single contact to delete')
			        .targetEvent(ev)
			        .ok('Ok')
			        
			      $mdDialog.show(alert)
	
			}
		};
		
		$scope.checkAll = function(){
			if($scope.checkAllStatus){
				$scope.contactsList.forEach(function(contacts) {
					contacts.checkedStatus = true;
				});
			}else{
				$scope.contactsList.forEach(function(contacts) {
					contacts.checkedStatus = false;
				});
			}
		}
		
//Declaring the function to load data from database
	$scope.fillContactList = function() {
		$scope.isEditNewCompany = false;
	    ContactsService.getContactInfo($rootScope.editContactId)
	        .then(function(result) {
	            $scope.contact = result.contact;
	        });
	};
	
	$scope.deleteCompanyInfo = function() {
		$scope.isEditNewCompany = true;
		$scope.isEditLocationInfo = true;
		$scope.previousCompanyId = $scope.contact.companyDetails.companyId;
		$scope.previousLocationId = $scope.contact.companyDetails.addressDetail.addressId;
		$scope.contact.companyDetails = {};
	};
	
	$scope.deleteLocationInfo = function() {
		$scope.isEditLocationInfo = true;
		$scope.getLocation($scope.contact.companyDetails);
		$scope.previousLocationId = $scope.contact.companyDetails.addressDetail.addressId;
		$scope.contact.companyDetails.addressDetail = {};
	};
})
 