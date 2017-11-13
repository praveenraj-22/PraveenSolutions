
app.controller('FriendController1', function ($scope, $rootScope,  $cookieStore, FriendService1, $location) {
	
	

	function listOfSuggesteduser()
	{
		$scope.suggestedUser=FriendService1.suggestedUser().then(function(response){
			alert('entering into suggested');
			console.log(response.data)
			$scope.suggestedUser=response.data;
		},function(response){
			console.log(response.data)
			console.log(response.status)
		})
	}
	
	function pendingRequests() {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		$scope.listOfPendingRequests = FriendService1.listOfPendingRequests($scope.user,$rootScope.currentUser.username).then(function (response) {
			alert('entering into pending');
			$scope.listOfPendingRequests = response.data;
		}, function (response) {
			console.log(response.data)
			console.log(response.status)
		})
	}
	
	function listOfFriends() {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		$scope.friendsList = FriendService1.friendsList($scope.user,$rootScope.currentUser.username).then(function (response) {
			$scope.friendsList = response.data;
		}, function (response) {
			console.log(response.data)
		})
	}
	/**
	 * to user class
	 */
	
	$scope.friendrequest = function (toUsername) {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		FriendService1.sendFriendRequest(toUsername,$rootScope.currentUser.username).then(function (response) {
			listOfSuggesteduser();
			$location.path('/suggestedpersons')
		}, function (response) {
			console.log(response.data)
		})
	}/*
	$scope.updatePendingRequest=function(request,value){
		console.log('pending request ' + request);
		request.status=value //value is 'A' for accept and 'D' for delete
		console.log('after assigning value to status  ' + request);
		FriendService.updatePendingRequest(request).then(function(response){
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}*/

	$scope.updatePendingRequests = function (fromUser,status,username) {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		FriendService1.updatePendingRequests(fromUser, status,$rootScope.currentUser.username,$scope.friend1).then(function (response) {
			pendingRequests();
			$location.path('/pendingrequests')
		}, function (response) {
			console.log(response.data)
			$location.path('/pendingrequests')
		})
	}
	listOfFriends()
	pendingRequests()
	listOfSuggesteduser()
})