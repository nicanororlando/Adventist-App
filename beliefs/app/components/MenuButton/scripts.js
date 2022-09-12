angular.module("menuButton", []).component("menuButton", {
  templateUrl: "./components/MenuButton/index.html",
  controller: function () {
    var vm = this;

    vm.active = false;
    this.toggle = function () {
      vm.active = !vm.active;
    };
  },
});
