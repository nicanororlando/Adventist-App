"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("completeBelief").component("completeBelief", {
  templateUrl: "./pages/completeBelief/belief.template.html",
  controller: [
    "$routeParams",
    function CompleteBeliefsController($routeParams, $http) {
      var self = this;

      this.welcome = "Belief";

      let url =
        "http://localhost:8080/api/complete-beliefs/the-holy-scriptures";
      // let url2 =
      //   "http://localhost:8080/api/complete-beliefs/" + $routeParams.beliefId;

      $http.get(url).then(
        (res) => {
          this.belief = res.data;
        },
        (res) => {
          this.belief = [{ name: "Error!" + res.status }];
        }
      );

      // this.beliefs = Belief.query();
    },
  ],
});
