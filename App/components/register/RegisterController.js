
app.controller('RegisterController', ['RegisterService', '$location', '$scope', function (RegisterService, $location, $scope) {
    
        $scope.user = {}; /*creating a object{}, user thatr we created in DAO */
         
    
    
        $scope.register = function ()                                               /**$scope.register ->ng-click value */
        {                   
            console.log("used data" + $scope.user);
    
            console.log('content reached to  RegisterController');
            console.log($scope.user.username);
    
            RegisterService.register($scope.user).then(function (response) 
            {
             console.log(response)
            //   console.log(response.data)
              //  $scope.success="Registered successfully.. please login again"
                alert('thank u for registering');
                $location.path('/login')
            },
                function (response)
                 {
               //      console.log(error)
                    $scope.response=response.status.data
                    console.log(response.status)
                    console.log(response.data)
            
                    $scope.error = response.data
                    if($scope.error.code==303)
                 {  
                    $scope.duplicateEmail=response.data;
                    alert('user email-id is already exists');
                 } 
                    if($scope.error.code==202)
                  { 
                            $scope.duplicateUsername=response.data;
                            alert('username already exists');
                  }  
                            if($scope.error.code==101)
                   {         
                            $scope.exception=response.data;
                   }        
    
                            $location.path('/Register')
                }
               

            )
            
        
        }
    
    }]);
    