"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("studyBible").component("studyBible", {
  templateUrl: "./pages/StudyTheBible/study.template.html",
  controller: function StudyTheBibleController($http) {
    this.title =
      "Find a free online Bible study to lead you through God’s Word.";
    this.description =
      "As Seventh-day Adventists, our promise is to help our friends understand the Bible to find freedom, healing and hope in Jesus. If you would like to experience this kind of relationship with Jesus and learn more about His plans for you, we’ve selected a variety of Bible studies to get you started.";

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
