"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("beliefs").component("beliefs", {
  templateUrl: "./pages/beliefs/beliefs.template.html",
  controller: function BeliefsController($http) {
    this.welcome = "Our beliefs";
    var self = this;

    // http://localhost:8080/api/beliefs
    $http.get("http://localhost:8080/api/beliefs").then(
      (res) => {
        this.beliefs = res.data;
      },
      (res) => {
        this.beliefs = [{ name: "Error!" + res.status }];
      }
    );
  },
});
