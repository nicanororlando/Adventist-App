var isCard;

angular.module("itdlCard", []).component("itdlCard", {
  templateUrl: "./components/ITDLCard/index.html",
  bindings: {
    data: "=",
  },
  controller: function ($scope, $window) {
    $scope.openLink = function (link) {
      $window.open(link + "/download");
    };
  },
});
