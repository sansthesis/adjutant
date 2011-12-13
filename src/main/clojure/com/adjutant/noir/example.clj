(ns com.adjutant.noir.example
  (:use noir.core)
  (:require [noir.response :as response]
            [com.adjutant.noir.common :as common])) 

;; This is a test page so I can see if it works or not.
(defpage hello "/hello"
  [] (response/content-type "text/html" (str "hello from " (common/full-url-for (url-for hello)))))
