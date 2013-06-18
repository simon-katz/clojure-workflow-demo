(ns clojure-workflow-demo.domain.domain-stuff)

(defn create-domain-model []
  (atom 0))

(defn value-in-domain-model [model]
  @model)

(defn inc-value-in-domain-model [model]
  (swap! model inc))
