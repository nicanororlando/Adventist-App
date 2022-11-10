"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("studyBible").component("studyBible", {
  templateUrl: "./pages/StudyTheBible/study.template.html",
  controller: function StudyTheBibleController(env, $http) {
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

    $http.get(env.API_URL_STUDY_BIBLE).then(
      (res) => {
        this.bibleStudies = res.data;
      },
      () => {
        this.error = "ERROR: Failed to load data";
      }
    );

    this.submitInfoUserForm = function () {
      $http.post("http://localhost:8888/bible-studies/users", this.user).then(
        function successCallback(response) {
          self.msg = { message: "Data sent successfully", success: true };
        },
        function errorCallback(response) {
          self.msg = { message: response.data.message, success: false };
        }
      );
    };
  },
});
