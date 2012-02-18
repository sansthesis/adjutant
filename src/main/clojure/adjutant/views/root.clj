(ns adjutant.views.root
  (:use noir.core)
  (:require [noir.response :as response]
            [adjutant.views.common :as common]))

(defpage "/"
  [] (response/json {:links [
                             {:href (common/full-url-for "/")
                              :rel "self"
                              :type "application/json"}
                             {:href (common/full-url-for "/environments")
                              :rel "environments"
                              :type "application/json"}]}))
