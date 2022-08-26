"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("beliefs").component("beliefs", {
  templateUrl: "./pages/beliefs/beliefs.template.html",
  controller: function BeliefsController($http) {
    var self = this;

    $http.get("http://localhost:8080/api/beliefs").then(
      (res) => {
        self.beliefs = res.data;
      },
      (res) => {
        self.beliefs = [{ name: "Error!" + res.status }];
      }
    );

    this.welcome = "Our beliefs";
  },
});
