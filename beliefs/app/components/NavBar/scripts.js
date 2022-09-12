angular.module("navBar", []).component("navBar", {
  templateUrl: "./components/NavBar/index.html",
  controller: function NavBarController($scope, $mdSidenav) {
    // $scope.currentNavItem = "beliefs";

    $scope.toggleRight = buildToggler("right");

    function buildToggler(componentId) {
      return function () {
        $mdSidenav(componentId).toggle();
      };
    }
  },
});
