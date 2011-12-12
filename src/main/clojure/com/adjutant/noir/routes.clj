(ns com.adjutant.noir.routes
  (:use noir.core)
  (:require [noir.response :as response]
            [noir.request :as request]
            [clojure.string :as string]))

;; I can't override the Noir url-for function because it's a macro, apparently.  I can't say (full-url-for hello) and have it prepend the protocol and then append the result of (url-for uri).
(defn full-url-for
  ([uri]
    (str
      (string/replace (str ((request/ring-request) :scheme)) #":" "")
      "://"
      ((:headers (request/ring-request)) "host")
      uri)))

;; I'd love to query my list of defined routes that Noir stores, but I can't figure out from the source files where they store them all.
(defn get-root-resources []
  [{:href (full-url-for (url-for hello))
    :rel "test resource"
    :type "text/plain"}
   {:href (full-url-for (url-for root))
    :rel "self"
    :type "application/json"}])

;; Here I'm trying to define a new function that allows me to put the content-type into the arguments list.  Noir defines content types in the response body and I don't like that.  I just want to hide where it goes but I don't get clojure enough to figure out how to do that.
(defn define-resource
  ([label uri content-type]
    (.. System err (println (str "Label: " label ", uri: " uri ", content-type: " content-type)))
    (defpage label uri [] "hello")))

;; This is a test page so I can see if it works or not.
(defpage hello "/hello" []
  "hello")

;; This almost works but I want to switch from (defpage ...) to the (define-resource ...) function I'm trying to write.
(defpage root "/" []
  (response/json {:urls (get-root-resources)}))
