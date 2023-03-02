"use strict";

angular.module("completeBelief").component("completeBelief", {
  templateUrl: "../pages/completeBelief/belief.template.html",
  controller: function CompleteBeliefsController(env, $routeParams, $http) {
    this.title = "Our beliefs";
    this.description =
      "Adventists regard the entire Holy Bible as the only sure thing rule of faith and hope. His doctrines, therefore, follow integrally the biblical teachings and are based on them. The Seventh-day Adventists accept the Bible as their only creed and hold fundamental beliefs based on the teachings of the Holy writings. These beliefs, as presented here, constitutes the way in which the church understands and expresses the Teachings of the Scriptures.";

    let url = env.API_URL_BELIEFS_SLUG + $routeParams.beliefSlug;
    // "http://localhost:8888/beliefs/slug/" + $routeParams.beliefSlug;

    $http.get(url).then(
      (res) => {
        this.belief = res.data;
      },
      () => {
        this.error = "ERROR: Failed to load data";
      }
    );
  },
});
