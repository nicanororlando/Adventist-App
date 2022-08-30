"use strict";

angular.module("core.belief").factory("Belief", [
  "$resource",
  function ($resource) {
    return $resource(
      "http://localhost:8080/api/beliefs",
      {},
      {
        query: {
          method: "GET",
          // params: { beliefId: "beliefs" },
          isArray: true,
        },
      }
    );
  },
]);
