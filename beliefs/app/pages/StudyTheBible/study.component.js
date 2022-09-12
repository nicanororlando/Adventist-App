"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("studyBible").component("studyBible", {
  templateUrl: "./pages/StudyTheBible/study.template.html",
  controller: function StudyTheBibleController($http) {
    this.project = {
      msg: true,
    };

    $http.get("http://localhost:8080/api/bible-studies").then(
      (res) => {
        this.bibleStudies = res.data;
      },
      (res) => {
        this.bibleStudies = [{ name: "Error!" + res.status }];
      }
    );
  },
});
