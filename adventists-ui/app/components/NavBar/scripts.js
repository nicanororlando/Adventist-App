angular.module("navBar", []).component("navBar", {
  templateUrl: "./components/NavBar/index.html",
  controller: function NavBarController($mdSidenav) {
    this.active = false;
    this.toggleRight = () => {
      $mdSidenav("right").toggle();
    };
  },
});
