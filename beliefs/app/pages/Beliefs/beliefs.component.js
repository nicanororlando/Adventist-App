"use strict";

angular.module("beliefs").component("beliefs", {
  templateUrl: "./pages/beliefs/beliefs.template.html",
  controller: function BeliefsController($http) {
    this.title = "Our beliefs";

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
