(ns clojure-workflow-demo.web.server
  (:require [ring.adapter.jetty :as jetty]))

(defn create
  [handler & {:keys [port]}]
  {:pre [(not (nil? port))]}
  (jetty/run-jetty handler {:port port :join? false}))

(defn stop
  [server]
  (.stop server))
