(ns adjutant.views.root
  (:use noir.core)
  (:require [noir.response :as response]))

(defpage "/"
  [] (response/json {:links [
                             {:href "/"
                              :rel "root"
                              :type "application/json"}]}))
