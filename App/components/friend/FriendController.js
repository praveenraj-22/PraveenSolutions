app.controller('FriendsController', ['FriendsService', '$location', '$rootScope', '$cookieStore', '$scope', '$http', '$routeParams','REST_URI',
function (FriendsService, $location, $rootScope, $cookieStore, $scope, $http, $routeParams,REST_URI) {
	var me = this;
	me.friends = {};
	me.friendEntity = {};
	me.users = {};
	me.event = '';
	me.myFriends = {};
	me.id = '';
	me.friends = null;
	me.users = null;
	$scope.defaultImage = REST_URI+'/resources/images/default.png';
	console.log("Friends Controller Invoked");
	me.currentUser = $cookieStore.get('currentUser');
	me.imageUrl = REST_URI+'/resources/images/'+me.currentUser.id+'.jpg';
	me.imgPath = REST_URI+'/resources/images/';
	FriendsService.getFrndRequests(me.currentUser).then(
		function (data) {
			me.myFriends = data;

		}, function (error) {
			alert(error);
		}
	);

	me.uploadFile = function () {
	   
		console.log('file is ');
		console.dir($scope.myFile);
		FriendsService.uploadFileToUrl($scope.myFile,me.currentUser);
		me.imageUrl = REST_URI+'/resources/images/'+me.currentUser.id+'.jpg';

	};

	me.myFrnds = function () {
		me.users = null;
		me.friends = null;
		FriendsService.getFrndRequests(me.currentUser).then(
			function (data) {
				me.myFriends = data;

			}, function (error) {
				alert(error);
			}
		);
	}

	me.getUsers = function () {
		me.friends = null;
		me.myFriends = null;
		console.log('Fetching Users....!!');
		FriendsService.getUsers(me.currentUser).then(
			function (data) {
				console.log('Users fetched');
				me.users = data;

			}, function (error) {
				alert(error);
			}
		);
	}

	me.frndRequests = function () {
		me.users = null;
		me.myFriends = null;
		console.log('Friend Controller reached...!!');
		FriendsService.getFrndRequests(me.currentUser).then(
			function (data) {
				me.friends = data;

			}, function (error) {
				alert(error);
			}
		);
	}

	me.accept = function (id) {
		console.log('Accepting request of ID: ' + id);
		FriendsService.acceptRequest(id,me.currentUser.id).then(
			function (data) {
				alert('Request accepted..!!');
			},
			function (error) {
				alert(error);
			}
		);
	}
	me.sendRequest = function (friend) {

			me.friendEntity.user = me.currentUser.id;
			me.friendEntity.friend = friend;
			console.log('Sending friend request...!!');
			FriendsService.sendRequest(me.friendEntity).then(
				function (data) {
					me.friend = data;
				},
				function (error) {
					alert(error);
				}
			);

	}

	if ($routeParams.id != null || $routeParams != 0 || $routeParams != '0') {

	}
	/*

	$scope.showUserDetails=false;
	function getSuggestedUsers(){
		FriendService.suggestedUsers().then(function(response){
			$scope.suggestedUsers=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
			else
			console.log(response.status)
		})
	}
	function pendingRequests(){
		FriendService.pendingRequests().then(function(response){
			$scope.pendingRequests=response.data//List of Friend objects [use only fromId])
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.sendFriendRequest=function(toId){
		FriendService.sendFriendRequest(toId).then(function(response){
			getSuggestedUsers();
			$location.path('/suggestedusers')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			else
			console.log(response.status)
		})
	}
	$scope.updatePendingRequest=function(request,value){
		console.log('pending request ' + request)
		request.status=value //value is 'A' for accept and 'D' for delete
		console.log('after assigning value to status  ' + request)
		FriendService.updatePendingRequest(request).then(function(response){
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	$scope.getUserDetails=function(fromId){
		$scope.showUserDetails=true
		FriendService.getUserDetails(fromId).then(function(response){
			$scope.user=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	function getFriends(){
	FriendService.getFriends().then(function(response){
		$scope.friends=response.data //List<Friend> select * from friend where status='A' and (fromId=? or toId=?)
		$rootScope.noOfFriends=$scope.friends.length
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	getSuggestedUsers();
	pendingRequests()
	getFriends();*/



}]);