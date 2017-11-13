

var JobModule= angular.module('JobModule',[]) /*creating a new module **/

JobModule.service('JobService',['REST_URI','$http',function(REST_URI,$http){

    this.Savejob=function(job,username){
        console.log("content reached to job service");
        return $http.post(REST_URI+'/savejob/'+username,job)
    }

    this.listOfJobs=function(job){
        console.log('content reached to list of job service');
        return $http.get(REST_URI+'/getalljobs',job)
    }
/*
    this.getJobById=function(id,job){
        console.log("content reached to get by id")
        return $http.get(REST_URI+"/getjob/"+id,job)
    
    }*/
    
    this.getJob=function(id,job){
        console.log("content reached to get by id");
        return $http.get(REST_URI+"/getjob/"+id,job)
        }
    
}])