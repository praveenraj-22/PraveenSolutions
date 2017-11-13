app.controller('LoginController', ['LoginService','BlogPostService','JobService', '$scope', '$location', '$rootScope', '$cookieStore', function (LoginService, BlogPostService ,JobService, $scope, $location, $rootScope, $cookieStore) {

    $rootScope.currentUser={};
    $scope.login = function () {
        console.log('content reached to login controller')

        LoginService.login($scope.user).then(function (response) {
            console.log(response.data)
            $rootScope.currentUser = response.data
            $cookieStore.put('currentUser', response.data)   //currenuser =name of user login

            $location.path('/home')
        }, function (response) {
            console.log(response.data)
            $scope.loginFail = response.data
            alert('please register first');
            $location.path('/login')
        })
    }

   $rootScope.logout=function(){
        console.log('content reached to logout controller');
            LoginService.logout($rootScope.currentUser).then(function(response){
                
                $rootScope.currentUser={};
                
                $cookieStore.remove('currentUser');
               $scope.currentUser=null;
                $location.path('/login');
    
            },function(response){
                console.log(response.data)
                
            })
             
        }
        
        

}]);

