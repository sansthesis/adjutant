(ns com.adjutant.noir.routes
  (:use noir.core)
  (:require [noir.response :as response]
            [noir.request :as request]
            [clojure.string :as string]))

(defn full-url-for
  ([uri]
;    (.. System err (println (str (request/ring-request))))
    (str
      (string/replace (str ((request/ring-request) :scheme)) #":" "")
      "://"
      ((:headers (request/ring-request)) "host")
      uri)))

(defpage hello "/hello" []
  "hello")

(defpage root "/" []
  (response/json {:urls [
                         {:href (full-url-for (url-for hello))
                          :rel "test resource"
                          :type "text/plain"}
                         {:href (full-url-for (url-for root))
                          :rel "self"
                          :type "application/json"}]}))
