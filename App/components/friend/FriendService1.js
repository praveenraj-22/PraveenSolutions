/*
app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:8007/praveensolutions/"
	friendService.suggestedUsers=function(){
		return $http.get(BASE_URL + "/suggestedusers")//List of user objects
	}
	friendService.sendFriendRequest=function(toId){
		return $http.post(BASE_URL +"/friendrequest/"+toId)
	}
	
	friendService.pendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	friendService.updatePendingRequest=function(request){
		return $http.put(BASE_URL + "/updatependingrequest",request)//request is Friend object with updated status(A/D).
	}
	friendService.getUserDetails=function(fromId){
		return $http.get(BASE_URL + "/getuserdetails/"+fromId)
	}
	
	friendService.getFriends=function(){
		return $http.get(BASE_URL + "/getfriends")
	}
	
	return friendService;
})
*/
var FriendModule1 = angular.module('FriendModule1', [])

FriendModule1.service('FriendService1', ['REST_URI', '$http', function (REST_URI, $http) {


	this.suggestedUser=function(user,username){
	return $http.get(REST_URI+"/suggestedusers/"+username,user)
	}
	this.sendFriendRequest=function(toUsername,username){
		return $http.get(REST_URI+"/friendrequest/"+toUsername+"/"+username);
		}
	this.listOfPendingRequests=function(user,username){
		return $http.get(REST_URI+"/pendingrequests/"+username,user)
	}
	
/*
	this.updatePendingRequests=function(request){
		return $http.put(REST_URI+"/updatependingrequests/",request)
		}
	*/
	this.updatePendingRequests=function(fromUser,status,username,friend){
		return $http.put(REST_URI+"/updatependingrequests/"+fromUser+"/"+status+"/"+username,friend)
		}
	this.friendsList=function(user,username){
		return $http.get(REST_URI+"/friends/"+username,user)
		}

	}])