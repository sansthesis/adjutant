(ns adjutant.aws
  (:use noir.core)
  (:use clojure.java.io)
  (:require clojure.stacktrace)
  (:import 
    [com.amazonaws.auth AWSCredentials PropertiesCredentials BasicAWSCredentials]
    [java.util Properties]
    [com.amazonaws.services.cloudformation AmazonCloudFormation AmazonCloudFormationClient]))

(defn- load-credentials
  []
  (PropertiesCredentials.
    (as-file (str (System/getenv "HOME") "/.aws_djcubed_credentials"))))

(def credentials (load-credentials))

(def endpoint-map { :us-east-1 
                   { :cloudformation "cloudformation.us-east-1.amazonaws.com"}})

(defn get-cloudformation-client
  ([region]
    (doto (AmazonCloudFormationClient. credentials)
      (.setEndpoint ((endpoint-map region) :cloudformation))))
  ([] (get-cloudformation-client :us-east-1)))

;(defn list-stacks 
;  ([region] 
;    (map mapify-stack
;      (-> 
;        (doto 
;          (AmazonCloudFormationClient. credentials)
;          (.setEndpoint ((endpoint-map region) :cloudformation)))
;        (.describeStacks)
;        (.getStacks))))
;  ([] (list-stacks :us-west-1)))

;(defn mapify-stack
;  [s]
;  (->
;    (bean s)
;    (update-in [:parameters] 
;               (fn [x] 
;                 (apply merge 
;                        (map 
;                          (fn [y] { (keyword (:parameterKey y)) (:parameterValue y)}) 
;                          (map bean x)))))
;    (update-in [:outputs] 
;               (fn [x]
;                 (apply merge 
;                        (map 
;                          (fn [y] { (keyword (:outputKey y)) (:outputValue y)}) 
;                          (map bean x)))))))
  