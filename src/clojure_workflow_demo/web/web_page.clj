(ns clojure-workflow-demo.web.web-page
  (:require [clojure-workflow-demo.domain.domain-stuff :as domain-model]
            [compojure.core :refer [GET POST routes]]
            [compojure.route :refer [not-found]]
            [compojure.handler]
            [ring.util.response]
            [hiccup.core]))

(defn create-html [domain-model]
  (let [value (domain-model/value domain-model)]
    (hiccup.core/html
     [:html
      [:div {:align "center"}
       [:h1 "Clojure Workflow Demo"]
       (str " The value is " value)
       [:br]
       [:br]
       [:form {:action "/inc-value"
               :method "post"}
        [:button {type "submit"}
         "Increment"]]]])))

(defn create-handler* [domain-model]
  (routes
   
   (GET "/" request
     (create-html domain-model))
   
   (POST "/inc-value" request
     (domain-model/inc-value domain-model)
     (ring.util.response/redirect-after-post "/"))
   
   (not-found "Sorry, there's nothing here.")))

(defn create-handler [domain-model]
  (compojure.handler/site
   (create-handler* domain-model)))
