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

(defonce the-system
  ;; "A container for the current instance of the application.
  ;; Only used for interactive development."
  ;; 
  ;; Don't want to lose this value if this file is recompiled (when
  ;; changes are made to the useful additional utilities for the REPL
  ;; at the end of the file), so use `defonce`.
  ;; But note that this /is/ blatted when a `reset` is done.
  nil)

(defn init
  "Creates a system and makes it the current development system."
  []
  (alter-var-root #'the-system
                  (constantly (system/create-system))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'the-system system/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'the-system
                  (fn [s] (when s (system/stop s)))))

(defn go
  "Creates a system, makes it the current development system and starts it."
  []
  (init)
  (start))

(defn reset
  "Stop, refresh and go."
  []
  (stop)
  (refresh :after 'user/go))

;;;; Useful additional utilities for the REPL

(defn inc-value []
  (domain-stuff/inc-value (:domain-model the-system)))
