$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("SpiGenericTests/SpiGeneric.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# new feature"
    }
  ],
  "line": 3,
  "name": "Spi Generic test cases",
  "description": "",
  "id": "spi-generic-test-cases",
  "keyword": "Feature",
  "tags": [
    {
      "line": 2,
      "name": "@SpiGeneric"
    }
  ]
});
formatter.scenarioOutline({
  "line": 6,
  "name": "Login-\u003cApp\u003e",
  "description": "",
  "id": "spi-generic-test-cases;login-\u003capp\u003e",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 5,
      "name": "@BasicWorkFlow"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "# Login Page"
    }
  ],
  "line": 8,
  "name": "I login to the \u0027SPI\u0027 application",
  "keyword": "Given "
});
formatter.examples({
  "line": 10,
  "name": "",
  "description": "",
  "id": "spi-generic-test-cases;login-\u003capp\u003e;",
  "rows": [
    {
      "cells": [
        "App"
      ],
      "line": 11,
      "id": "spi-generic-test-cases;login-\u003capp\u003e;;1"
    },
    {
      "cells": [
        "SPI Generic"
      ],
      "line": 12,
      "id": "spi-generic-test-cases;login-\u003capp\u003e;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 6815244,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Login-SPI Generic",
  "description": "",
  "id": "spi-generic-test-cases;login-\u003capp\u003e;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 2,
      "name": "@SpiGeneric"
    },
    {
      "line": 5,
      "name": "@BasicWorkFlow"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "# Login Page"
    }
  ],
  "line": 8,
  "name": "I login to the \u0027SPI\u0027 application",
  "keyword": "Given "
});
formatter.match({
  "location": "StepsLogin.i_login_to_the_spi_application()"
});
formatter.result({
  "duration": 11941652266,
  "status": "passed"
});
formatter.after({
  "duration": 1391475,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 14,
  "name": "Scenario - \u003ctestcase\u003e",
  "description": "",
  "id": "spi-generic-test-cases;scenario---\u003ctestcase\u003e",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 15,
  "name": "I click on \u0027New Customer \u0026 Quote\u0027 under Quote/Policy",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "I fill all the required fields in customer information",
  "keyword": "And "
});
formatter.examples({
  "line": 20,
  "name": "",
  "description": "",
  "id": "spi-generic-test-cases;scenario---\u003ctestcase\u003e;",
  "rows": [
    {
      "cells": [
        "testcase"
      ],
      "line": 21,
      "id": "spi-generic-test-cases;scenario---\u003ctestcase\u003e;;1"
    },
    {
      "cells": [
        "SPI001_CreateANewCustomer"
      ],
      "line": 22,
      "id": "spi-generic-test-cases;scenario---\u003ctestcase\u003e;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5135148,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Scenario - SPI001_CreateANewCustomer",
  "description": "",
  "id": "spi-generic-test-cases;scenario---\u003ctestcase\u003e;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 2,
      "name": "@SpiGeneric"
    }
  ]
});
formatter.step({
  "line": 15,
  "name": "I click on \u0027New Customer \u0026 Quote\u0027 under Quote/Policy",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "I fill all the required fields in customer information",
  "keyword": "And "
});
formatter.match({
  "location": "StepsLogin.i_click_on_new_customer_quote_under_policy_menu()"
});
formatter.result({
  "duration": 22341796017,
  "status": "passed"
});
formatter.match({
  "location": "StepsLogin.i_fill_all_required_fields_in_customer_information()"
});
formatter.result({
  "duration": 106367,
  "status": "passed"
});
formatter.after({
  "duration": 3774463,
  "status": "passed"
});
});