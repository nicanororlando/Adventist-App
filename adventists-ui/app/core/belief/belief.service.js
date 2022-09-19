"use strict";

angular.module("core.belief", []).factory("Belief", function ($http) {
  return {
    get: function (parameters) {
      console.log(parameters);

      return $http({
        method: "GET",
        url: "http://localhost:8080/api/belief",
        params: { location: parameters },
      });
    },
  };
});

// angular.module("core.belief").factory("Belief", [
//   "$resource",
//   function ($resource) {
//     return $resource(
//       "http://localhost:8080/api/beliefs",
//       {},
//       {
//         query: {
//           method: "GET",
//           params: { beliefId: "beliefs" },
//           isArray: true,
//         },
//       }
//     );
//   },
// ]);
