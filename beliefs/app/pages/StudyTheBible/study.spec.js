"use strict";

describe("completeBelief module", function () {
  beforeEach(module("completeBelief"));

  describe("CompleteBeliefController", function () {
    var $httpBackend, ctrl;

    var beliefTestData = {
      id: 1,
      slug: "test-belief",
      title: "Test belief",
      description: ["p1", "p2", "p3"],
      image: "https://imagenprueba.com",
      moreData: [
        { title: "Titulo 1", description: ["p1.1", "p1.2"] },
        {
          title: "Titulo 2",
          description: ["p2.1", "p2.2"],
          image: "https://tuvieja.com",
        },
      ],
    };

    beforeEach(inject(function (
      $componentController,
      _$httpBackend_,
      $routeParams
    ) {
      $httpBackend = _$httpBackend_;
      $httpBackend
        .expectGET("http://localhost:8080/api/beliefs/test-belief")
        .respond(beliefTestData);

      $routeParams.beliefId = "test-belief";

      ctrl = $componentController("completeBelief");
    }));

    it("should define component controller", inject(function () {
      expect(ctrl).toBeDefined();
    }));

    it("should fetch the complete belief", () => {
      jasmine.addCustomEqualityTester(angular.equals);

      expect(ctrl.belief).toEqual();

      $httpBackend.flush();
      expect(ctrl.belief).toEqual(beliefTestData);
    });
  });
});
