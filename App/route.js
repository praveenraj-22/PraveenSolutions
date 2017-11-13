app.constant('REST_URI', 'http://localhost:8007/praveensolutions/'); //backend url where to get the values

app.config(function ($routeProvider) {


    // route for the home page
    $routeProvider

        .when("/home", {
            templateUrl: '/components/home/home.html',
           controller:'homecontroller'
        })

        .when('/post',{
            templateUrl:'/components/blogpost/post.html'
        })
       

        .when('/Register', {
            templateUrl: '/components/register/Register.html',
            controller: 'RegisterController',
        })
        

        .when('/login',{
            templateUrl: '/components/login/login.html',
            controller: 'LoginController'
        })
        .when('/editprofile',{
            templateUrl: '/components/register/editprofile.html',
            controller:'registercontroller1',
        })
 
        

/*
*job
*/

        .when('/addjob',{
            templateUrl:'/components/job/jobform.html',
            controller:'JobController',
        })
        .when('/getalljobs',{
            templateUrl:'/components/job/listofjob.html',
            controller:'jobcontroller1',
        })
        .when('/getjob/:id',{
            templateUrl:'/components/job/getjob.html',
            controller:'JobController',
        })
        .when('/job',{
            templateUrl:'/components/job/job.html',
          
        })




        .when('/saveblogpost', {
            templateUrl: '/components/blogpost/blogpostform.html',
            controller:'BlogPostController',
        })

        .when('/getallblogs',{
            templateUrl:'/components/blogpost/listofblogs.html',
            controller:'blogpostcontroller1'
        })
        .when('/blogpostforapproval/:id',{
            templateUrl:'/components/blogpost/blogpostapprovalform.html',
            controller:'blogdetailcontroller'
        })
        .when('/blogdetail/:id',{
            templateUrl:'/components/blogpost/blogdetail.html',
            controller:'blogdetailcontroller'
        })
        .when('/approvalstatus/:id',{
            templateUrl:'/components/blogpost/blogpoststatus.html',
            controller:'blogdetailcontroller'
        })
        
    

        .when('/uploadprofilepic',{
            templateUrl:'/components/profilepic/pic.html',
            controller:'piccontroller'
        })

/*
        .when('/friends/:id', {
            templateUrl: '/components/friend/friends.html',
            controller: 'FriendsController',
            controllerAs: 'frndCtrl'
        })
        .when('/user', {
            templateUrl: '/components/friend/user.html',
            controller: 'FriendsController',
            controllerAs: 'frndCtrl'
        })
        .when('/listUser', {
            templateUrl: '/components/friend/listUser.html'
        })
*/
        .when('/suggestedusers',{
            templateUrl:'/components/friend/suggestedusers.html',
            controller:'FriendController1' //From C to V
        })
        .when('/pendingrequests',{
            templateUrl:'/components/friend/pendingrequest.html',
            controller:'FriendController1'//From C to V
        })
        .when('/getfriends',{
            templateUrl:'/components/friend/listoffriends.html',
            controller:'FriendController1'//From C to V
        })

        .when('/chat',
    {
        templateUrl:'/components/chat/chat.html',
        controller:'ChatController',
        controllerAs:'ChatCtrl',
    })
    
        .otherwise({
            templateUrl: '/components/home/home.html',
     controller:'homecontroller'       
        })
});