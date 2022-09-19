"use strict";

// Register `beliefs` component, along with its associated controller and template
angular.module("studyBible").component("studyBible", {
  templateUrl: "./pages/StudyTheBible/study.template.html",
  controller: function StudyTheBibleController($http, $window) {
    this.title =
      "Find a free online Bible study to lead you through God’s Word.";
    this.description =
      "As Seventh-day Adventists, our promise is to help our friends understand the Bible to find freedom, healing and hope in Jesus. If you would like to experience this kind of relationship with Jesus and learn more about His plans for you, we’ve selected a variety of Bible studies to get you started.";

    this.user = {
      enableSendMsg: true,
    };

    $http.get("http://localhost:8080/api/bible-studies").then(
      (res) => {
        this.bibleStudies = res.data;
      },
      (res) => {
        this.bibleStudies = [{ name: "Error!" + res.status }];
      }
    );

    this.submitInfoUserForm = function () {
      var onSuccess = function (data) {
        if (!data.errors) {
          this.message = data.message;
        }
      };
      var onError = function (data) {
        this.message = data.message;
      };

      $http({
        method: "POST",
        url: "http://localhost:8080",
        data: this.user,
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
      })
        .success(onSuccess)
        .error(onError);
    };
  },
});
