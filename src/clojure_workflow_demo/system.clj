(ns clojure-workflow-demo.system
  (:require [clojure-workflow-demo.domain.domain-stuff :as domain-stuff]
            [clojure-workflow-demo.web.server :as server]
            [clojure-workflow-demo.web.web-page :as web-page]))

(defn create-system []
  "Returns a new instance of the whole application."
  (let [domain-model (domain-stuff/create-domain-model)
        handler (web-page/create-handler domain-model)]
    {:domain-model domain-model
     :handler handler}))

;;;; ___________________________________________________________________________
;;;; ---- Production stuff ---

;;;; Maybe this belongs in a separate namespace that's only loaded in a
;;;; production profile.

(def the-production-system
  (create-system))

(def the-production-handler
  "The production system's Ring handler. Can be used by `lein ring`."
  (:handler the-production-system))

;;;; ___________________________________________________________________________
;;;; ---- Dev stuff ---

;;;; Maybe this belongs in a separate namespace that's only loaded in a
;;;; dev profile.

(defn create-dev-system
  "Returns a new dev instance of the whole application."
  [port]
  (into (create-system)
        {:port port}))

(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (let [server (server/create (:handler system)
                              :port (:port system))]
    (into system
          {:server server})))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (when (:server system)
    (server/stop (:server system)))
  (dissoc system :server))
