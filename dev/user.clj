(ns user
  "Namespace to support hacking at the REPL."
  (:require [clojure-workflow-demo.system :as system]
            [clojure-workflow-demo.domain.domain-stuff :as domain-stuff]
            [clojure.tools.namespace.repl :refer :all]
            [clojure.tools.namespace.move :refer :all]
            [clojure.repl :refer :all]
            [clojure.pprint :refer :all]
            [spyscope.core]
            [cemerick.pomegranate :refer [add-dependencies]]
            [clojure.java.io :as cjio]
            [clojure.string :as str]
            [clojure.java.classpath :as cjc]
            [criterium.core :as crit]))

(def dev-port 7601)

(def system
  "A container for the current instance of the application.
  Only used for interactive development."
  nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
    (constantly (system/dev-system dev-port))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'system system/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'system
    (fn [s] (when s (system/stop s)))))

(defn go
  "Initializes the current development system and starts it running."
  []
  (init)
  (start))

(defn reset []
  "Stop, refresh and go."
  (stop)
  (refresh :after 'user/go))

;;;;; Useful additional utilities for the REPL

(defn inc-value-in-domain-model []
  (domain-stuff/inc-value-in-domain-model (:domain-model system)))
