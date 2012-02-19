(ns adjutant.views.environments
  (:use noir.core)
  (:require [noir.response :as response]
            [adjutant.views.common :as common]
            [adjutant.services.services :as services]
            [adjutant.views.views :as views]))

(defn convert-into-link
  [environment] {:href (common/full-url-for (str "/environments/" (:id environment)))
                 :rel "environment"
                 :type "application/json"
                 :title (:name environment)})

(defpage "/environments"
  [] (response/json {:links (lazy-cat [
                             {:href (common/full-url-for "/")
                              :rel "root"
                              :type "application/json"}
                             {:href (common/full-url-for "/environments")
                              :rel "self"
                              :type "application/json"}]
                             (map convert-into-link (services/list-environments)))}))

(defpage "/environments/:id" {:keys [id]}
  (let [environment (services/get-environment id)]
    (if (nil? environment)
      (views/not-found)
      (response/json {:links [{:href (common/full-url-for (str "/environments/" (:id environment)))
                               :rel "self"
                               :type "application/json"}
                              {:href (common/full-url-for "/environments")
                               :rel "environments"
                               :type "application/json"}]
                      :name (:name environment)
                      :description (:description environment)
                      :environment-type (:environment-type environment)}))))