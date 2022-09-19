"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("studyBible").component("studyBible", {
  templateUrl: "./pages/StudyTheBible/study.template.html",
  controller: function StudyTheBibleController($http, $window) {
    var self = this;
    this.title =
      "Find a free online Bible study to lead you through God’s Word.";
    this.description =
      "As Seventh-day Adventists, our promise is to help our friends understand the Bible to find freedom, healing and hope in Jesus. If you would like to experience this kind of relationship with Jesus and learn more about His plans for you, we’ve selected a variety of Bible studies to get you started.";

    this.user = {
      wantToStudy: true,
      enableSendMsg: true,
    };

    this.msg = { message: "", success: false };

    $http.get("http://localhost:8888/bible-studies").then(
      (res) => {
        this.bibleStudies = res.data;
      },
      (res) => {
        this.bibleStudies = { name: "Error!" + res.status };
      }
    );

    this.submitInfoUserForm = function () {
      $http
        .post("http://localhost:8081/api/bible-studies/users", this.user)
        .then(
          // $http.post("http://localhost:8888/bible-studies/users", this.user).then(
          function suc0cessCallback(response) {
            self.msg = { message: response.data.message, success: true };
          },
          function errorCallback(response) {
            if (response.status == 409)
              self.msg = { message: response.data.message, success: false };
            else
              self.msg = { message: "Internal server error", success: false };
          }
        );
    };
  },
});
