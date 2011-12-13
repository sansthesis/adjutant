(ns com.adjutant.noir.common
  (:require [clojure.string :as string]
            [noir.request :as request]))


;; I can't wrap the Noir url-for function because it's a macro, apparently.  I can't say (full-url-for hello) and have it prepend the protocol and then append the result of (url-for uri).
(defn full-url-for
  ([uri]
    (str
      (string/replace ((request/ring-request) :scheme) #":" "")
      "://"
      ((:headers (request/ring-request)) "host")
      uri)))

;; Here I'm trying to define a new function that allows me to put the content-type into the arguments list.  Noir defines content types in the response body and I don't like that.  I just want to hide where it goes but I don't get clojure enough to figure out how to do that.
;(defn define-resource
;  ([label uri content-type]
;    (.. System err (println (str "Label: " label ", uri: " uri ", content-type: " content-type)))
;    (defpage label uri [] "hello")))
