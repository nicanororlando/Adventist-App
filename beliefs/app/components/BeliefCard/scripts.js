var isCard;

angular.module("beliefCard", []).component("beliefCard", {
  templateUrl: "./components/BeliefCard/index.html",
  controller: function BeliefCardController($document, $window) {
    // console.log(this.verses);

    // this.verses = this.belief.description;
    // console.log(this.verses);

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
    };
  },
  bindings: {
    belief: "=",
    verses: "=",
  },
});
