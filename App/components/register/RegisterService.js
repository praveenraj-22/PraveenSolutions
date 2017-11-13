
var RegisterModule= angular.module('RegisterModule',[]) /*creating a new module **/

RegisterModule.service('RegisterService',['REST_URI','$http',function(REST_URI,$http){
    this.register=function(user){                               /** this.register-> function of ng-click */
        console.log('content reached to register service');
    return  $http.post(REST_URI +'/registerUser',user)
    .then(
        function(response){
            return response.data;
        }
    )
}

    this.getUser=function(user,username){
        console.log('content reached to getuser service');
        return  $http.post(REST_URI +'/getuser/'+username,user)
            
    }

    this.updateProfile=function(user){
        console.log('content reached to update service');
        return  $http.post(REST_URI +'/updateprofile',user)
     
    }

}]);
