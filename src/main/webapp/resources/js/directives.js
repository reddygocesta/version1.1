myapp.directive('access', [
    'AuthSharedService',
    function (AuthSharedService) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var roles = attrs.access.split(',');
                if (roles.length > 0) {
                    if (AuthSharedService.isAuthorized(roles)) {
                        element.removeClass('hide');
                    } else {
                        element.addClass('hide');
                    }
                }
            }
        };
    }]);

myapp.directive('numbersOnly', function(){
	return {
	     require: 'ngModel',
	     link: function(scope, element, attrs, modelCtrl) {
	       modelCtrl.$parsers.push(function (inputValue) {
	           // this next if is necessary for when using ng-required on your input. 
	           // In such cases, when a letter is typed first, this parser will be called
	           // again, and the 2nd time, the value will be undefined
	           if (inputValue == undefined) return '' 
	           var transformedInput = inputValue.replace(/[^0-9-]/g, ''); 
console.log("value" + value);
	           if (transformedInput!=inputValue) {
				  modelCtrl.$setViewValue(transformedInput);
	              modelCtrl.$render();
	           }       
			   

	           return transformedInput;         
	       });
	     }
	 };
});

myapp.directive('lettersOnly', function(){
	return {
	     require: 'ngModel',
	     link: function(scope, elm, attrs, modelCtrl) {
	       modelCtrl.$parsers.push(function (inputValue) {
	           // this next if is necessary for when using ng-required on your input. 
	           // In such cases, when a letter is typed first, this parser will be called
	           // again, and the 2nd time, the value will be undefined
	           if (inputValue == undefined) return '' 
	           var transformedInput = inputValue.replace(/[^a-z A-Z]/g, ''); 
			   
				if (transformedInput!=inputValue) {
				  modelCtrl.$setViewValue(transformedInput);
	              modelCtrl.$render();
	           }         

	           return transformedInput;         
	       });
		   
	     }
	 };
});

myapp.directive('emailAddress', function(){
	return {
	     restrict: 'A',
	        require: 'ngModel',
	        link: function (scope, element, attr, ngModelCtrl) {
	            var pattern = /[^a-zA-Z0-9-+@_.]/g;
	            function fromUser(text) {
	                if (!text)
	                    return text;

	                var transformedInput = text.replace(pattern, '');
	                if (transformedInput !== text) {
	                    ngModelCtrl.$setViewValue(transformedInput);
	                    ngModelCtrl.$render();
	                }
	                return transformedInput;
	            }
	            ngModelCtrl.$parsers.push(fromUser);
	        }
	 };
});

myapp.directive("limitTo", [function() {
    return {
        restrict: "A",
        link: function(scope, elem, attrs) {
            var limit = parseInt(attrs.limitTo);
            angular.element(elem).on("keyup", function(e) {
                if (this.value.length == limit) e.preventDefault();
            });
        }
    }
}]);

myapp.directive("ngUnique", function(EmailCheckService) {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function (scope, element, attrs, ngModel) {
	      element.bind('keypress keyup cut blur', function (e) {
	        if (!ngModel || !element.val()) return;
	        var keyProperty = scope.$eval(attrs.ngUnique);
	        var currentValue = element.val();
	        EmailCheckService.checkUniqueValue(keyProperty.key, keyProperty.property, currentValue)
	          .then(function (unique) {

				var condition = true;
				if(unique) {
					condition = false;
				}else {
					condition = true;
				}
				
				if(element.val() == ''){
					ngModel.$setValidity('unique', true); 
				} else if(element.val() != ''){
                    ngModel.$setValidity('unique', condition); 
                } else {
					//No Action
				}
				
				

			});

	        });
	      }
	    }
});

myapp.directive("ngDigit", function(EmailCheckService) {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function (scope, element, attrs, ngModel) {
	      element.bind('keypress keyup cut blur', function (e) {
	        if (!ngModel || !element.val()) return;
	        var keyProperty = scope.$eval(attrs.ngUnique);
	        var currentValue = element.val();
				
				var condition = false;
				console.log("Phone Number Length : "+element.val().length);
				if(element.val().length == 0 || element.val().length == 14) {
					condition = true;
					ngModel.$setValidity('digit', condition); 
				} else {
					condition = false;
					ngModel.$setValidity('digit', condition); 
				}
				
			});
	      }
	    }
});

myapp.directive('textMaxlength', function() {
	  return {
	    require: 'ngModel',
	    link: function (scope, element, attrs, ngModelCtrl) {
	      var maxlength = Number(attrs.textMaxlength);
	      function fromUser(text) {
	          if (text.length > maxlength) {
	            var transformedInput = text.substring(0, maxlength);
	            ngModelCtrl.$setViewValue(transformedInput);
	            ngModelCtrl.$render();
	            return transformedInput;
	          } 
	          return text;
	      }
	      ngModelCtrl.$parsers.push(fromUser);
	    }
	  }; 
});

myapp.directive('phoneInput', function($filter, $browser) {
    return {
		restrict: 'A',
        require: 'ngModel',
        link: function($scope, $element, $attrs, ngModelCtrl) {
            var listener = function() {
                var value = $element.val().replace(/[^0-9]/g, '');

                $element.val($filter('tel')(value, false));
			};

            // This runs when we update the text field
            ngModelCtrl.$parsers.push(function(viewValue) {
				var condition = false;
				var number = viewValue.replace(/[^0-9]/g, '').slice(0,10);
				if(number.length == 0 || (number.length == 10 && Number(number) != 0)) {
					condition = true;
					ngModelCtrl.$setValidity('digit', condition); 
					ngModelCtrl.$setValidity('digitinvalid', condition); 
				} else if(number.length == 10 && Number(number) == 0) {
					condition = false;
					ngModelCtrl.$setValidity('digitinvalid', condition); 
					ngModelCtrl.$setValidity('digit', true);
				} else {
					condition = false;
					ngModelCtrl.$setValidity('digit', condition); 
					ngModelCtrl.$setValidity('digitinvalid', true); 
				}
                return viewValue.replace(/[^0-9]/g, '').slice(0,10);
            });

            // This runs when the model gets updated on the scope directly and keeps our view in sync
            ngModelCtrl.$render = function() {
				
                $element.val($filter('tel')(ngModelCtrl.$viewValue, false));
            };

           
            $element.bind('keydown keypress keyup blur paste cut change', function(event) {
                var key = event.keyCode;
                // If the keys include the CTRL, SHIFT, ALT, or META keys, or the arrow keys, do nothing.
                // This lets us support copy and paste too
                if (key == 91 || (15 < key && key < 19) || (37 <= key && key <= 40)){
                    return;
                }
                $browser.defer(listener); // Have to do this or changes don't get picked up properly
            });

            
        }

    };
});

myapp.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
}]);

myapp.filter('tel', function () {
    return function (tel) {
        console.log(tel);
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        if (value.match(/[^0-9]/)) {
            return tel;
        }

        var country, city, number;

        switch (value.length) {
            case 1:
            case 2:
            case 3:
                city = value;
                break;

            default:
                city = value.slice(0, 3);
                number = value.slice(3);
        }

        if(number){
            if(number.length>3){
                number = number.slice(0, 3) + '-' + number.slice(3,7);
            }
            else{
                number = number;
            }

            return ("(" + city + ") " + number).trim();
        }
        else{
            return "(" + city;
        }

    };
});

myapp.directive('lowercase', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attrs, modelCtrl) {
        var capitalize = function(inputValue) {
          if (inputValue == undefined) inputValue = '';
          var capitalized = inputValue.toLowerCase();
          if (capitalized !== inputValue) {
            modelCtrl.$setViewValue(capitalized);
            modelCtrl.$render();
          }
          return capitalized;
        }
        modelCtrl.$parsers.push(capitalize);
        capitalize(scope[attrs.ngModel]); // capitalize initial value
      }
    };
  });


myapp.directive('tooltip', function(){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){
            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});

myapp.directive('wmBlock', function ($parse) {
    return {
        scope: {
          wmBlockLength: '='
        },
        link: function (scope, elm, attrs) {
         
          elm.bind('keypress', function(e){
           
            if(elm[0].value.length > scope.wmBlockLength){
              e.preventDefault();
              
              return false;
            }
          });
        }
    }   
});



myapp.directive('validPasswordC', function() {
	  return {
	    require: 'ngModel',
	    scope: {
	      reference: '=validPasswordC'
	    },
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue, $scope) {

	        var noMatch = viewValue != scope.reference
	        ctrl.$setValidity('noMatch', !noMatch);
	        return (noMatch)?noMatch:!noMatch;
	      });

	      scope.$watch("reference", function(value) {;
	        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

	      });
	    }
	  }
});

myapp.directive('campaignownerautocomplete', ['$http', function($http,$scope) {
    return function (scope, element, attrs) {
        element.autocomplete({
            minLength:1,
            source:function (request, response) {
            	var formData = new FormData();
	            formData.append("ownerName",request.term);
			   
			   $http({
				   method: 'POST',
				   url: "configuration/getCampaignOwner",
				   headers : {
	                	'Content-Type' : undefined
	                },
				   transformRequest : angular.identity,
				   data: formData
				 }).then(
				   function (result) {
				     var data = result.data;
				     if(result.data.length==0)
				    	 {
				    	 scope.campaignConfiguration.OwnerName='';
				    	 }
				     response(data);
				   }, function (error) {
				     var data = error.data;
				   });
            },
            focus:function (event, ui) {
                element.val(ui.item.firstName+' '+ui.item.lastName);
                return false;
            },
            select:function (event, ui) {
                scope.campaignConfiguration.campaingOwner = ui.item ;//campaingOwner
                scope.$apply;
                return false;
            },
            change:function (event, ui) {
                if (ui.item === null) {
                    scope.campaignConfiguration.OwnerName = null;
                    scope.campaignConfiguration.OwnerName='';
                }
            },
            close:function (event,ui){
            	if (ui.item === null) {
                    scope.campaign.OwnerNameValue = null;
                    scope.campaignConfiguration.OwnerName='';
                }
            },
              
            }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
        	  return $( "<li>" )
        	    .data( "ui-autocomplete-item", item )
        	    .append( "<a>" + item.firstName + "<br>" + item.lastName  + "</a>" )
        	    .appendTo( ul );
        	};
    }
}]);


myapp.directive('serviceautocomplete', ['$http', function($http,$scope) {
    return function (scope, element, attrs) {
        element.autocomplete({
            minLength:1,
            source:function (request, response) {
            	var formData = new FormData();
	            formData.append("serviceOfferingId",request.term);
			   
			   $http({
				   method: 'POST',
				   url: "configuration/getServiceOffering",
				   headers : {
	                	'Content-Type' : undefined
	                },
				   transformRequest : angular.identity,
				   data: formData
				 }).then(
				   function (result) {
				     var data = result.data;
				     if(result.data.length==0)
			    	 {
			    	 scope.campaignConfiguration.serviceOffering.serviceName='';
			    	 }
				     response(data);
				   }, function (error) {
				     var data = error.data;
				   });
            },
            focus:function (event, ui) {
                element.val(ui.item.serviceName);
                return false;
            },
            select:function (event, ui) {
                scope.campaignConfiguration.serviceOffering = ui.item ;
                scope.$apply;
                return false;
            },
            change:function (event, ui) {
                if (ui.item === null) {
                    scope.campaignConfiguration.serviceOffer = null;
                	scope.campaignConfiguration.serviceOffering.serviceName='';
                }
            }
        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
        	  return $( "<li>" )
        	    .data( "ui-autocomplete-item", item )
        	    .append( "<a>" + item.serviceName + "</a>" )
        	    .appendTo( ul );
        	};
    }
}]);


myapp.directive('blur', function () {
    return function (scope, elem, attrs) {
        elem.bind('blur', function () {
            scope.$apply(attrs.blur);
        });
    };
});

myapp.directive("ngContactlistunique", function(ContactListCheckService) {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function (scope, element, attrs, ngModel) {
	      element.bind('keypress keyup cut blur', function (e) {
	        if (!ngModel || !element.val()) return;
	       
	        var currentValue = element.val();
	        
	        ContactListCheckService.checkUniqueContactList(currentValue)
	          .then(function (unique) {
	        	 var condition = true;
				if(unique) {
					condition = false;
				}else {
					condition = true;
				}
				
				if(element.val() == ''){
					ngModel.$setValidity('unique', true); 
				} else if(element.val() != ''){
                ngModel.$setValidity('unique', condition); 
            } else {
					//No Action
				}

			});
	        });
	      }
	    }
});

/*myapp.directive('autoComplete', function($timeout) {
    return function(scope, iElement, iAttrs) {
    	     console.log("scope= "+scope+" iElement="+iElement+"iAttrs="+iAttrs);
    	     console.log(scope);
    	     console.log(iElement);
    	     console.log(iAttrs);
            iElement.autocomplete({
                source: scope[iAttrs.uiItems],
                select: function() {
                    $timeout(function() {
                      iElement.trigger('input');
                    }, 0);
                }
            });
    };
});*/

myapp.directive('ngdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
                dateFormat: 'mm/dd/yy',
                onSelect: function (campaignRunDate) {
                    scope.campaignSearch.campaignRunDate = campaignRunDate;
                    scope.$apply();
                }
            });
        }
    };
});

myapp.directive('contactserviceautocomplete', ['$http', function($http,$scope) {
    return function (scope, element, attrs) {
        element.autocomplete({
            minLength:1,
            source:function (request, response) {
            	var formData = new FormData();
	            formData.append("serviceOfferingId",request.term);
			   
			   $http({
				   method: 'POST',
				   url: "configuration/getServiceOffering",
				   headers : {
	                	'Content-Type' : undefined
	                },
				   transformRequest : angular.identity,
				   data: formData
				 }).then(
				   function (result) {
				     var data = result.data;
				     response(data);
				     if(result.data.length==0)
			    	 {
				    	scope.searchContact.contactServiceOffering='';
			    	 }
				   }, function (error) {
				     var data = error.data;
				   });
            },
            focus:function (event, ui) {
                element.val(ui.item.serviceName);
                return false;
            },
            select:function (event, ui) {
                scope.searchContact.contactServiceOffering = ui.item ;
                scope.$apply;
                return false;
            },
            change:function (event, ui) {
                if (ui.item === null) {
                    scope.searchContact.contactServiceOffer = null;
                    scope.searchContact.contactServiceOffer='';
                }
            }
        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
        	  return $( "<li>" )
        	    .data( "ui-autocomplete-item", item )
        	    .append( "<a>" + item.serviceName + "</a>" )
        	    .appendTo( ul );
        	};
    }
}]);
