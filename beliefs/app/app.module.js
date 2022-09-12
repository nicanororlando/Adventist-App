"use strict";

// Define the `app` module
angular
  .module("app", [
    "ngAnimate",
    "ngMaterial",
    "ngMessages",
    "ngRoute",
    "core",
    "beliefs",
    "completeBelief",
    "studyBible",
    "appFooter",
    "beliefCard",
    "itdlCard",
    "menuButton",
    "navBar",
    "topBar",
  ])
  .config(function ($mdThemingProvider) {
    $mdThemingProvider.definePalette("primary", {
      50: "ffebee",
      100: "ffffff",
      200: "ef9a9a",
      300: "e57373",
      400: "ef5350",
      500: "003c71",
      600: "e53935",
      700: "d32f2f",
      800: "c62828",
      900: "b71c1c",
      A100: "ff8a80",
      A200: "ff5252",
      A400: "ff1744",
      A700: "d50000",
      // By default, text (contrast) on this palette should be white with 87% opacity.
      contrastDefaultColor: "light",
      // By default, for these darker hues, text (contrast) should be white with 100% opacity.
      contrastStrongLightColors: "white",
    });

    $mdThemingProvider.theme("default").primaryPalette("primary");

    $mdThemingProvider.definePalette("LightPalette", {
      500: "009688",
      50: "E0F2F1",
      100: "B2DFDB",
      200: "80CBC4",
      300: "4DB6AC",
      400: "26A69A",
      500: "009688",
      600: "00897B",
      700: "00796B",
      800: "00695C",
      900: "004D40",
      A100: "A7FFEB",
      A200: "f2ce1b",
      A400: "1DE9B6",
      A700: "00BFA5",
      // By default, text (contrast) on this palette should be dark with 87% opacity.
      contrastDefaultColor: "dark",
      // By default, for these darker hues, text (contrast) should be white with 100% opacity.
      contrastStrongLightColors: "800 900",
    });

    $mdThemingProvider.theme("default").accentPalette("LightPalette");
  });
