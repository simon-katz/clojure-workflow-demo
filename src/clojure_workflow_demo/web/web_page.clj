(ns clojure-workflow-demo.web.web-page
  (:require [clojure-workflow-demo.domain.domain-stuff :as domain-model]
            [compojure.core :refer [GET PUT routes]]
            [compojure.route :refer [not-found]]
            [compojure.handler]
            [hiccup.core]))

(defn create-handler* [domain-model]
  (routes
   (GET "/" request
     (let [value (domain-model/value domain-model)]
       (hiccup.core/html
        [:html
         [:b "Clojure Workflow Demo"]
         [:br]
         [:br]
         (str " The value is "
              value
              ".")])))
   (PUT "/inc-value" request
     (let [value (domain-model/inc-value domain-model)]
       (str value)))
   (not-found "Sorry, there's nothing here.")))

(defn create-handler [domain-model]
  (compojure.handler/site
   (create-handler* domain-model)))
