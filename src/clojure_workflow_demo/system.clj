(ns clojure-workflow-demo.system
  (:require [clojure-workflow-demo.domain.domain-stuff :as domain-stuff]
            [clojure-workflow-demo.web.server :as server]
            [clojure-workflow-demo.web.web-page :as web-page]))

(def port 7601)

(defn create-system []
  "Returns a new instance of the whole application."
  (let [domain-model (domain-stuff/create-domain-model)
        handler (web-page/create-handler domain-model)]
    {:domain-model domain-model
     :handler handler
     :port port}))

(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (let [server (server/create-and-start (:handler system)
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

(defn -main [& args]
  (let [system (create-system)]
    (start system)))
