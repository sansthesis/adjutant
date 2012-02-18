(ns adjutant.views.environments
  (:use noir.core)
  (:require [noir.response :as response]
            [adjutant.views.common :as common]))

(defpage "/environments"
  [] (response/json {:links [
                             {:href (common/full-url-for "/")
                              :rel "root"
                              :type "application/json"}
                             {:href (common/full-url-for "/environments")
                              :rel "self"
                              :type "application/json"}]}))
