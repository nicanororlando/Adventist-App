"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("beliefs").component("beliefs", {
  templateUrl: "./pages/beliefs/beliefs.template.html",
  controller: function BeliefsController($http) {
    this.welcome = "Our beliefs";

    $http.get("http://localhost:8080/api/beliefs").then(
      (res) => {
        this.beliefs = res.data;

        let allVers = [];

        for (let i = 0; i < this.beliefs.length; i++) {
          let books = [];
          books.push(_.last(this.beliefs[i].description));

          let obj = {};
          obj.id = i;
          obj.books = _.split(books, "; ");

          allVers.push(obj);
        }

        this.verses = allVers;
        console.log(this.verses);
      },
      (res) => {
        this.beliefs = [{ name: "Error!" + res.status }];
      }
    );
  },
});
