(ns adjutant.views.root
  (:use noir.core)
  (:require [noir.response :as response]
            [adjutant.views.links :as links]))

(defpage "/"
  [] (response/json {:links [
                             (links/generate-root-link "self")
                             (links/generate-environments-link)]}))
