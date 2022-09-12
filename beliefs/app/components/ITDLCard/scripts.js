var isCard;

angular.module("itdlCard", []).component("itdlCard", {
  templateUrl: "./components/ITDLCard/index.html",
  bindings: {
    data: "=",
  },
  controller: function ITDLCardController($scope, $window) {
    $scope.openLink = function (link) {
      console.log(link);
      $window.open(link, "_blank");
    };
  },
});
