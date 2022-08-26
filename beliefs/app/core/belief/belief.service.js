"use strict";

angular.module("core.belief").factory("Belief", [
  "$resource",
  function ($resource) {
    return $resource(
      "phones/:phoneId.json",
      {},
      {
        query: {
          method: "GET",
          params: { beliefId: "beliefs" },
          isArray: true,
        },
      }
    );
  },
]);
