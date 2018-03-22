# Code style in ShopMe project

## Project language:
* english

## Java coding guidelines

* split implementation by functionality using java packages, redundant class
  names, e.g. `com.intive.login.LoginController`

* use as narrow visibility scope as possible, favor default package protected
  scope

* use internal classes when appropriate

* decouple system exposed classes from internal (Resource from DB model)

* use mapper for convert internal model from external, `ObjectMapper` could help
  but only for 1-1 mappings (rare cases)

* Offer model - start with simple model, give us easy possibility to change
  internal details

* Do not use primitive types in model classes

* prefer immutable classes, especially for model (use `final`)

* Java class names ending with `Util` or `Helper` are considered bad smell and
  should be avoided

* `/offers/categories` vs `/categories` - no real arguments -> use `/categories`

* test data - let's not mix implementation with tests, provide test data as
  file, easy script to ingest. Couple it with whats stable -> REST API (JSON is
  natural choice)

* Category validator, Identifiable interface - explain your ideas as new PR and
  discuss it on slack or github


## Code formatter:

* use default IntelliJ IDEA formatter

* make sure EOL is automatically added

* avoid wildcard imports


## Lessons learned so far

* no test means fear for change, unit test are good

* coupling is bad, we cannot easily change our decisions (implementation)

* universal model makes your implementation complicated

* better is start from brain stupid and deliver than invent racket and not
  deliver (ideal is to fly by clojure ;P)
  
* java package system is limited, Scala has better

* resources (like time) are limited - cope with that
