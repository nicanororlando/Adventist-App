"use strict";

describe("beliefs", function () {
  beforeEach(module("beliefs"));

  describe("BeliefsController", function () {
    var $httpBackend, ctrl;

    /* Usamos el inject para injectar las instancias del component controller y 
    httpBackend services en la funcion beforeEach de Jasmine. */
    beforeEach(inject(function ($componentController, _$httpBackend_) {
      $httpBackend = _$httpBackend_;
      $httpBackend
        .expectGET("http://localhost:8080/api/beliefs")
        .respond(
          { id: 1, title: "belief-1, description: ['p1.1', 'p1.2']" },
          { id: 2, title: "belief-2, description: ['p2.1', 'p2.2']" }
        );

      ctrl = $componentController("beliefs");
    }));

    it("should define component controller.", inject(function () {
      expect(ctrl).toBeDefined();
    }));

    it("should set at default value for the 'title' property", function () {
      expect(ctrl.title).toBe("Our beliefs");
    });

    it("should create 2 beliefs fetched with '$http'", function () {
      jasmine.addCustomEqualityTester(angular.equals);

      expect(ctrl.beliefs).toEqual();

      /* Vaciamos la cola de solicitudes en el navegador, llamando a 'flush'. Esto
      hace que la promesa retornada por el servicio http se resuelva con la respuesta
      entrenada, osea que descarte explicitamente las solicitudes pendientes. Esto 
      conserva la API asincrona del backend, al mismo tiempo que permite que el test
      se ejecute de forma sincrona. */
      $httpBackend.flush();
      expect(ctrl.beliefs).toEqual(
        { id: 1, title: "belief-1, description: ['p1.1', 'p1.2']" },
        { id: 2, title: "belief-2, description: ['p2.1', 'p2.2']" }
      );
    });
  });
});
