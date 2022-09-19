var isCard;

angular.module("imgTextHeader", []).component("imgTextHeader", {
  templateUrl: "./components/ImgTextHeader/index.html",
  bindings: {
    title: "<",
    description: "<",
  },
  controller: function ImgTextHeaderController($scope, $http) {
    $scope.isLoading = function () {
      return $http.pendingRequests.length > 0;
    };
  },
});
