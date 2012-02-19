(ns adjutant.views.views
  (:require [clojure.string :as string]
            [noir.request :as request]))

(defn not-found
  [] {:status 404})
