"use strict";

angular.module("completeBelief").component("completeBelief", {
  templateUrl: "../pages/completeBelief/belief.template.html",
  controller: [
    "$routeParams",
    "$http",
    function CompleteBeliefsController($routeParams, $http) {
      this.title = "Our beliefs";
      this.description =
        "Adventists regard the entire Holy Bible as the only sure thing rule of faith and hope. His doctrines, therefore, follow integrally the biblical teachings and are based on them. The Seventh-day Adventists accept the Bible as their only creed and hold fundamental beliefs based on the teachings of the Holy writings. These beliefs, as presented here, constitutes the way in which the church understands and expresses the Teachings of the Scriptures.";

      let url = "http://localhost:8080/api/beliefs/" + $routeParams.beliefSlug;

      $http.get(url).then(
        (res) => {
          this.belief = res.data;
        },
        (res) => {
          this.belief = [{ name: "Error!" + res.status }];
        }
      );
    },
  ],
});
