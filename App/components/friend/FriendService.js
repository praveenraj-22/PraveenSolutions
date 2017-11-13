var FriendsModule = angular.module('FriendsModule', []);



FriendsModule.service('FriendsService', ['$http', '$location', '$timeout', 'REST_URI', function ($http, $location, $timeout, REST_URI) {

    this.uploadFileToUrl = function (file, user) {
        var fd = new FormData();
        fd.append('file', file);

        $http({
            method: 'POST',
            url: REST_URI + '/image/upload/' + user.id, // The URL to Post.
            headers: { 'Content-Type': undefined }, // Set the Content-Type to undefined always.
            data: fd,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        }).then(
            function (response) {
                $location.path('/user');
            }, function (error) {
                alert(error);
            }
            );

    }

    this.getFrndRequests = function (user) {
        console.log('Posting request to : ' + REST_URI + '/list/friends')
        return $http.post(REST_URI + '/list/friends', user).then(
            function (response) {

                return response.data;
            }
            , function (error) {

                return error;
            }
        );
    }

    this.searchFriends = function (name) {
        return $http.post(REST_URI + '/search', name).then(
            function (response) {

                return response.data;
            }
            , null
        );
    }

    this.sendRequest = function (friends) {
        return $http.post(REST_URI + '/send/friend/request', friends).then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }

    this.acceptRequest = function (id, cuid) {
        return $http.post(REST_URI + '/friend/accept/' + cuid, id).then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }

    this.getUsers = function (friend) {
        return $http.post(REST_URI + '/all/users',friend).then(
            function (response) {

                return response.data;
            }
            , function (error) {
                return error;
            }
        );
    }
/*
var friendService={}

friendService.suggestedUsers=function(){
    return $http.get(REST_URI+ "/suggestedusers")//List of user objects
 }
friendService.sendFriendRequest=function(toId){
    return $http.post(REST_URI +"/friendrequest/"+toId)
}

friendService.pendingRequests=function(){
    return $http.get(REST_URI + "/pendingrequests")
}
friendService.updatePendingRequest=function(request){
    return $http.put(REST_URI + "/updatependingrequest",request)//request is Friend object with updated status(A/D).
}
friendService.getUserDetails=function(fromId){
    return $http.get(REST_URI + "/getuserdetails/"+fromId)
}

friendService.getFriends=function(){
    return $http.get(REST_URI + "/getfriends")
}

return friendService;
*/
}]);