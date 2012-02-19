(ns adjutant.aws
  (:use noir.core)
  (:use clojure.java.io)
  (:require clojure.stacktrace)
  (:import [com.amazonaws.auth AWSCredentials PropertiesCredentials BasicAWSCredentials])
  (:import [java.util Properties])
  (:import [com.amazonaws.services.cloudformation AmazonCloudFormation AmazonCloudFormationClient]))

(defn- load-credentials
  []
  (PropertiesCredentials.
    (as-file (str (System/getenv "HOME") "/.aws_djcubed_credentials"))))

(def credentials (load-credentials))

(def endpoint-map { :us-west-1 
                   { :cloudformation "cloudformation.us-east-1.amazonaws.com"}})

(defn list-stacks 
  ([region] 
    (-> 
      (doto 
        (AmazonCloudFormationClient. credentials)
        (.setEndpoint ((endpoint-map region) :cloudformation)))
      (.describeStacks)
      (.getStacks)))
  ([] (list-stacks :us-west-1)))
