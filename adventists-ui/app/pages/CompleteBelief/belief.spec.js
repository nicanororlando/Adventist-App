"use strict";

describe("completeBelief module", function () {
  describe("CompleteBeliefController", function () {
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

    var env;

    beforeEach(module("completeBelief"));
    beforeEach(inject(function (
      // $injector,
      // _env_,
      $componentController,
      _$httpBackend_,
      $routeParams
    ) {
      // env = $injector.get("env");
      // env = _env_;
      this.$httpBackend = _$httpBackend_;
      this.$httpBackend
        .expectGET(
          // env.ADVENTISTS_API_BASE_URL +
          "http://localhost:8888/beliefs/slug/test-belief"
        )
        .respond(beliefTestData);

      $routeParams.beliefSlug = "test-belief";

      this.ctrl = $componentController("completeBelief");
    }));

    it("should define component controller", inject(function () {
      expect(this.ctrl).toBeDefined();
    }));

    it("should fetch the complete belief", function () {
      jasmine.addCustomEqualityTester(angular.equals);

      expect(this.ctrl.belief).toEqual();

      this.$httpBackend.flush();
      expect(this.ctrl.belief).toEqual(beliefTestData);
    });
  });
});
