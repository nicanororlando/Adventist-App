var isCard;

angular.module("beliefCard", []).component("beliefCard", {
  templateUrl: "./components/BeliefCard/index.html",
  bindings: {
    belief: "=",
  },
  controller: function ($document, $window) {
    this.$onInit = function () {
      let verses = _.split(_.last(this.belief.description), "; ");
      this.verses = verses;
    };

    if (
      $document[0].documentElement.clientWidth < 576 ||
      $document[0].documentElement.clientWidth > 1200
    ) {
      this.isCard = true;
    } else isCard = false;
    $window.onresize = () => {
      if (
        $document[0].documentElement.clientWidth < 576 ||
        $document[0].documentElement.clientWidth > 1200
      ) {
        this.isCard = true;
      } else isCard = false;
      console.log(this.isCard);
    };
  },
});
