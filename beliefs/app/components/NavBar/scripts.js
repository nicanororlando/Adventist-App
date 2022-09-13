angular.module("navBar", []).component("navBar", {
  templateUrl: "./components/NavBar/index.html",
  controller: function NavBarController($mdSidenav) {
    // $scope.currentNavItem = "beliefs";

    this.active = false;
    this.toggleRight = () => {
      $mdSidenav("right").toggle();
    };
  },
});
