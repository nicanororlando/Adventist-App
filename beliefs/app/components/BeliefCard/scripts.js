var isCard;

angular.module("beliefCard", []).component("beliefCard", {
  templateUrl: "./components/BeliefCard/index.html",
  bindings: {
    belief: "=",
  },
  controller: function ($scope, $window) {
    $scope.openLink = function (link) {
      $window.open(link + "/download");
    };

    this.$onInit = function () {
      let verses = _.split(_.last(this.belief.description), "; ");
      this.verses = verses;
    };
  },
});
