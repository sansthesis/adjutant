(ns adjutant.views.common
  (:require [clojure.string :as string]
            [noir.request :as request]))

;; I can't wrap the Noir url-for function because it's a macro, apparently. I can't say (full-url-for hello) and have it prepend the protocol and then append the result of (url-for uri).
(defn full-url-for
  ([uri]
    (.toString(new java.net.URI(str
      (string/replace ((request/ring-request) :scheme) #":" "")
      "://"
      ((:headers (request/ring-request)) "host")
      (if (= (first uri) \/)
        uri
        (str \/ uri)))))))
