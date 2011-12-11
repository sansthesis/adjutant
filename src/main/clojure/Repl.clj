(ns Repl
  (:use com.adjutant.noir.routes)
  (:require [Test :as test]
            [noir.server :as server]))

(server/start 8080)
