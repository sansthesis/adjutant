(ns adjutant.services.services
  (:import [com.amazonaws.services.cloudformation.model DescribeStacksRequest])
  (:use adjutant.aws)
  (:use clojure.set))

(defn make-environment-fixture
  [id] {:id id
        :name (str "environment " id)
        :description (str "description for environment " id)
        :environment-type "QA"})

(defn stack-to-env
  [s]
  (->
    (bean s)
    (select-keys [:stackId :stackName :description :stackStatus])
    (rename-keys {:stackId :id, :stackName :name, :stackStatus :status})
    (assoc 
      :environment-type
      (or (first 
            (map (fn [o] (.getOutputValue o)) 
                 (filter (fn [x] (= (.getOutputKey x) "ProvisioningGroup")) 
                         (.getOutputs s))))
          "Development"))))

;;(defn list-environments
;;  [] (take 10 (map make-environment-fixture (range))))

(defn list-environments
  [] (map stack-to-env (-> (get-cloudformation-client) (.describeStacks) (.getStacks))))


;(defn get-environment
;  [id] (first (filter #(= (str (:id %)) id) (list-environments))))

(defn get-environment
  [id]
  (-> 
    (get-cloudformation-client)
    (.describeStacks (doto (DescribeStacksRequest.) (.setStackName id)))
    (.getStacks)
    (first)
    (stack-to-env)))
