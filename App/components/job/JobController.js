app.controller('JobController', ['JobService', '$location', '$scope','$rootScope','$cookieStore','$routeParams', function (JobService, $location, $scope,$rootScope,$cookieStore,$routeParams) 
{

    var id=$routeParams.id;

$scope.Savejob=function(){
    console.log('content reached to job controller');
   console.log($scope.job)

    JobService.Savejob($scope.job,$rootScope.currentUser.username).then(function(response){

        alert("Job deatail is posted successfully");
        $location.path('/getalljobs')
    },function(response){
        if(response.status==401)
        $location.path('/login')
        else
        $location.path('/job')
    })
}




$scope.job=JobService.getJob(id,$scope.job).then(function(response){
    console.log("JOB ID IS :" + id);
    $scope.job=response.data;
    console.log(response.data)
    console.log(response.status)
},function(error)
{
    if(response.status==401)
    $location.path('/login')
})


}]);