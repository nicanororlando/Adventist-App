var isCard;

angular.module("fabSpeedDial", []).component("fabSpeedDial", {
  templateUrl: "./components/FabSpeedDial/index.html",
  // bindings: {
  //   data: "=",
  // },
  controller: function FabSpeedDialController() {
    this.isOpen = false;
  },
});
