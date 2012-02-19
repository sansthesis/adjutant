(ns adjutant.views.projects
  (:use noir.core)
  (:require [noir.response :as response]
            [adjutant.views.common :as common]))

(defpage "/projects"
  [] (response/json {:links [
                             {:href (common/full-url-for "/")
                              :rel "root"
                              :type "application/json"}
                             {:href (common/full-url-for "/projects")
                              :rel "self"
                              :type "application/json"}]}))
