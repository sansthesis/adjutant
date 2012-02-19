(ns adjutant.views.environments
  (:use noir.core)
  (:use [ring.util.codec :only [url-encode url-decode base64-encode base64-decode]])
  (:import [java.net URLDecoder URLEncoder])
  (:require [noir.response :as response]
            [adjutant.views.common :as common]
            [adjutant.services.services :as services]
            [adjutant.views.views :as views]
            [adjutant.views.links :as links]))

(defn- convert-into-link
  [environment] (links/generate-environment-link (:id environment) "environment" (:name environment)))

(defpage "/environments"
  [] (response/json {:links (lazy-cat [
                             (links/generate-root-link)
                             (links/generate-environments-link "self")]
                             (map convert-into-link (services/list-environments)))}))

(defpage "/environments/:id" {:keys [id]}
  (let [environment (services/get-environment (String. (base64-decode id)))]
    (if (nil? environment)
      (views/not-found)
      (response/json (assoc
                       (dissoc environment :id)
                       :links [(links/generate-environment-link (:id environment) "self" (:name environment)) 
                               (links/generate-environments-link)])))))
