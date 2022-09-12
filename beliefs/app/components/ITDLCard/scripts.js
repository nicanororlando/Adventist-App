var isCard;

angular.module("itdlCard", []).component("itdlCard", {
  templateUrl: "./components/ITDLCard/index.html",
  bindings: {
    data: "=",
  },
});
