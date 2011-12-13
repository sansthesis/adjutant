(ns com.adjutant.noir.routes
  (:use noir.core)
  (:require [noir.response :as response]
            [com.adjutant.noir.common :as common]))

;; I'd love to query my list of defined routes that Noir stores, but I can't figure out from the source files how they store them all.
(defn get-root-resources []
  [{:href (common/full-url-for (url-for hello))
    :rel "test resource"
    :type "text/plain"}
   {:href (common/full-url-for (url-for root))
    :rel "self"
    :type "application/json"}]) 

;; This almost works but I want to switch from (defpage ...) to the (define-resource ...) function I'm trying to write.
(defpage root "/"
  [] (response/json {:urls (get-root-resources)}))
