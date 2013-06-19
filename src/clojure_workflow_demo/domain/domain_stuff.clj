(ns clojure-workflow-demo.domain.domain-stuff)

(defn create-domain-model []
  (atom 0))

(defn value [model]
  @model)

(defn inc-value [model]
  (swap! model inc))
