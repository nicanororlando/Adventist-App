"use-strict";

angular.module("buttonScrollTop", ["ngMaterial"]).component("buttonScrollTop", {
  templateUrl: "./components/ButtonScrollTop/index.html",
  controller: function ($window, $scope) {
    var self = this;

    this.isScrolling = false;

    $window.onscroll = () => {
      if ($window.scrollY != 0) self.isScrolling = true;
      else self.isScrolling = false;
      $scope.$apply();
    };

    this.scrollTop = () => {
      // $mdUtil.animateScrollTo($window, 0, 200);
      $window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth",
      });
    };
  },
});
