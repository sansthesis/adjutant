(ns com.adjutant.noir.server
  (:gen-class)
  (:use com.adjutant.noir.routes)
  (:use com.adjutant.noir.example)
  (:require [noir.server :as server]))

;(server/load-views "src/my_website/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'com.adjutant.noir})))
