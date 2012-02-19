(ns adjutant.services.services)

(defn make-environment-fixture
  [id] {:id id
        :name (str "environment " id)
        :description (str "description for environment " id)
        :environment-type "QA"})

(defn list-environments
  [] (take 10 (map make-environment-fixture (range))))

(defn get-environment
  [id] (first (filter #(= (str (:id %)) id) (list-environments))))
