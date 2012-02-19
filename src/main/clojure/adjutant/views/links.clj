(ns adjutant.views.links
  (:use noir.core)
  (:require [adjutant.views.common :as common]))

(defn- remove-null-values
  [record] (into {} (remove (comp nil? second) record)))

(defn- generate-link
  [& args] (remove-null-values (apply hash-map args)))

(defn generate-root-link
  ([] (generate-root-link "root"))
  ([rel] (generate-link :href (common/full-url-for "/") :rel rel :type "application/json")))

(defn generate-environments-link
  ([] (generate-environments-link "environments"))
  ([rel] (generate-link :href (common/full-url-for "/environments") :rel rel :type "application/json")))

(defn generate-environment-link
  ([id] (generate-environment-link id "environment"))
  ([id rel] (generate-environment-link id rel nil))
  ([id rel title] (generate-link :href (common/full-url-for (str "/environments/" id)) :rel rel :type "application/json" :title title)))
